package cl.impac.diagnostico.controllers;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;


@RestController
@RequestMapping("/preguntas")
public class DiagnosticQuestionController {
	
	@Autowired
	private IDiagnosticQuestionService iDiagnosticQuestionService;
	
	@GetMapping(value = "/lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiagnosticQuestion> getDiagnosticQuestions(){
		return iDiagnosticQuestionService.getAllDiagnosticQuestions();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<?> getDiagnosticQuestionById(@PathVariable Long id) {
	    Optional<DiagnosticQuestion> optionalDiagnosticQuestion = iDiagnosticQuestionService.getDiagnosticQuestionById(id);
	    return optionalDiagnosticQuestion.isPresent() ?
	            ResponseEntity.ok(optionalDiagnosticQuestion.get()) :
	            ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("Pregunta no encontrada");
	}
	
	
	
	
	
}
 