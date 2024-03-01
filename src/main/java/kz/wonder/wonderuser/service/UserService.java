package kz.wonder.wonderuser.service;

import kz.wonder.wonderuser.dto.request.SellerRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registration(SellerRequestDto userRequestDto);

    Object getUser(String token);
}
