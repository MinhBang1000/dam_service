package ctu.cit.se.dam_service.daos.specs;

import ctu.cit.se.dam_service.dtos.requests.damschedules.CreateDamScheduleReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damschedules.UpdateDamScheduleReqDTO;
import ctu.cit.se.dam_service.dtos.responses.commands.CommandResDTO;
import ctu.cit.se.dam_service.dtos.responses.damschedules.RetrieveDamScheduleBySelectedDateResDTO;
import ctu.cit.se.dam_service.dtos.responses.damschedules.RetrieveDamScheduleResDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IDamScheduleDAO {
    CommandResDTO create(CreateDamScheduleReqDTO createDamScheduleReqDTO);
    CommandResDTO update(UpdateDamScheduleReqDTO updateDamScheduleReqDTO);
    List<RetrieveDamScheduleResDTO> list();
    List<RetrieveDamScheduleBySelectedDateResDTO> listBySelectedDate(LocalDate selectedDate);
    RetrieveDamScheduleResDTO retrieve(UUID damScheduleId);
    void delete(UUID damScheduleId);
}
