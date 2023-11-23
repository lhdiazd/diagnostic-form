package cl.impac.diagnostico.general.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.impac.diagnostico.models.general.entities.CatMachine;
import cl.impac.diagnostico.models.general.repositories.CatMachineRepository;

@Service
public class CatMachineServiceImpl implements ICatMachineService {
	@Autowired
	private CatMachineRepository catMachineRepository;
	
	@Override
	public List<CatMachine> getAllCategories() {		
		return catMachineRepository.findAll();
	}

	@Override
	public Optional<CatMachine> getCategoryById(Long id) {		
		return catMachineRepository.findById(id);
	}

	@Override
	public void saveCategory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub

	}

}
