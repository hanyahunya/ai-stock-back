package hanyahunya.stock.dailyStock.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(staticName = "set")
public class StockChartResDto {
    List<StockChartPointDto> stockChartPoints;
}
