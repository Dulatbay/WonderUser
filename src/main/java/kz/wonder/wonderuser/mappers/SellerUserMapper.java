package kz.wonder.wonderuser.mappers;

import kz.wonder.wonderuser.entities.SellerUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerUserMapper extends Mappable<SellerUser> {
}
