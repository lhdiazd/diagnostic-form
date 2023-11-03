package cl.impac.diagnostico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.impac.diagnostico.models.entities.BaseCategory;  
import cl.impac.diagnostico.services.IBaseCategoryService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class BaseCatagoryController {
	@Autowired
	private IBaseCategoryService ibaseCategoryService;

	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BaseCategory> getAllEquipmentForms() {

		return ibaseCategoryService.getAllBaseCategories();

	}
}
