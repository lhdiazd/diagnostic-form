package cl.impac.diagnostico.models.general.entities;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

@Table(name = "impac_ctm_reports_machines", uniqueConstraints = {@UniqueConstraint(columnNames = { "customer_report_id", "machine_id" }) })
public class CtmReportMachine {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "machine_id")
	private Long machineId;
	private String singleRegistration;
	private String model;
	private String mark;
	private float capacityTask;
	private Long categoryId;
	private String groupAir;
	private String pumpMark;
	private String pumpModel;
	private float pumpPressure;
	private float pumpFlow;
	private String regulatorMark;
	private String regulatorModel;
	private float regulatorPressure;
	private float regulatorFlow;
	private boolean lineFilter;
	private String pressureGauge;
	private String barra;
	private String numAgitators;
	private String numberInvoice;
	private String supplierInvoice;
	private boolean suctionDeflector;
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	@CreationTimestamp
	private Instant createdOn;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reports_machines_id")
	private List<CtmReportMachineForm>  answers;
	
	

	


}
