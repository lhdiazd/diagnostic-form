package cl.impac.diagnostico.controllers;


import java.util.ArrayList;
import java.util.HashMap;
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

import cl.impac.diagnostico.dto.BaseCategoryDTO;
import cl.impac.diagnostico.dto.DiagnosticQuestionDTO;
import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.services.IBaseCategoryService;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;
import cl.impac.diagnostico.services.IEquipmentFormService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/formularios")
public class EquipmentFormController {

	@Autowired
	private IEquipmentFormService iEquipmentFormService;

	@Autowired
	private IBaseCategoryService iBaseCategoryService;

	@Autowired
	private IDiagnosticQuestionService iDiagnosticQuestionService;
	

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EquipmentFormDTO> getAllEquipmentForms() {
		return iEquipmentFormService.getAllEquipmentForms();

	}

	@GetMapping(value = "/ver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEquipmentFormById(@PathVariable Long id) {

		Optional<EquipmentForm> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(id);

		return optionalEquipmentForm.isPresent() ? ResponseEntity.ok(optionalEquipmentForm.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulario no encontrado");
	}

	@PostMapping(value = "/crear-actualizar", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrUpdateEquipmentForm(@RequestBody EquipmentFormDTO formData) {
		 try {
		        List<BaseCategory> categoryList = formData.getBaseCategories();
		        List<BaseCategory> baseCategoryList = new ArrayList<>();

		        for (BaseCategory category : categoryList) {
		            BaseCategory baseCategory = iBaseCategoryService.getBaseCategoryById(category.getId())
		                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La categoría base no existe."));
		            baseCategoryList.add(baseCategory);
		        }

		        EquipmentForm savedEquipmentForm = iEquipmentFormService
		            .saveOrUpdateEquipmentForm(formData.getEquipmentFormId(), formData.getName(), baseCategoryList);

		        formData.getQuestions().forEach(questionDTO -> iDiagnosticQuestionService
		            .saveDiagnosticQuestion(questionDTO.getId(), savedEquipmentForm, questionDTO.getDetalle()));

		        Map<String, Object> response = new HashMap<>();
		        response.put("status", "success");
		        response.put("message", "El formulario se ha creado/actualizado exitosamente.");
		        return ResponseEntity.ok(response);
		    } catch (Exception e) {
		        Map<String, Object> response = new HashMap<>();
		        response.put("status", "error");
		        response.put("message", "No se pudo crear/actualizar el formulario.");
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear/actualizar el formulario.");
		    }
	}

	@DeleteMapping("/eliminar")
	public ResponseEntity<String> deleteEquipmentForm(@RequestParam Long equipmentFormId) {

		if (equipmentFormId != null) {

			Optional<EquipmentForm> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(equipmentFormId);

			if (optionalEquipmentForm.isPresent()) {

				try {

					iEquipmentFormService.deleteEquipmentFormById(equipmentFormId);
					return ResponseEntity.ok("El EquipmentForm se eliminó con éxito.");

				} catch (DataIntegrityViolationException e) {

					return ResponseEntity.badRequest()
							.body("Error en la eliminación debido a restricciones de integridad.");
				}
			}
		}

		return ResponseEntity.badRequest()
				.body("No se encontró el EquipmentForm con el ID especificado o el ID es nulo.");
	}



}
