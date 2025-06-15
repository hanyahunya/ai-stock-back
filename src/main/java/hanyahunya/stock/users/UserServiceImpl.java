package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.util.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseDto<Void> join(JoinUserDto joinUserDto) {
        joinUserDto.setUserId(UUID.randomUUID().toString());
        User savedUser = userRepository.save(joinUserDto.toEntity());
        return null;
    }
}
