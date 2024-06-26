package ctu.cit.se.dam_service.daos.impls;

import ctu.cit.se.dam_service.daos.mappers.IMapper;
import ctu.cit.se.dam_service.daos.specs.IDamDAO;
import ctu.cit.se.dam_service.daos.specs.IDamTypeDAO;
import ctu.cit.se.dam_service.dtos.requests.dams.CreateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.dams.UpdateDamReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damtypes.CreateDamTypeReqDTO;
import ctu.cit.se.dam_service.dtos.requests.damtypes.UpdateDamTypeReqDTO;
import ctu.cit.se.dam_service.dtos.responses.commands.CommandResDTO;
import ctu.cit.se.dam_service.dtos.responses.dams.RetrieveDamResDTO;
import ctu.cit.se.dam_service.dtos.responses.damtypes.RetrieveDamTypeResDTO;
import ctu.cit.se.dam_service.entites.Dam;
import ctu.cit.se.dam_service.entites.DamType;
import ctu.cit.se.dam_service.exceptions.messages.CustomExceptionMessage;
import ctu.cit.se.dam_service.repositories.IDamRepository;
import ctu.cit.se.dam_service.repositories.IDamTypeRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DamTypeDAO implements IDamTypeDAO {
    @Autowired
    private IDamTypeRepository damTypeRepository;
    @Autowired
    private IDamRepository damRepository;
    @Autowired
    private IMapper<CreateDamTypeReqDTO, DamType> createMapper;
    @Autowired
    private IMapper<UpdateDamTypeReqDTO, DamType> updateMapper;
    @Autowired
    private IMapper<DamType, RetrieveDamTypeResDTO> retrieveMapper;
    @Autowired
    private EntityManager entityManager;

    @Override
    public CommandResDTO create(CreateDamTypeReqDTO createDamTypeReqDTO) {
        return CommandResDTO.builder().id(damTypeRepository.save(createMapper.convert(createDamTypeReqDTO)).getId().toString()).build();
    }

    @Override
    public CommandResDTO update(UpdateDamTypeReqDTO updateDamTypeReqDTO) {
        return CommandResDTO.builder().id(damTypeRepository.save(updateMapper.convert(updateDamTypeReqDTO)).getId().toString()).build();
    }

    @Override
    public List<RetrieveDamTypeResDTO> list() {
        return damTypeRepository.findAll().stream().map(damType -> retrieveMapper.convert(damType)).collect(Collectors.toList());
    }

    @Override
    public RetrieveDamTypeResDTO retrieve(UUID damTypeId) {
        var damType = damTypeRepository.findById(damTypeId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.DAM_TYPE_NOT_FOUND_BY_ID));
        return retrieveMapper.convert(damType);
    }

    @Override
    public void delete(UUID damTypeId) {
        var damType = damTypeRepository.findById(damTypeId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.DAM_TYPE_NOT_FOUND_BY_ID));
        var dams = damType.getDams();
        if (!dams.isEmpty()) {
            var defaultDamTypes = damTypeRepository.findByCode("init");
            if (!defaultDamTypes.isEmpty()) {
                var defaultDamType = defaultDamTypes.get(0);
                dams.stream().forEach(dam -> {
                    dam.setDamType(defaultDamType);
                    damRepository.save(dam);
                });
                damRepository.flush();
                damTypeRepository.flush();
            }
        }
        entityManager.clear();
        damType = damTypeRepository.findById(damTypeId).orElseThrow(() -> new IllegalArgumentException(CustomExceptionMessage.DAM_TYPE_NOT_FOUND_BY_ID));
        damTypeRepository.delete(damType);
    }

    @Override
    public void initData(List<CreateDamTypeReqDTO> createDamTypeReqDTOS) {
        createDamTypeReqDTOS.stream().forEach(createDamTypeReqDTO -> {
            var damType = createMapper.convert(createDamTypeReqDTO);
            damType.setCode("init");
            var damTypes = damTypeRepository.findAll();
            if (damTypes.isEmpty()) {
                damTypeRepository.save(damType);
            }
        });
    }
}
