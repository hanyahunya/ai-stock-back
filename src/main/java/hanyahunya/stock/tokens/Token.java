package hanyahunya.stock.tokens;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @Column(name = "token_id", columnDefinition = "CHAR(36")
    private String tokenId;

    @Column(name = "access_token", length = 128)
    private String accessToken;

    @Column(name = "refresh_token", length = 128)
    private String refreshToken;

    @Column(name = "access_token_expiry", columnDefinition = "TIMESTAMP")
    private LocalDateTime accessTokenExpiry;

    @Column(name = "refresh_token_expiry", columnDefinition = "TIMESTAMP")
    private LocalDateTime refreshTokenExpiry;
}
