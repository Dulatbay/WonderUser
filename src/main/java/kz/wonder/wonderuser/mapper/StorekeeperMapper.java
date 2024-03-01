package kz.wonder.wonderuser.mapper;

import kz.wonder.wonderuser.dto.response.SellerResponseDto;
import kz.wonder.wonderuser.dto.response.StorekeeperResponseDto;
import kz.wonder.wonderuser.entity.Storekeeper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StorekeeperMapper extends Mappable<Storekeeper, StorekeeperResponseDto> {
}
