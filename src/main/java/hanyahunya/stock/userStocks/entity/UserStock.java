package hanyahunya.stock.userStocks.entity;

import hanyahunya.stock.stock.Stock;
import hanyahunya.stock.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_stocks")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStock {
    @EmbeddedId
    private UserStockId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(
                    name = "fk_user-stocks_user",
                    foreignKeyDefinition = "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE"
            )
    )
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("stockCode")
    @JoinColumn(
            name = "stock_code",
            foreignKey = @ForeignKey(
                    name = "fk_user-stocks_stock",
                    foreignKeyDefinition = "FOREIGN KEY (stock_code) REFERENCES stocks(stock_code) ON DELETE CASCADE"
            )
    )
    private Stock stock;
}
