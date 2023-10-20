package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
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
	public void saveDiagnosticQuestion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteDiagnosticQuestionById(Long id) {
		// TODO Auto-generated method stub

	}

}
