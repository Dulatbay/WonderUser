package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.dto.request.StorekeeperRequestDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.Storekeeper;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserAlreadyExistAuthenticationException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.mapper.StorekeeperMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.StorekeeperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorekeeperServiceImpl implements StorekeeperService {

    private final UserRepository userRepository;
    private final StorekeeperMapper storekeeperMapper;


    @Override
    public void registration(StorekeeperRequestDto storekeeperRequestDto) throws KaspiApiTokenInvalidException {
        if(userRepository.findByEmail(storekeeperRequestDto.getEmail()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");

        User user =  User.builder()
                .firstname(storekeeperRequestDto.getFirstname())
                .lastname(storekeeperRequestDto.getLastname())
                .email(storekeeperRequestDto.getEmail())
                .phoneNumber(storekeeperRequestDto.getPhoneNumber())
                .role(Role.STOREKEEPER)
                .build();

        userRepository.save(user);
    }

    @Override
    public Object get() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getRole() == Role.STOREKEEPER) {
            return storekeeperMapper.toDto((Storekeeper) user);
        }else{
            throw new UserNotFoundException("User not found");
        }    }

    @Override
    public void update(StorekeeperRequestDto storekeeperRequestDto) throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        Seller user = (Seller) userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFirstname(storekeeperRequestDto.getFirstname());
        user.setEmail(storekeeperRequestDto.getEmail());
        user.setLastname(storekeeperRequestDto.getLastname());
        user.setPhoneNumber(storekeeperRequestDto.getPhoneNumber());
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
}
