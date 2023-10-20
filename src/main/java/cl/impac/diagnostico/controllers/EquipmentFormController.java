package cl.impac.diagnostico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.services.IEquipmentFormService;

@RestController
@RequestMapping("/formularios")
public class EquipmentFormController {
	
	@Autowired
	private IEquipmentFormService iEquipmentFormService;	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EquipmentFormDTO> getAllEquipmentForms(){
		return iEquipmentFormService.getAllEquipmentForms();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getEquipmentFormById(@PathVariable Long id) {
	    Optional<EquipmentFormDTO> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(id);
	    return optionalEquipmentForm.isPresent() ?
	            ResponseEntity.ok(optionalEquipmentForm.get()) :
	            ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Formulario no encontrado");
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEquipmentForm(@RequestBody EquipmentFormDTO equipmentFormDTO) {
        iEquipmentFormService.createEquipmentForm(equipmentFormDTO);
        return ResponseEntity.ok().build();
    }
}
