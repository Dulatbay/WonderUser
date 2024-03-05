package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;

public interface AdminService {

    void registration(AdminRequestDto userRequestDto) throws KaspiApiTokenInvalidException;

    Object getUser() throws UserNotFoundException;

    void updateUser(AdminRequestDto userRequestDto) throws UserNotFoundException;

    void deleteUser() throws UserNotFoundException;
}
