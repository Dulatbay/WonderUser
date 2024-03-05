package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.Storekeeper;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.exceptions.KaspiApiTokenInvalidException;
import kz.wonder.wonderuser.exceptions.UserAlreadyExistAuthenticationException;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.mapper.SellerMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final UserRepository userRepository;
    private final SellerMapper sellerMapper;

    @Override
    public void registration(SellerRequestDto sellerRequestDto) throws KaspiApiTokenInvalidException {
        if(userRepository.findByEmail(sellerRequestDto.getEmail()).isPresent()) throw new UserAlreadyExistAuthenticationException("User is already exists with this email");
        User user =  User.builder()
                .firstname(sellerRequestDto.getFirstname())
                .lastname(sellerRequestDto.getLastname())
                .email(sellerRequestDto.getEmail())
                .phoneNumber(sellerRequestDto.getPhoneNumber())
                .role(Role.SELLER)
                .build();

        boolean tokenIsActive = kaspiProxy.getStatus(sellerRequestDto.getApiToken());
        if(tokenIsActive) {
            ((Seller) user).setApiToken(sellerRequestDto.getApiToken());
             userRepository.save(user);
        }else{
            throw new KaspiApiTokenInvalidException("Api token is invalid");
        }
    }

    @Override
    public Object get() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getRole() == Role.SELLER) {
            return sellerMapper.toDto((Seller) user);
        }else{
            throw new UserNotFoundException("User not found");
        }
    }

    @Override
    public void update(SellerRequestDto sellerRequestDto) throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        Seller user = (Seller) userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setFirstname(sellerRequestDto.getFirstname());
        user.setEmail(sellerRequestDto.getEmail());
        user.setLastname(sellerRequestDto.getLastname());
        user.setPhoneNumber(sellerRequestDto.getPhoneNumber());
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
