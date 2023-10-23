package cl.impac.diagnostico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.repositories.EquipmentFormRepository;

@Service
public class EquipmentFormServiceImpl implements IEquipmentFormService {

	@Autowired
	private EquipmentFormRepository equipmentFormRepository;

	@Override
	public List<EquipmentForm> getAllEquipmentForms() {
		return equipmentFormRepository.findAll();
	}

	@Override
	public Optional<EquipmentForm> getEquipmentFormById(Long id) {
		return equipmentFormRepository.findById(id);
	}

	@Override
	public boolean createEquipmentForm(String name, BaseCategory baseCategory) {
		if (baseCategory == null) {
			return false;
		}
		EquipmentForm equipmentForm = new EquipmentForm();
		equipmentForm.setBaseCategory(baseCategory);
		equipmentForm.setName(name);
		try {
			equipmentFormRepository.save(equipmentForm);
			return true;

		} catch (DataIntegrityViolationException e) {
			return false;
		}

	}

	@Override
	public boolean deleteEquipmentFormById(Long id) {
		Optional<EquipmentForm> optionalEquipmentForm = equipmentFormRepository.findById(id);
		if (optionalEquipmentForm.isPresent()) {
			return false;			
		}
		try {
			equipmentFormRepository.delete(optionalEquipmentForm.get());
			return true;
		} catch (DataIntegrityViolationException e) {
			return false;
		}
		

	}

}
