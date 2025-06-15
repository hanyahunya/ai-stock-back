package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.util.ResponseDto;

public interface UserService {
    ResponseDto<Void> join(JoinUserDto joinUserDto);
}
