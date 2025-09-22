package hanyahunya.stock.userStocks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor(staticName = "set")
public class UserStockListDto {
    private List<UserStockDto> userStocks;
}
