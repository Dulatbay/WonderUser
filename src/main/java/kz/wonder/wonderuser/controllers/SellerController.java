package kz.wonder.wonderuser.controllers;

import kz.wonder.wonderuser.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final UserService userService;


}
