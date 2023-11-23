package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;

public interface IDiagnosticQuestionService {
	public List<DiagnosticQuestion> getAllDiagnosticQuestions();
	public Optional<DiagnosticQuestion> getDiagnosticQuestionById(Long id);	
	public DiagnosticQuestion saveDiagnosticQuestion(Long diagnosticQuestionId, EquipmentForm equipmentForm, String detalle, int orderIndex);
	public void deleteDiagnosticQuestionById(Long id);
}
