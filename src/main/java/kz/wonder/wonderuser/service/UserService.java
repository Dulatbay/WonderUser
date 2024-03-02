package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registration(SellerRequestDto sellerRequestDto);

    Object getUser() throws UserNotFoundException;

    void updateUser(SellerRequestDto sellerRequestDto) throws UserNotFoundException;

    void deleteUser() throws UserNotFoundException;


}
