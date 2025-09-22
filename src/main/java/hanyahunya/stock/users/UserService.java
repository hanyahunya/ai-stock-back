package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.users.dto.UserInfoDto;
import hanyahunya.stock.util.ResponseDto;

public interface UserService {
    ResponseDto<Void> join(JoinUserDto joinUserDto);

    ResponseDto<UserInfoDto> getUserInfo(String userId);
}
