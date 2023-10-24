package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;
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
