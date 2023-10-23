package cl.impac.diagnostico.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.services.IBaseCategoryService;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;
import cl.impac.diagnostico.services.IEquipmentFormService;

@RestController
@RequestMapping("/formularios")
public class EquipmentFormController {

	@Autowired
	private IEquipmentFormService iEquipmentFormService;

	@Autowired
	private IBaseCategoryService iBaseCategoryService;

	@Autowired
	private IDiagnosticQuestionService iDiagnosticQuestionService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EquipmentForm> getAllEquipmentForms() {
		return iEquipmentFormService.getAllEquipmentForms();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEquipmentFormById(@PathVariable Long id) {
		Optional<EquipmentForm> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(id);
		return optionalEquipmentForm.isPresent() ? ResponseEntity.ok(optionalEquipmentForm.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulario no encontrado");
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createEquipmentForm(@RequestParam String name, @RequestParam Long baseCategoryId,
			@RequestBody List<String> questions) {
		if (name != null && !name.isEmpty() && baseCategoryId != null && questions != null) {
			Optional<BaseCategory> optionalBaseCategory = iBaseCategoryService.getBaseCategoryById(baseCategoryId);
			if (optionalBaseCategory.isPresent()) {
				BaseCategory baseCategory = optionalBaseCategory.get();
				EquipmentForm equipmentForm = new EquipmentForm();
				equipmentForm.setBaseCategory(baseCategory);
				equipmentForm.setName(name);
				boolean created = iEquipmentFormService.createEquipmentForm(name, baseCategory);			
				try {					
					if (created && !questions.stream().allMatch(String::isBlank)) {
						for (String question : questions) {
							System.out.println("hola");
							iDiagnosticQuestionService.saveDiagnosticQuestion(equipmentForm, question);
						}
						return ResponseEntity.ok().build();
					}
				} catch (DataIntegrityViolationException e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				}
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	/*
	 * @DeleteMapping(value = "/{id}") public ResponseEntity<Void>
	 * deleteEquipmentForm(@PathVariable Long id) {
	 * 
	 * }
	 */

}
