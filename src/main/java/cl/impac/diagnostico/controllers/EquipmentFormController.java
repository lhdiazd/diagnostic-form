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
import cl.impac.diagnostico.dto.DiagnosticQuestionDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
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

	private List<Long> markedQuestions = new ArrayList<>();

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EquipmentForm> getAllEquipmentForms() {

		return iEquipmentFormService.getAllEquipmentForms();

	}

	@GetMapping(value = "/ver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEquipmentFormById(@PathVariable Long id) {

		Optional<EquipmentForm> optionalEquipmentForm = iEquipmentFormService.getEquipmentFormById(id);

		return optionalEquipmentForm.isPresent() ? ResponseEntity.ok(optionalEquipmentForm.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Formulario no encontrado");
	}

	@PostMapping(value = "/crear-actualizar",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrUpdateEquipmentForm(@RequestParam(required = false) Long equipmentFormId,
			@RequestParam(required = false) String name, @RequestParam(required = false) Long baseCategoryId,
			@RequestBody(required = false) List<DiagnosticQuestionDTO> questionsDTO) {

		try {

			Optional<BaseCategory> optionalBaseCategory = iBaseCategoryService.getBaseCategoryById(baseCategoryId);

			if (optionalBaseCategory.isPresent()) {

				BaseCategory baseCategory = optionalBaseCategory.get();

				EquipmentForm savedEquipmentForm = iEquipmentFormService.saveOrUpdateEquipmentForm(equipmentFormId,
						name, baseCategory);

				if (savedEquipmentForm != null) {

					for (DiagnosticQuestionDTO questionDTO : questionsDTO) {

						iDiagnosticQuestionService.saveDiagnosticQuestion(questionDTO.getId(), savedEquipmentForm,
								questionDTO.getDetalle());
					}

					if (!markedQuestions.isEmpty()) {
						for (Long questionId : markedQuestions) {
							iDiagnosticQuestionService.deleteDiagnosticQuestionById(questionId);
						}
						markedQuestions.clear();
					}

					return ResponseEntity.ok("El formulario se ha creado/actualizado exitosamente.");
				}
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los datos proporcionados no son válidos");

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se pudo crear/actualizar el formulario.");
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

	@PostMapping("/marcar-pregunta")
	public ResponseEntity<?> markQuestionForDeletion(@RequestParam Long diagnosticQuestionId) {

		if (diagnosticQuestionId == null) {

			return ResponseEntity.badRequest().body("El ID de la pregunta es nulo.");

		}

		markedQuestions.add(diagnosticQuestionId);

		return ResponseEntity.ok("Pregunta marcada para eliminación.");
	}

}
