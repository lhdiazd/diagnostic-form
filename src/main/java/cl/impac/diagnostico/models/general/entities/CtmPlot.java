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
@Table(name = "impac_customers_plots", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "customer_id", "single_registration" }) })
public class CtmPlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "single_registration")
	private String singleRegistration;
	private String address;
	private String region;
	private String comuna;
	private float latitude;
	private float longitude;
	@UpdateTimestamp
	private Instant lastUpdatedOn;
	@CreationTimestamp
	private Instant createdOn;
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	private Customer customer;

}
