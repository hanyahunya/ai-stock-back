package hanyahunya.stock.users;

import hanyahunya.stock.users.dto.JoinUserDto;
import hanyahunya.stock.util.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
