package cl.impac.diagnostico.general.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.general.entities.Question;

public interface IQuestionService {
	public List<Question> getAllQuestions();
	public Optional<Question> getQuestionById(Long id);	
	public Question saveQuestion(Long QuestionId, String question, short order);
	public void deleteQuestionById(Long id);
}
