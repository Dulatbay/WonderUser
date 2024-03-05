package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.StorekeeperRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.service.StorekeeperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/storekeeper")
public class StorekeeperController {

    private final StorekeeperService storekeeperService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() throws UserNotFoundException {
        return ResponseEntity.ok(storekeeperService.get());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody StorekeeperRequestDto storekeeperRequestDto) throws KaspiApiTokenInvalidException {
        storekeeperService.registration(storekeeperRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody StorekeeperRequestDto storekeeperRequestDto) throws UserNotFoundException {
        storekeeperService.update(storekeeperRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
