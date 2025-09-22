package hanyahunya.stock.userStocks.dto;

import hanyahunya.stock.userStocks.entity.UserStock;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class UserStockDto {
    private String userId; // 今後トークンに変える予定
    private String stockCode;

    public static UserStockDto toDto(UserStock userStock) {
        return UserStockDto.builder()
                .userId(userStock.getUser().getUserId())
                .stockCode(userStock.getStock().getStockCode())
                .build();
    }
}
