package kz.wonder.wonderuser.mappers;

import java.util.List;


// E - entity, RQD - request dto, RSD - response dto
public interface Mappable<E, RQD, RSD> {

    RSD toResponseDto(E entity);

    List<RSD> toDtoList(List<E> entityList);

    E toEntityFromRequest(RQD dto);
}
