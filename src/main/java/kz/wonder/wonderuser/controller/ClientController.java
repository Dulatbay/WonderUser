package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.dto.request.ClientRequestDto;
import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/client")//Менеджер
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/get")
    public ResponseEntity<?> getUser() throws UserNotFoundException {
        return ResponseEntity.ok(clientService.get());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody ClientRequestDto clientRequestDto) throws KaspiApiTokenInvalidException {
        clientService.registration(clientRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClientRequestDto clientRequestDto) throws UserNotFoundException {
        clientService.update(clientRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(clientService.getAllUsers());

    }


}
