package hanyahunya.stock.dailyStock;

import hanyahunya.stock.dailyStock.Dto.SaveStockDetailListDto;
import hanyahunya.stock.dailyStock.Dto.StockChartResDto;
import hanyahunya.stock.util.ResponseDto;

public interface DailyStockService {
    /**
     * 株価チャートを応答
     * @param StockCode 読み込みたい株のコード
     * @return List<StockChartPointDto>
     */
    ResponseDto<StockChartResDto> getStockChart(String StockCode);

    ResponseDto<Void> saveStockData(SaveStockDetailListDto saveStockDetailListDto);
}
