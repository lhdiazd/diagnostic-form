package cl.impac.diagnostico.general.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.impac.diagnostico.general.services.ICatMachineService;
import cl.impac.diagnostico.models.entities.BaseCategory;
import cl.impac.diagnostico.models.general.entities.CatMachine;
import cl.impac.diagnostico.services.IBaseCategoryService;

@RestController
@RequestMapping("/categorias-formularios")
@CrossOrigin(origins = "http://localhost:4200")
public class CatMachineController {
	@Autowired
	private ICatMachineService iCatMachineService;

	@GetMapping(value = "/listar-categorias", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CatMachine> getAllCategories() {
		return iCatMachineService.getAllCategories();

	}
}
