package kz.wonder.wonderuser.mapper;

import kz.wonder.wonderuser.dto.response.ClientResponseDto;
import kz.wonder.wonderuser.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper extends Mappable<User, ClientResponseDto>{
}
