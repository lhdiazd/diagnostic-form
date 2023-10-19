package cl.impac.diagnostico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.models.entities.BaseCatergory;
import cl.impac.diagnostico.models.repositories.BaseCategoryRepository;

@Service
public class BaseCategoryServiceImpl implements IBaseCategoryService {
	
	@Autowired
	private BaseCategoryRepository baseCategoryRepository;
	@Override
	public List<BaseCatergory> getAllBaseCategories() {		
		return baseCategoryRepository.findAll();
	}

	@Override
	public Optional<BaseCatergory> getBaseCategoryById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
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
