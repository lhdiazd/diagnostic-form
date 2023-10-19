package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.EquipmentForm;

public interface IEquipmentFormService {
	public List<EquipmentForm> getAllEquipmentForms();
	public Optional<EquipmentForm> getEquipmentFormById(Long id);
	public void saveEquipmentForm();
	public void deleteEquipmentFormById(Long id);
}
