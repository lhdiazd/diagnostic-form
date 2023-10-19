package cl.impac.diagnostico.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "diagnostic_question", uniqueConstraints = {@UniqueConstraint(columnNames = {"equipment_form_id", "detalle"})})
public class DiagnosticQuestion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	@NotNull	
	@ManyToOne
	@JoinColumn(name = "equipment_form_id", nullable = false)
	private EquipmentForm equipmentForm;
	@NotBlank
	@Column(nullable = false)
	private String detalle;
}
