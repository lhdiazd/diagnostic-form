package cl.impac.diagnostico.general.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.general.services.IQuestionService;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.general.entities.Question;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;
import cl.impac.diagnostico.utils.ResponsesBuilder;

@RestController
@RequestMapping("/preguntas-formulario")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {
	@Autowired
	private IQuestionService iQuestionService;
	
	@Autowired
	private ResponsesBuilder responsesBuilder;;
	
	@DeleteMapping("/eliminar-pregunta")
	public ResponseEntity<?> deleteQuestion(@RequestParam Long questionId) {
	    if (questionId != null) {
	        Optional<Question> optionalQuestion = iQuestionService.getQuestionById(questionId);
	        
	        if (optionalQuestion.isPresent()) {
	            try {
	                iQuestionService.deleteQuestionById(questionId);
	                Map<String, Object> response = responsesBuilder
	    					.createSuccessResponse("La pregunta se ha eliminado correctamente.");

	    			return ResponseEntity.ok(response);
	            } catch (DataIntegrityViolationException e) {
	            	Map<String, Object> response = responsesBuilder.createErrorResponse(e);
	    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	            }
	        }
	    }

	    return ResponseEntity.badRequest()
	            .body("No se encontró la pregunta con el ID especificado o el ID es nulo.");
	}
}
