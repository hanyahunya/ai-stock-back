package hanyahunya.stock.dailyStock;

import hanyahunya.stock.dailyStock.Dto.StockChartResDto;
import hanyahunya.stock.dailyStock.service.DailyStockService;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static hanyahunya.stock.util.ResponseUtil.toResponse;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class DailyStockController {
    private final DailyStockService dailyStockService;

    @GetMapping("/{stockCode}/chart")
    public ResponseEntity<ResponseDto<StockChartResDto>> getChart(@PathVariable String stockCode) {
        return toResponse(dailyStockService.getStockChart(stockCode));
    }
}
