package cl.impac.diagnostico.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;

import cl.impac.diagnostico.services.IDiagnosticQuestionService;

@RestController
@RequestMapping("/preguntas")
@CrossOrigin(origins = "http://localhost:4200")
public class DiagnosticQuestionController {
	
	@Autowired
	private IDiagnosticQuestionService iDiagnosticQuestionService;
	
	@DeleteMapping("/eliminar-pregunta")
	public ResponseEntity<String> deleteDiagnosticQuestion(@RequestParam Long diagnosticQuestionId) {
	    if (diagnosticQuestionId != null) {
	        Optional<DiagnosticQuestion> optionalDiagnosticQuestion = iDiagnosticQuestionService.getDiagnosticQuestionById(diagnosticQuestionId);
	        
	        if (optionalDiagnosticQuestion.isPresent()) {
	            try {
	                iDiagnosticQuestionService.deleteDiagnosticQuestionById(diagnosticQuestionId);
	                return ResponseEntity.ok("La pregunta se eliminó con éxito.");
	            } catch (DataIntegrityViolationException e) {
	                return ResponseEntity.badRequest()
	                        .body("Error en la eliminación debido a restricciones de integridad.");
	            }
	        }
	    }

	    return ResponseEntity.badRequest()
	            .body("No se encontró la pregunta con el ID especificado o el ID es nulo.");
	}


}
