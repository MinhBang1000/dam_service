package ctu.cit.se.dam_service.daos.specs;

import ctu.cit.se.dam_service.dtos.requests.dams.CreateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.dams.UpdateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damstatuses.CreateDamStatusReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damstatuses.UpdateDamStatusReqDTO;
import ctu.cit.se.dam_service.dtos.responses.commands.CommandResDTO;
import ctu.cit.se.dam_service.dtos.responses.dams.RetrieveDamResDTO;
import ctu.cit.se.dam_service.dtos.responses.damstatuses.RetrieveDamStatusResDTO;
import ctu.cit.se.dam_service.dtos.responses.damtypes.RetrieveDamTypeResDTO;

import java.util.List;
import java.util.UUID;

public interface IDamStatusDAO {
    CommandResDTO create(CreateDamStatusReqDTO createDamStatusReqDTO);
    CommandResDTO update(UpdateDamStatusReqDTO updateDamStatusReqDTO);
    List<RetrieveDamStatusResDTO> list();
    RetrieveDamStatusResDTO retrieve(UUID damStatusId);
    void delete(UUID damStatusId);
}
