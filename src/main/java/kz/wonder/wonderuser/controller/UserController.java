package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser());
    }

    @PostMapping("/register")
    public ResponseEntity<?> getUser(@RequestBody SellerRequestDto sellerRequestDto) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser());
    }


}
