package kz.wonder.wonderuser.services.impl;

import kz.wonder.wonderuser.mappers.SellerUserMapper;
import kz.wonder.wonderuser.repositories.SellerUserRepository;
import kz.wonder.wonderuser.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SellerUserRepository sellerUserRepository;
    private final SellerUserMapper sellerUserMapper;


}
