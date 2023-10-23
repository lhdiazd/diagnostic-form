package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.DiagnosticQuestion;
import cl.impac.diagnostico.models.entities.EquipmentForm;

public interface IEquipmentFormService {
	public List<EquipmentForm> getAllEquipmentForms();
	public Optional<EquipmentForm> getEquipmentFormById(Long id);
	public boolean createEquipmentForm(String name, BaseCategory baseCategory);
	public boolean deleteEquipmentFormById(Long id);
}
