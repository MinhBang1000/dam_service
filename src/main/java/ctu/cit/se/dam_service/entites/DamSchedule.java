package ctu.cit.se.dam_service.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dam_schedules")
public class DamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "dam_schedule_begin_at")
    private LocalDateTime beginAt;
    @Column(name = "dam_schedule_end_at")
    private LocalDateTime endAt;
    @Column(name = "dam_schedule_description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "dam_id")
    private Dam dam;
    @ManyToOne
    @JoinColumn(name = "dam_status_id")
    private DamStatus damStatus;
}
