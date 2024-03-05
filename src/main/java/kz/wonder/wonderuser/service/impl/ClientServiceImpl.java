package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.ClientRequestDto;
import kz.wonder.wonderuser.dto.response.SellerResponseDto;
import kz.wonder.wonderuser.dto.response.StorekeeperResponseDto;
import kz.wonder.wonderuser.entity.Seller;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Object get() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getRole() == Role.SELLER) {
            return clientMapper.toDto(user);
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void update(ClientRequestDto userRequestDto) throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFirstname(userRequestDto.getFirstname());
        user.setEmail(userRequestDto.getEmail());
        user.setLastname(userRequestDto.getLastname());
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public void delete() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<SellerResponseDto> getAllSellers() {
        return null;
    }

    @Override
    public List<StorekeeperResponseDto> getAllStorekeepers() {
        return null;
    }

    @Override
    public List<User> getAllAdmins() {
        return null;
    }

    @Override
    public Object getUserById(Integer id) {
        return null;
    }
}
