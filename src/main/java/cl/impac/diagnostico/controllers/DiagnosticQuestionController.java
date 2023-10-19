package cl.impac.diagnostico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.services.IDiagnosticQuestionService;


@RestController
@RequestMapping("/preguntas")
public class DiagnosticQuestionController {
	
	@Autowired
	private IDiagnosticQuestionService iDiagnosticQuestionService;
	
	@GetMapping(value = "lista", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DiagnosticQuestion> getDiagnosticQuestions(){
		return iDiagnosticQuestionService.getAllDiagnosticQuestions();
	}
	
	
	
}
 