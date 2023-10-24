package cl.impac.diagnostico.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiagnosticQuestionDTO {
	private Long id;
	private Long equipmentFormId;
	private String detalle;
}
