package cl.impac.diagnostico.dto;

import java.util.List;

import cl.impac.diagnostico.models.entities.EquipmentForm;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseCategoryDTO {
	private Long id;
	private String name;
	private List<EquipmentForm> equipmentForms;
}	
