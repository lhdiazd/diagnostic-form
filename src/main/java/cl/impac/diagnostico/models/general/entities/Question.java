package cl.impac.diagnostico.models.general.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "impac_forms_questions", uniqueConstraints = { @UniqueConstraint(columnNames = { "forms_id", "question" }) })
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 150, nullable = false)
	private String question;
	private  short 	orden;



}
