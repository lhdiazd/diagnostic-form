package cl.impac.diagnostico.general.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerReportDto {
	private Long id;
	private String campo;
	private String address;
	private String status;
	private String agronomist;
	private String agronomistEmail;
	private String agronomistPhone;
	private Long idCustomer;
	private Long[] machines;
}
