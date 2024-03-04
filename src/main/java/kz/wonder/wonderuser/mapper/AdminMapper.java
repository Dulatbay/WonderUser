package kz.wonder.wonderuser.mapper;

import kz.wonder.wonderuser.dto.response.AdminResponseDto;
import kz.wonder.wonderuser.dto.response.SellerResponseDto;
import kz.wonder.wonderuser.entity.Seller;
import kz.wonder.wonderuser.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper extends Mappable<User, AdminResponseDto> {
}
