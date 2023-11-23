package cl.impac.diagnostico.general.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.dto.EquipmentFormDTO;

import cl.impac.diagnostico.general.dto.FormMachinesDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.general.entities.FormMachines;
import cl.impac.diagnostico.models.general.entities.Question;
import cl.impac.diagnostico.models.general.repositories.FormMachinesRepository;
import cl.impac.diagnostico.models.repositories.EquipmentFormRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class FormMachinesServiceImpl implements IFormMachinesService {
	
	@Autowired
	private FormMachinesRepository formMachinesRepository;

	@Override
	public List<FormMachinesDTO> getAllForms() {
	    List<FormMachines> formMachines = formMachinesRepository.findAll();
	    List<FormMachinesDTO> formMachinesDTOs = new ArrayList<>();

	    for (FormMachines formMachine : formMachines) {
	    	FormMachinesDTO formMachinesDTO = new FormMachinesDTO();
	    	formMachinesDTO.setId(formMachine.getId());
	    	formMachinesDTO.setName(formMachine.getName());      
	     
	        
	        List<Question> questions = new ArrayList<>();
	        for (Question question : formMachine.getQuestions()) {
	           questions.add(question);	           
	        }
	        formMachinesDTO.setQuestions(questions);
	        formMachinesDTOs.add(formMachinesDTO);
	    }
	    return formMachinesDTOs;
	}

	@Override
	public Optional<FormMachines> getFormById(Long id) {		
		return formMachinesRepository.findById(id);
	}

	@Override
	public FormMachines saveOrUpdateForm(Long formMachineId, String name, String description) {
		FormMachines formMachines = (formMachineId != null)
				? formMachinesRepository.findById(formMachineId).orElse(new FormMachines())
				: new FormMachines();		
		formMachines.setName(name);
		try {			
			return formMachinesRepository.save(formMachines);
			
		} catch (DataIntegrityViolationException e) {			
			return null;
			
		}
	}

	@Override
	public boolean deleteFormById(Long id) {
		if (id != null) {
			Optional<FormMachines> optionalFormMachines = formMachinesRepository.findById(id);
			if (optionalFormMachines.isPresent()) {
				try {
					formMachinesRepository.delete(optionalFormMachines.get());
					return true;
				} catch (DataIntegrityViolationException e) {
					return false;
				}
			}
		}
		return false;

	}

}
