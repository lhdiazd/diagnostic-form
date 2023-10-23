package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import cl.impac.diagnostico.dto.BaseCategoryDTO;
import cl.impac.diagnostico.models.entities.BaseCategory;

public interface IBaseCategoryService {
	public List<BaseCategory> getAllBaseCategories();
	public Optional<BaseCategory> getBaseCategoryById(Long id);
	public void saveBaseCategory();
	public void deleteBaseCategoryById(Long id);
	
}
