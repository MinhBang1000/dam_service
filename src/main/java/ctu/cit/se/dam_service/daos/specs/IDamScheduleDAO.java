package ctu.cit.se.dam_service.daos.specs;

import ctu.cit.se.dam_service.dtos.requests.dams.CreateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.dams.UpdateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damschedules.CreateDamScheduleReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damschedules.UpdateDamScheduleReqDTO;
import ctu.cit.se.dam_service.dtos.responses.dams.RetrieveDamResDTO;
import ctu.cit.se.dam_service.dtos.responses.damschedules.RetrieveDamScheduleResDTO;

import java.util.List;
import java.util.UUID;

public interface IDamScheduleDAO {
    void create(CreateDamScheduleReqDTO createDamScheduleReqDTO);
    void update(UpdateDamScheduleReqDTO updateDamScheduleReqDTO);
    List<RetrieveDamScheduleResDTO> list();
    RetrieveDamScheduleResDTO retrieve(UUID damScheduleId);
    void delete(UUID damScheduleId);
}