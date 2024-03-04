package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.AdminRequestDto;
import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserAlreadyExistAuthenticationException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.mapper.AdminMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final AdminMapper adminMapper;

    @Override
    public void registration(AdminRequestDto adminRequestDto) throws KaspiApiTokenInvalidException {
        if(userRepository.findByEmail(adminRequestDto.getEmail()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");

        User user =  User.builder()
                .firstname(adminRequestDto.getFirstname())
                .lastname(adminRequestDto.getLastname())
                .email(adminRequestDto.getEmail())
                .phoneNumber(adminRequestDto.getPhoneNumber())
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
    }

    @Override
    public Object getUser() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getRole() == Role.ADMIN) {
            return adminMapper.toDto(user);
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void updateUser(AdminRequestDto adminRequestDto) throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        Seller user = (Seller) userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFirstname(adminRequestDto.getFirstname());
        user.setEmail(adminRequestDto.getEmail());
        user.setLastname(adminRequestDto.getLastname());
        user.setPhoneNumber(adminRequestDto.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public void deleteUser() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }
}
