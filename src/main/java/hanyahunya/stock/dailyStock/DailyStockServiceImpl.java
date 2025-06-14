package hanyahunya.stock.dailyStock;

import hanyahunya.stock.dailyStock.Dto.StockChartPointDto;
import hanyahunya.stock.dailyStock.Dto.StockChartResDto;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyStockServiceImpl implements DailyStockService {
    private final DailyStockRepository dailyStockRepository;

    @Override
    public ResponseDto<StockChartResDto> getStockChart(String StockCode) {
        List<StockChartPointDto> resultList = new ArrayList<>();
        dailyStockRepository.findByStockCodeWithJoin(StockCode).forEach(dailyStock -> {
            resultList.add(StockChartPointDto.entityToDto(dailyStock));
        });
        if (resultList.isEmpty()) {
            return ResponseDto.fail("該当する銘柄の株価データが見つかりませんでした");
        }
        return ResponseDto.success("株価チャート読み込みに成功しました", StockChartResDto.set(resultList));
    }
}
