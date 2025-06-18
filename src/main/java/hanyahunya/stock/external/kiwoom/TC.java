package hanyahunya.stock.external.kiwoom;

import hanyahunya.stock.external.kiwoom.dto.StockDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

// test Controller!
@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TC {
    private final KiwoomService kiwoomService;
    @GetMapping
    public ResponseEntity<Map<LocalDate, StockDetailDto>> test() {
        return ResponseEntity.ok(kiwoomService.getDailyStockDetails("005930", LocalDate.now()));
    }
}
