package com.daniel.quasar.core.dto;

import com.daniel.quasar.core.entity.Satelite;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RequestSatelites")
public class RequestSatelitesDTO {

    private List<SateliteDTO> satellites;

}
