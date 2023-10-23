package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.repositories.BaseCategoryRepository;

@Service
public class BaseCategoryServiceImpl implements IBaseCategoryService {
	
	@Autowired
	private BaseCategoryRepository baseCategoryRepository;
	
	@Override
	public List<BaseCategory> getAllBaseCategories() {		
		return baseCategoryRepository.findAll();
	}

	@Override
	public Optional<BaseCategory> getBaseCategoryById(Long id) {		
		return baseCategoryRepository.findById(id);
	}

	@Override
	public void saveBaseCategory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBaseCategoryById(Long id) {
		// TODO Auto-generated method stub

	}

}
