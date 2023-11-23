package cl.impac.diagnostico.general.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.general.entities.Question;
import cl.impac.diagnostico.models.general.repositories.QuestionRepository;
import cl.impac.diagnostico.models.repositories.DiagnosticQuestionRepository;

@Service
public class QuestionServiceImpl implements IQuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	@Override
	public List<Question> getAllQuestions() {
		// TODO Auto-generated method stub
		return questionRepository.findAll();
	}

	@Override
	public Optional<Question> getQuestionById(Long id) {
		return questionRepository.findById(id);
	}

	@Override
	public Question saveQuestion(Long questionId, String question, short order) {
		Question questionForm = (questionId != null)
				? questionRepository.findById(questionId).orElse(new Question())
				: new Question();

		
		questionForm.setQuestion(question);
		questionForm.setOrden(order);

		try {
			
			if(question != null && !question.isBlank()) {
				return questionRepository.save(questionForm);
			} else {
				return null;
			}
			
		} catch (DataIntegrityViolationException e) {
			return null;
		}
	}

	@Override
	public void deleteQuestionById(Long id) {
		if (id != null) {
	        questionRepository.deleteById(id);
	    }
	}

}
