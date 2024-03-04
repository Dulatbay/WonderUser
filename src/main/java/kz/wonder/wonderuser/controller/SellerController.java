package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/seller")
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() throws UserNotFoundException {
        return ResponseEntity.ok(sellerService.get());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SellerRequestDto sellerRequestDto) throws KaspiApiTokenInvalidException {
        sellerService.registration(sellerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody SellerRequestDto sellerRequestDto) throws UserNotFoundException {
        sellerService.update(sellerRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
