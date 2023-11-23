package cl.impac.diagnostico.general.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	private Long id;
	private String rut;
	private String rsocial;
	private String address;
	private String comuna;
	private String region;
		

}
