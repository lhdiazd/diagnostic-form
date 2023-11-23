package cl.impac.diagnostico.dto;

import java.util.List;

import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EquipmentFormDTO {
	private Long equipmentFormId;
    private String name;
    private List<BaseCategory> baseCategories;
    private List<DiagnosticQuestion> questions;
}
