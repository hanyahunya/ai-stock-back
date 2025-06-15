package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.util.ResponseDto;
import hanyahunya.stock.util.service.EncodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EncodeService encodeService;

    @Override
    public ResponseDto<Void> join(JoinUserDto joinUserDto) {
        joinUserDto.setUserId(UUID.randomUUID().toString());
        try {
            joinUserDto.setPassword(encodeService.encode(joinUserDto.getPassword()));
            userRepository.save(joinUserDto.toEntity());
            return ResponseDto.success("会員登録に成功しました");
        } catch (Exception e) {
            return ResponseDto.fail("会員登録に失敗しました");
        }
    }
}
