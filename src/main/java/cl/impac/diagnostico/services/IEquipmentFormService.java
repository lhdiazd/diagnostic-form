package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.EquipmentForm;

public interface IEquipmentFormService {
	public List<EquipmentForm> getAllEquipmentForms();
	public Optional<EquipmentForm> getEquipmentFormById(Long id);
	public EquipmentForm saveOrUpdateEquipmentForm(Long equipmentFormId, String name, BaseCategory baseCategory);
	public boolean deleteEquipmentFormById(Long id);
}
