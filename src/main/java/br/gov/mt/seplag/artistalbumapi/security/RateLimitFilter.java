package br.gov.mt.seplag.artistalbumapi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    @Value("${rate.limit.requests:10}")
    private int LIMIT;

    @Value("${rate.limit.window:60000}")
    private long WINDOW;

    private static class UserRate {
        AtomicInteger count = new AtomicInteger(0);
        volatile long windowStart = System.currentTimeMillis();
    }

    private final Map<String, UserRate> userRates = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        userRates.entrySet().removeIf(entry -> System.currentTimeMillis() - entry.getValue().windowStart > WINDOW * 2);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = auth.getName();
        UserRate rate = userRates.computeIfAbsent(username, k -> new UserRate());

        synchronized (rate) {
            long now = System.currentTimeMillis();

            if (now - rate.windowStart > WINDOW) {
                rate.count.set(0);
                rate.windowStart = now;
            }

            if (rate.count.incrementAndGet() > LIMIT) {
                long resetSeconds = (rate.windowStart + WINDOW - now) / 1000;
                response.setStatus(429);
                response.setHeader("X-RateLimit-Limit", String.valueOf(LIMIT));
                response.setHeader("X-RateLimit-Remaining", "0");
                response.setHeader("X-RateLimit-Reset", String.valueOf(Instant.now().getEpochSecond() + resetSeconds));
                response.getWriter().write("Too Many Requests");
                return;
            }

            response.setHeader("X-RateLimit-Limit", String.valueOf(LIMIT));
            response.setHeader("X-RateLimit-Remaining", String.valueOf(LIMIT - rate.count.get()));
            response.setHeader("X-RateLimit-Reset", String.valueOf((rate.windowStart + WINDOW - now) / 1000));
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().startsWith("/ws");
    }
}
