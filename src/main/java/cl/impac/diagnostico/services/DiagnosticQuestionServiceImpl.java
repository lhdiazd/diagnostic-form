package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.repositories.DiagnosticQuestionRepository;

@Service
public class DiagnosticQuestionServiceImpl implements IDiagnosticQuestionService {

	@Autowired
	private DiagnosticQuestionRepository diagnosticQuestionRepository;
	
	@Override
	public List<DiagnosticQuestion> getAllDiagnosticQuestions() {
		return diagnosticQuestionRepository.findAll();
	}

	@Override
	public Optional<DiagnosticQuestion> getDiagnosticQuestionById(Long id) {
		return diagnosticQuestionRepository.findById(id);
	}

	@Override
	public DiagnosticQuestion saveDiagnosticQuestion(Long diagnosticQuestionId, EquipmentForm equipmentForm, String detalle) {
		DiagnosticQuestion diagnosticQuestion = (diagnosticQuestionId != null)
				? diagnosticQuestionRepository.findById(diagnosticQuestionId).orElse(new DiagnosticQuestion())
				: new DiagnosticQuestion();

		diagnosticQuestion.setEquipmentForm(equipmentForm);
		diagnosticQuestion.setDetalle(detalle);

		try {
			
			if(detalle != null && !detalle.isBlank()) {
				return diagnosticQuestionRepository.save(diagnosticQuestion);
			} else {
				return null;
			}
			
		} catch (DataIntegrityViolationException e) {
			return null;
		}		
	}
	
	

	

	@Override
	public void deleteDiagnosticQuestionById(Long diagnosticQuestionId) {
		if (diagnosticQuestionId != null) {
	        diagnosticQuestionRepository.deleteById(diagnosticQuestionId);
	    }
	}

}
