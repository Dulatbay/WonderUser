package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.ClientRequestDto;
import kz.wonder.wonderuser.dto.response.SellerResponseDto;
import kz.wonder.wonderuser.dto.response.StorekeeperResponseDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.Storekeeper;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;

import java.util.List;
//Менеджер
public interface ClientService {

    void registration(ClientRequestDto userRequestDto) throws KaspiApiTokenInvalidException;

    Object get() throws UserNotFoundException;

    void update(ClientRequestDto userRequestDto) throws UserNotFoundException;

    void delete() throws UserNotFoundException;

    List<User> getAllUsers();

    List<SellerResponseDto> getAllSellers();

    List<StorekeeperResponseDto> getAllStorekeepers();

    List<User> getAllAdmins();

    Object getUserById(Integer id);


}
