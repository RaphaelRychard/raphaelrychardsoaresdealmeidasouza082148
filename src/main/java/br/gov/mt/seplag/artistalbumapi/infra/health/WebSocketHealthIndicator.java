package br.gov.mt.seplag.artistalbumapi.infra.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Component("websocket")
public class WebSocketHealthIndicator implements HealthIndicator {

    private final StandardWebSocketClient client = new StandardWebSocketClient();

    @Value("${app.health.websocket-url:ws://localhost:8080/ws}")
    private String wsUrl;

    @Value("${app.health.websocket-timeout-ms:1500}")
    private long timeoutMs;

    @Override
    public Health health() {
        try {
            client.execute(
                    new AbstractWebSocketHandler() {
                    },
                    new WebSocketHttpHeaders(),
                    URI.create(wsUrl)
            ).get(timeoutMs, TimeUnit.MILLISECONDS);

            return Health.up().withDetail("handshake", "ok").build();
        } catch (Exception ex) {
            return Health.down().withDetail("reason", "WebSocket handshake failed").build();
        }
    }
}