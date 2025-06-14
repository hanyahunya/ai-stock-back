package hanyahunya.stock.stock.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stock_id", "date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_stock_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_code", nullable = false)
    private Stock stock;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "open_price", nullable = false)
    private Integer openPrice;

    @Column(name = "high_price", nullable = false)
    private Integer highPrice;

    @Column(name = "low_price", nullable = false)
    private Integer lowPrice;

    @Column(name = "close_price", nullable = false)
    private Integer closePrice;

    @Column(name = "price_change_rate", nullable = false)
    private Double priceChangeRate;

    @Column(name = "volume", nullable = false)
    private Integer volume;

    @Column(name = "credit_ratio", nullable = false)
    private Double creditRatio;

    @Column(name = "individual", nullable = false)
    private Integer individual;

    @Column(name = "foreign", nullable = false)
    private Integer foreign;

    @Column(name = "institution", nullable = false)
    private Integer institution;

    @Column(name = "program", nullable = false)
    private Integer program;

    @Column(name = "credit_remain_ratio", nullable = false)
    private Double creditRemainRatio;

    @Column(name = "shrts_qty", nullable = false)
    private Integer shrtsQty;

    @Column(name = "ovr_shrts_qty", nullable = false)
    private Integer ovrShrtsQty;

    @Column(name = "trde_wght", nullable = false)
    private Double trdeWght;

    @Column(name = "shrts_trde_prica", nullable = false, length = 20)
    private String shrtsTrdePrica;

    @Column(name = "shrts_avg_pric", nullable = false)
    private Integer shrtsAvgPric;
}