package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/profile")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUser(@RequestHeader String token){
        return ResponseEntity.ok(userService.getUser(token));
    }

}
