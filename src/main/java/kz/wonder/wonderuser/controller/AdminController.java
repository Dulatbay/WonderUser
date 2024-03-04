package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() throws UserNotFoundException {
        return ResponseEntity.ok(adminService.getUser());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AdminRequestDto adminRequestDto) throws KaspiApiTokenInvalidException {
        adminService.registration(adminRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody AdminRequestDto adminRequestDto) throws UserNotFoundException {
        adminService.updateUser(adminRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
