package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.users.dto.UserInfoDto;
import hanyahunya.stock.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static hanyahunya.stock.util.ResponseUtil.toResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDto<Void>> addUser(@RequestBody @Valid JoinUserDto reqDto) {
        return toResponse(userService.join(reqDto));
    }

    @GetMapping("/{loginId}")
    public ResponseEntity<ResponseDto<UserInfoDto>> getUserTest(@PathVariable String loginId) {
        return toResponse(userService.getUserInfo(loginId));
    }
}
