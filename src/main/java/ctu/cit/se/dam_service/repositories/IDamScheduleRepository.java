package ctu.cit.se.dam_service.repositories;

import ctu.cit.se.dam_service.entites.DamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IDamScheduleRepository extends JpaRepository<DamSchedule, UUID> {
}
