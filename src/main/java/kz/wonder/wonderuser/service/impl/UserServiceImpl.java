package kz.wonder.wonderuser.service.impl;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.Storekeeper;
import kz.wonder.wonderuser.entity.User;
import kz.wonder.wonderuser.enums.Role;
import kz.wonder.wonderuser.mapper.SellerMapper;
import kz.wonder.wonderuser.mapper.StorekeeperMapper;
import kz.wonder.wonderuser.repository.UserRepository;
import kz.wonder.wonderuser.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerMapper sellerMapper;
    private final StorekeeperMapper storekeeperMapper

    @Override
    public void registration(SellerRequestDto userRequestDto) {

    }

    @Override
    public Object getUser(String token) {
        var token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserID = token.getToken().getClaim("user_id");
        User user = userRepository.findByKeycloakUserId(keycloakUserID);
        if(user.getRole() == Role.SELLER){
            return sellerMapper.toDto((Seller) user);
        }else if( user.getRole() == Role.STOREKEEPER){
            return storekeeperMapper.toDto((Storekeeper) user);
        }else{

        }

        return null;
    }

}
