package cl.impac.diagnostico.general.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.general.dto.FormMachinesDTO;
import cl.impac.diagnostico.general.services.ICatMachineService;
import cl.impac.diagnostico.general.services.IFormMachinesService;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.general.entities.FormMachines;
import cl.impac.diagnostico.services.IBaseCategoryService;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;
import cl.impac.diagnostico.services.IEquipmentFormService;
import cl.impac.diagnostico.utils.ResponsesBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/formularios")
public class FormMachinesController {
	@Autowired
	private IFormMachinesService iFormMachinesService;

	@Autowired
	private ICatMachineService iCatMachineService;

	
	@Autowired
	private ResponsesBuilder responsesBuilder;

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FormMachinesDTO> getAllForms() {
		return iFormMachinesService.getAllForms();

	}

	@GetMapping(value = "/ver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFormById(@PathVariable Long id) {

		Optional<FormMachines> optionFormMachine = iFormMachinesService.getFormById(id);

		return optionFormMachine.isPresent() ? ResponseEntity.ok(optionFormMachine.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulario no encontrado");
	}

	@PostMapping(value = "/crear-actualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrUpdateForm(@RequestBody FormMachinesDTO formData) {
		try {
			String name = formData.getName();
			String description = formData.getDescription();
			
			if (name == null || name.isEmpty()) {
				Map<String, Object> response = responsesBuilder
						.createErrorResponse(new Exception("El nombre del formulario no puede ser nulo o vacío."));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}			

			
			FormMachines savedForm = iFormMachinesService
					.saveOrUpdateForm(formData.getId(), name, description);

			formData.getQuestions().forEach(question -> iDiagnosticQuestionService
					.saveDiagnosticQuestion(question.getId(), savedEquipmentForm, question.getDetalle(), question.getOrderIndex()));

			Map<String, Object> response = responsesBuilder
					.createSuccessResponse("El formulario se ha creado/actualizado exitosamente.");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			Map<String, Object> response = responsesBuilder.createErrorResponse(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<?> deleteEquipmentForm(@RequestParam Long equipmentFormId) {

		if (equipmentFormId != null) {

			Optional<EquipmentForm> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(equipmentFormId);

			if (optionalEquipmentForm.isPresent()) {

				try {

					iEquipmentFormService.deleteEquipmentFormById(equipmentFormId);
					Map<String, Object> response = responsesBuilder
							.createSuccessResponse("El formulario se ha eliminado exitosamente.");

					return ResponseEntity.ok(response);

				} catch (DataIntegrityViolationException e) {
					Map<String, Object> response = responsesBuilder.createErrorResponse(e);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

				}
			}
		}

		return ResponseEntity.badRequest()
				.body("No se encontró el EquipmentForm con el ID especificado o el ID es nulo.");
	}

}
