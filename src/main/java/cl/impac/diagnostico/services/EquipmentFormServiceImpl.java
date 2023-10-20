package cl.impac.diagnostico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.repositories.EquipmentFormRepository;

@Service
public class EquipmentFormServiceImpl implements IEquipmentFormService {

	@Autowired
	private EquipmentFormRepository equipmentFormRepository;

	@Override
	public List<EquipmentFormDTO> getAllEquipmentForms() {
		List<EquipmentForm> equipmentForms = equipmentFormRepository.findAll();

		return equipmentForms.stream().map(equipmentForm -> new EquipmentFormDTO(equipmentForm.getId(),
				equipmentForm.getBaseCategory(), equipmentForm.getName(), equipmentForm.getDiagnosticQuestion()))
				.collect(Collectors.toList());
	}

	@Override
	public Optional<EquipmentFormDTO> getEquipmentFormById(Long id) {
		Optional<EquipmentForm> optionalEquipmentForm = equipmentFormRepository.findById(id);
		return optionalEquipmentForm.map(equipmentForm -> new EquipmentFormDTO(equipmentForm.getId(),
				equipmentForm.getBaseCategory(), equipmentForm.getName(), equipmentForm.getDiagnosticQuestion()));
	}

	@Override
	public void createEquipmentForm(EquipmentFormDTO equipmentFormDTO) {
		EquipmentForm equipmentForm = new EquipmentForm();
        equipmentForm.setBaseCategory(equipmentFormDTO.getBaseCategoryDTO());
        equipmentForm.setName(equipmentFormDTO.getName());
        equipmentFormRepository.save(equipmentForm);
	}

	@Override
	public void deleteEquipmentFormById(Long id) {
		// TODO Auto-generated method stub

	}

}
