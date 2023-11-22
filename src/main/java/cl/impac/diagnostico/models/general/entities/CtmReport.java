package cl.impac.diagnostico.models.general.entities;


import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "impac_ctm_reports")
public class CtmReport  {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String campo;
	private String address;
	private String status;
	private String agronomist;
	private String agronomistEmail;
	private String agronomistPhone;
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	@CreationTimestamp
	private Instant createdOn;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_report_id")
	private List<CtmReportMachine>  reportMachine;
	

}
