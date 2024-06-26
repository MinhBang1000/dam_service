package ctu.cit.se.dam_service.repositories;

import ctu.cit.se.dam_service.entites.DamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IDamTypeRepository extends JpaRepository<DamType, UUID> {
    List<DamType> findByCode(String code);
}
