package hanyahunya.stock.userStocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserStockId implements Serializable {
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;
    @Column(name = "stock_code", length = 6)
    private String stockCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStockId)) return false;
        UserStockId that = (UserStockId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(stockCode, that.stockCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, stockCode);
    }
}
