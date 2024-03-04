package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.ClientRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;

public interface ClientService {

    void registration(ClientRequestDto userRequestDto) throws KaspiApiTokenInvalidException;

    Object getUser() throws UserNotFoundException;

    void updateUser(ClientRequestDto userRequestDto) throws UserNotFoundException;

    void deleteUser() throws UserNotFoundException;

}
