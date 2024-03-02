package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.Storekeeper;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.exceptions.UserNotFoundException;
import kz.wonder.wonderuser.mapper.SellerMapper;
import kz.wonder.wonderuser.mapper.StorekeeperMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerMapper sellerMapper;
    private final StorekeeperMapper storekeeperMapper;

    @Override
    public void registration(SellerRequestDto userRequestDto) {
        User user =  User.builder()
                .firstname(userRequestDto.getFirstname())
                .lastname(userRequestDto.getLastname())
                .email(userRequestDto.getEmail())
                .build();
        ((Seller) user).setApiToken(userRequestDto.getApiToken());
    }

    @Override
    public Object getUser() throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if(user.getRole() == Role.SELLER){
            return sellerMapper.toDto((Seller) user);
        }else if( user.getRole() == Role.STOREKEEPER){
            return storekeeperMapper.toDto((Storekeeper) user);
        }else{

        }

        return null;
    }

    @Override
    public void updateUser(SellerRequestDto sellerRequestDto) throws UserNotFoundException {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");

        User user = userRepository.findByKeycloakUserId(keycloakUserID)
                .orElseThrow(() -> new UserNotFoundException("User not found"));


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
