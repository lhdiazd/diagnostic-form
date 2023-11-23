package cl.impac.diagnostico.general.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CtmMachineDto {

	private Long id;
	private String singleRegistration;
	private float capacityTask;
	private String groupAir;
	private String model;
	private String mark;
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
	private boolean hidroConst;
	private Long categoryId;
	private Long customerId;

}
