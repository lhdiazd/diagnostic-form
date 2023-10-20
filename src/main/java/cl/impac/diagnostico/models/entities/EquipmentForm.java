package cl.impac.diagnostico.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "equipment_form"/*, uniqueConstraints = @UniqueConstraint(columnNames = "base_category_id")*/)
public class EquipmentForm {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	@NotNull	
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "base_category_id", nullable= false)
	private BaseCategory baseCategory;
	@NotBlank
	private String name;
	@JsonManagedReference	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipmentForm")
	private List<DiagnosticQuestion> diagnosticQuestion;
}
