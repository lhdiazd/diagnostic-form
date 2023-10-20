package cl.impac.diagnostico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class FormularioDiagnosticoImpacApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
		
	@Test
	void getAllQuestionsTest() throws Exception {
		mockMvc.perform(get("/preguntas/lista")).andDo(print());		
	}

}
