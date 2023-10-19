package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;

public interface IDiagnosticQuestionService {
	public List<DiagnosticQuestion> getAllDiagnosticQuestions();
	public Optional<DiagnosticQuestion> getDiagnosticQuestionById(Long id);
	public void saveDiagnosticQuestion();
	public void deleteDiagnosticQuestionById(Long id);
}
