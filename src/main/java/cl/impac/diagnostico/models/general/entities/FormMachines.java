package cl.impac.diagnostico.models.general.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cl.impac.diagnostico.models.entities.BaseCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "impac_forms")
public class FormMachines {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false)
	private String name;
	private String description;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "forms_id")
	private List<Question> questions;
	@JoinTable(name = "impac_categories_machines_form_machines")
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JsonIgnore
	private List<CatMachine> categories;
  

}
