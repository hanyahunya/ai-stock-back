package hanyahunya.stock.dailyStock.Dto;

import hanyahunya.stock.dailyStock.DailyStock;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "set")
public class SaveStockDetailListDto {
    private final List<SaveStockDetailDto> saveStockDetailDtos;

    public List<DailyStock> toEntity() {
        List<DailyStock> dailyStockList = new ArrayList<>();
        for (SaveStockDetailDto saveStockDetailDto : saveStockDetailDtos) {
            dailyStockList.add(saveStockDetailDto.toEntity());
        }
        return dailyStockList;
    }
}
