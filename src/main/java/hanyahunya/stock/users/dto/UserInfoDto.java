package hanyahunya.stock.users.dto;

import hanyahunya.stock.users.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {
    private String userId;
    private String loginId;

    public static UserInfoDto toDto(User user) {
        return UserInfoDto.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .build();
    }
}
