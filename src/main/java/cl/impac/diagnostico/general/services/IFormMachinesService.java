package cl.impac.diagnostico.general.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.dto.EquipmentFormDTO;
import cl.impac.diagnostico.general.dto.FormMachinesDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.entities.EquipmentForm;
import cl.impac.diagnostico.models.general.entities.FormMachines;

public interface IFormMachinesService {
	public List<FormMachinesDTO> getAllForms();
	public Optional<FormMachines> getFormById(Long id);
	public FormMachines saveOrUpdateForm(Long FormMachineId, String name, String description);
	public boolean deleteEquipmentFormById(Long id);
}
