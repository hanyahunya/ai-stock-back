package hanyahunya.stock.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hanyahunya.stock.users.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinUserDto {
    @JsonIgnore
    private String userId;
    @NotNull
    private String loginId;
    @NotNull
    private String password;

    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .loginId(this.loginId)
                .password(this.password)
                .build();
    }
}
