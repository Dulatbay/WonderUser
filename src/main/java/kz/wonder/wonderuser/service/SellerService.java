package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

public interface SellerService {

    void registration(SellerRequestDto userRequestDto) throws KaspiApiTokenInvalidException;

    Object get() throws UserNotFoundException;

    void update(SellerRequestDto userRequestDto) throws UserNotFoundException;

    void delete() throws UserNotFoundException;

}
