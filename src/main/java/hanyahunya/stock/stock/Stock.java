package hanyahunya.stock.stock;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

    @Id
    @Column(name = "stock_code", length = 6)
    private String stockCode;

    @Column(name = "stock_name", nullable = false, length = 32)
    private String stockName;
}