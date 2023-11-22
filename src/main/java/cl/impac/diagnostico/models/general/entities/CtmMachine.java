package cl.impac.diagnostico.models.general.entities;

import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "impac_customers_machines", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "customer_id", "single_registration" }) })
public class CtmMachine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "single_registration")
	private String singleRegistration;
	private String model;
	private String mark;
	private float capacityTask;
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
	private boolean hidroConst;
	private String pressureGauge;
	private String barra;
	private String numAgitators;
	@Column(length = 20)
	private String numberInvoice;
	private String supplierInvoice;
	private boolean suctionDeflector;
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	@CreationTimestamp
	private Instant createdOn;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Customer customer;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = true)
	private CatMachine category;

}
