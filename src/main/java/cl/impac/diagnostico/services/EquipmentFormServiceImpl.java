package cl.impac.diagnostico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.dto.BaseCategoryDTO;
import cl.impac.diagnostico.dto.DiagnosticQuestionDTO;
import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.repositories.EquipmentFormRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EquipmentFormServiceImpl implements IEquipmentFormService {

	@Autowired
	private EquipmentFormRepository equipmentFormRepository;

	@Override
	public List<EquipmentFormDTO> getAllEquipmentForms() {
	    List<EquipmentForm> equipmentForms = equipmentFormRepository.findAll();
	    List<EquipmentFormDTO> equipmentFormsDTO = new ArrayList<>();

	    for (EquipmentForm equipmentForm : equipmentForms) {
	        EquipmentFormDTO equipmentFormDTO = new EquipmentFormDTO();
	        equipmentFormDTO.setEquipmentFormId(equipmentForm.getId());
	        equipmentFormDTO.setName(equipmentForm.getName());
	        equipmentFormDTO.setBaseCategories(equipmentForm.getBaseCategories());
	        
	        for (BaseCategory categoria : equipmentForm.getBaseCategories()) {
	            System.out.println("Categoria: " + categoria.getName());
	            categoria.getEquipmentForms().forEach(form -> {
	                System.out.println("  Formulario - ID: " + form.getId() + ", Nombre: " + form.getName());
	                form.getDiagnosticQuestion().forEach(question -> {
	                    System.out.println("    Pregunta: " + question.getDetalle());
	                });
	            });
	        }
	        
	        List<DiagnosticQuestion> questions = new ArrayList<>();
	        for (DiagnosticQuestion question : equipmentForm.getDiagnosticQuestion()) {
	           questions.add(question);	           
	        }
	        equipmentFormDTO.setQuestions(questions);
	        equipmentFormsDTO.add(equipmentFormDTO);
	    }
	    return equipmentFormsDTO;
	}

	@Override
	public Optional<EquipmentForm> getEquipmentFormById(Long id) {
		return equipmentFormRepository.findById(id);
	}

	@Override
	public EquipmentForm saveOrUpdateEquipmentForm(Long equipmentFormId, String name, List<BaseCategory> baseCategories) {
		EquipmentForm equipmentForm = (equipmentFormId != null)
				? equipmentFormRepository.findById(equipmentFormId).orElse(new EquipmentForm())
				: new EquipmentForm();
		
		equipmentForm.setBaseCategories(baseCategories);
		equipmentForm.setName(name);

		try {			
			return equipmentFormRepository.save(equipmentForm);
			
		} catch (DataIntegrityViolationException e) {			
			return null;
			
		}
	}

	@Override
	public boolean deleteEquipmentFormById(Long equipmentFormId) {
		if (equipmentFormId != null) {
			Optional<EquipmentForm> optionalEquipmentForm = equipmentFormRepository.findById(equipmentFormId);
			if (optionalEquipmentForm.isPresent()) {
				try {
					equipmentFormRepository.delete(optionalEquipmentForm.get());
					return true;
				} catch (DataIntegrityViolationException e) {
					return false;
				}
			}
		}
		return false;

	}

}
