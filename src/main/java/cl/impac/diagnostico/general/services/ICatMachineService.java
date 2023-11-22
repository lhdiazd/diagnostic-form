package cl.impac.diagnostico.general.services;

import java.util.List;
import java.util.Optional;
import cl.impac.diagnostico.models.general.entities.CatMachine;

public interface ICatMachineService {
	public List<CatMachine> getAllCategories();
	public Optional<CatMachine> getCategoryById(Long id);
	public void saveCategory();
	public void deleteCategoryById(Long id);
}
