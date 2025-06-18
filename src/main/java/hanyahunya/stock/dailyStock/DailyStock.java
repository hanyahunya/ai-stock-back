package hanyahunya.stock.dailyStock;

import hanyahunya.stock.stock.Stock;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_stock", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stock_code", "date"})
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

    @Column(name = "price_change_rate", nullable = false) //　騰落率
    private Double priceChangeRate;

    @Column(name = "volume", nullable = false) // 出来高
    private Integer volume;

//    @Column(name = "credit_ratio", nullable = false) // 信用倍率
//    private Double creditRatio;

    @Column(name = "individual", nullable = false) // 個人
    private Integer individual;

    @Column(name = "fore", nullable = false) // 外国人
    private Integer fore;

    @Column(name = "institution", nullable = false) // 企業
    private Integer institution;

    @Column(name = "program", nullable = false) // プログラム
    private Integer program;

    @Column(name = "credit_remain_ratio", nullable = false) // 信用残高率 *뺄예정
    private Double creditRemainRatio;

//    @Column(name = "shrts_qty", nullable = false) // 空売り
//    private Integer shrtsQty;

    @Column(name = "ovr_shrts_qty", nullable = false) // 累積空売り
    private Integer ovrShrtsQty;

    @Column(name = "trde_wght", nullable = false) // 空売り売買比率
    private Double trdeWght;

    @Column(name = "shrts_trde_price", nullable = false, length = 20) // 空売り売買代金
    private String shrtsTrdePrice;

    @Column(name = "shrts_avg_pric", nullable = false) // 空売り平均価格
    private Integer shrtsAvgPric;
}