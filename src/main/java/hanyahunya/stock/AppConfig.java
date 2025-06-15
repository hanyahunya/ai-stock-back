package hanyahunya.stock;

import hanyahunya.stock.util.service.EncodeService;
import hanyahunya.stock.util.service.PBKDF2EncodeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    // ここからutilパッケージ
    @Bean
    public EncodeService encodeService() {
        return new PBKDF2EncodeService();
    }
    // ここまでutilパッケージ
}
