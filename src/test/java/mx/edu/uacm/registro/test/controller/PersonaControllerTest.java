package mx.edu.uacm.registro.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import mx.edu.uacm.registro.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
@AutoConfigureMockMvc
public class PersonaControllerTest {
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void probarHome() throws Exception {
		
		mockMvc.perform(get("/")).andExpect(view().name("home"));
		
	}
	
	

	@Test
	public void probarHomeConPersonas() throws Exception {
		
		mockMvc.perform(get("/")).andExpect(model().attributeExists("personas"));
		
	}
	
	@Test
	public void probarRecibirObjeto() throws Exception {
		mockMvc.perform(post("/recibirInformacion").
				param("nombre", "francisco").
				param("rfc", "franky").
				param("total", "1130.57")).
				andExpect(redirectedUrl("/"));
	}

}
