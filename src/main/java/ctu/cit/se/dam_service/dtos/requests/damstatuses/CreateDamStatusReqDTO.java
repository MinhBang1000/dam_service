package ctu.cit.se.dam_service.dtos.requests.damstatuses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateDamStatusReqDTO {

    @NotBlank(message = "The dam status name should not be blank")
    @JsonProperty("damStatusName")
    private String name;
}
