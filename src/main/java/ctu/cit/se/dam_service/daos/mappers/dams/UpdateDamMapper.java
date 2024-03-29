package ctu.cit.se.dam_service.daos.mappers.dams;

import ctu.cit.se.dam_service.daos.mappers.IMapper;
import ctu.cit.se.dam_service.dtos.requests.dams.UpdateDamReqDTO;
import ctu.cit.se.dam_service.entites.Dam;
import ctu.cit.se.dam_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.dam_service.repositories.IDamRepository;
import ctu.cit.se.dam_service.repositories.IDamTypeRepository;
import ctu.cit.se.dam_service.repositories.IRiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UpdateDamMapper implements IMapper<UpdateDamReqDTO, Dam> {
    @Autowired
    private IDamTypeRepository damTypeRepository;
    @Autowired
    private IRiverRepository riverRepository;
    @Autowired
    private IDamRepository damRepository;
    @Override
    public Dam convert(UpdateDamReqDTO source) {
        var dam = damRepository.findById(UUID.fromString(source.getId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.DAM_NOT_FOUND_BY_ID));
        var river = Objects.isNull(source.getRiverId()) ? dam.getRiver() : riverRepository.findById(UUID.fromString(source.getRiverId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.RIVER_NOT_FOUND_BY_ID));
        var damType = Objects.isNull(source.getDamTypeId()) ? dam.getDamType() : damTypeRepository.findById(UUID.fromString(source.getDamTypeId())).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.DAM_TYPE_NOT_FOUND_BY_ID));
        return Dam.builder()
                .id(dam.getId())
                .name(Objects.isNull(source.getName()) ? dam.getName() : source.getName())
                .constructedAt(Objects.isNull(source.getConstructedAt()) ? dam.getConstructedAt() : source.getConstructedAt())
                .height(Objects.isNull(source.getHeight()) ? dam.getHeight() : source.getHeight())
                .damType(damType)
                .river(river)
                .description(Objects.isNull(source.getDescription()) ? dam.getDescription() : source.getDescription())
                .capacity(Objects.isNull(source.getCapacity()) ? dam.getCapacity() : source.getCapacity())
                .longitude(Objects.isNull(source.getLongitude()) ? dam.getLongitude() : source.getLongitude())
                .latitude(Objects.isNull(source.getLatitude()) ? dam.getLatitude() : source.getLatitude())
                .build();
    }
}
