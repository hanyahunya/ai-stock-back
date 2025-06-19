package hanyahunya.stock.userStocks.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserStockId implements Serializable {
    @Column(name = "user_id", columnDefinition = "CHAR(36)")
    private String userId;
    @Column(name = "stock_code", length = 6)
    private String stockCode;
}
