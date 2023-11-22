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
@Table(name = "impac_ctm_reports_machines_forms", uniqueConstraints = { @UniqueConstraint(columnNames = { "reports_machines_id", "form_id","question_id"}) })
public class CtmReportMachineForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "form_id")
	private  Long formId;
	@Column(name = "question_id")
	private  Long questionId;
	private String question;
	private  boolean answer;
    @Column(columnDefinition = "boolean default false")
	private boolean answerStatus;
	private  int punctuation;
    @Column(columnDefinition = "boolean default false")
	private boolean punctuationStatus;
	private int frequency;
    @Column(columnDefinition = "boolean default false")
	private boolean frequencyStatus;
	
	


}
