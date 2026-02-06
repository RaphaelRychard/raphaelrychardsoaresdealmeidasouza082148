package br.gov.mt.seplag.artistalbumapi.modules.regional.scheduler;

import br.gov.mt.seplag.artistalbumapi.modules.regional.dto.response.SyncRegionalResponseDTO;
import br.gov.mt.seplag.artistalbumapi.modules.regional.service.RegionalSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(
        prefix = "app.regional-sync",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = false
)
public class RegionalSyncScheduler {

    @Autowired
    private RegionalSyncService regionalSyncService;

    @Scheduled(cron = "${app.regional-sync.cron:0 0 2 * * ?}")
    public void scheduledSync() {
        log.info("Starting scheduled regional synchronization");

        try {
            SyncRegionalResponseDTO result = regionalSyncService.syncRegionals();

            log.info("Scheduled sync completed successfully: fetched={}, inserted={}, updated={}, inactivated={}",
                    result.getTotalFetched(),
                    result.getInserted(),
                    result.getUpdated(),
                    result.getInactivated()
            );

        } catch (Exception ex) {
            log.error("Error during scheduled regional synchronization", ex);
        }
    }
}