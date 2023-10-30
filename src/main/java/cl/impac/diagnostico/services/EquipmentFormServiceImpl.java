package cl.impac.diagnostico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

@Service
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
	        equipmentFormDTO.setBaseCategoryDTO(equipmentForm.getBaseCategory());
	        

	       
	        List<DiagnosticQuestionDTO> questionsDTO = new ArrayList<>();
	        for (DiagnosticQuestion question : equipmentForm.getDiagnosticQuestion()) {
	            DiagnosticQuestionDTO questionDTO = new DiagnosticQuestionDTO();
	            questionDTO.setId(question.getId());
	            questionDTO.setDetalle(question.getDetalle());
	            questionsDTO.add(questionDTO);
	        }
	        equipmentFormDTO.setQuestionsDTO(questionsDTO);

	        equipmentFormsDTO.add(equipmentFormDTO);
	    }

	    return equipmentFormsDTO;
	}

	@Override
	public Optional<EquipmentForm> getEquipmentFormById(Long id) {
		return equipmentFormRepository.findById(id);
	}

	@Override
	public EquipmentForm saveOrUpdateEquipmentForm(Long equipmentFormId, String name, BaseCategory baseCategory) {
		EquipmentForm equipmentForm = (equipmentFormId != null)
				? equipmentFormRepository.findById(equipmentFormId).orElse(new EquipmentForm())
				: new EquipmentForm();
		
		equipmentForm.setBaseCategory(baseCategory);
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
