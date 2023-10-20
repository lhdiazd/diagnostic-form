package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.dto.EquipmentFormDTO;

public interface IEquipmentFormService {
	public List<EquipmentFormDTO> getAllEquipmentForms();
	public Optional<EquipmentFormDTO> getEquipmentFormById(Long id);
	public void createEquipmentForm(EquipmentFormDTO equipmentFormDTO);
	public void deleteEquipmentFormById(Long id);
}
