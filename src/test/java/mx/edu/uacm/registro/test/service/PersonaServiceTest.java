package mx.edu.uacm.registro.test.service;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.registro.Application;
import mx.edu.uacm.registro.domain.Persona;
import mx.edu.uacm.registro.service.PersonaService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class PersonaServiceTest {
	
	public static final Logger log = Logger.getLogger(PersonaServiceTest.class);
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	MessageSource messages;
	
	@Test
	public void guardarPersonaServiceTest() {
		
		log.debug("Iniciando metodo guardarPersonaServiceTest");
		
		String mensaje1 = messages.getMessage("E001", null, LocaleContextHolder.getLocale());
		log.debug("*****************");
		log.debug(mensaje1);
		
		String mensaje2 = messages.getMessage("E002", new Object[]{"Christian"}, LocaleContextHolder.getLocale());
		log.debug("*****************");
		log.debug(mensaje2);
		
		//System.out.println("Iniciando metodo guardarPersonaServiceTest");
		
		Persona p = new Persona();
		//datos de prueba
		String nombre = "Neymar1";
		
		p.setNombre(nombre);
		
		p.setRfc("Messd");
		p.setTotal(new Double("1000.0"));
		
		String mensajeDeError = personaService.agregarPersona(p);
		
		
		
		Persona personaEnocontrada = personaService.buscarPersonaXNombre(nombre);
		Assert.assertNotNull(personaEnocontrada);
		
		Assert.assertTrue("La concicion se cumple", nombre.equals(personaEnocontrada.getNombre()));
		
		
	}

}
