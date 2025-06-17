package hanyahunya.stock.external.kiwoom;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class AccessTokenScheduler {
    private final KiwoomService kiwoomService;

    @Scheduled(cron = "0 0 0 * * *")
    public void setAccessToken() {
        kiwoomService.setToken();
    }
}
