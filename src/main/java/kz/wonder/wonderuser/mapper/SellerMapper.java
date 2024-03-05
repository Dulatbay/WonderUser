package kz.wonder.wonderuser.mapper;

import kz.wonder.wonderuser.dto.response.SellerResponseDto;
import kz.wonder.wonderuser.entity.Seller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerMapper extends Mappable<Seller, SellerResponseDto> {
}
