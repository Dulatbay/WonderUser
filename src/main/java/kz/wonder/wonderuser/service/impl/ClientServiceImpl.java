package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.ClientRequestDto;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserAlreadyExistAuthenticationException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.mapper.ClientMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final UserRepository userRepository;
    private final ClientMapper clientMapper;

    @Override
    public void registration(ClientRequestDto userRequestDto) throws KaspiApiTokenInvalidException {
        if(userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");

        User user =  User.builder()
                .firstname(userRequestDto.getFirstname())
                .lastname(userRequestDto.getLastname())
                .email(userRequestDto.getEmail())
                .phoneNumber(userRequestDto.getPhoneNumber())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
    }

    @Override
    public Object getUser() throws UserNotFoundException {
        return null;
    }

    @Override
    public void updateUser(ClientRequestDto userRequestDto) throws UserNotFoundException {

    }

    @Override
    public void deleteUser() throws UserNotFoundException {

    }
}
