package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.dto.request.StorekeeperRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;

public interface StorekeeperService {

    void registration(StorekeeperRequestDto userRequestDto) throws KaspiApiTokenInvalidException;

    Object get() throws UserNotFoundException;

    void update(StorekeeperRequestDto userRequestDto) throws UserNotFoundException;

    void delete() throws UserNotFoundException;

}
