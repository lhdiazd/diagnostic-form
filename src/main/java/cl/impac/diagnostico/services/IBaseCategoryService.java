package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.models.entities.BaseCatergory;

public interface IBaseCategoryService {
	public List<BaseCatergory> getAllBaseCategories();
	public Optional<BaseCatergory> getBaseCategoryById(Long id);
	public void saveBaseCategory();
	public void deleteBaseCategoryById(Long id);
	
}
