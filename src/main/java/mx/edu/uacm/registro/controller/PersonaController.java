package mx.edu.uacm.registro.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mx.edu.uacm.registro.domain.Persona;
import mx.edu.uacm.registro.service.PersonaService;

@Controller
@RequestMapping("/")
public class PersonaController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonaService personaService;
	
	
	@RequestMapping(value="/recibirInformacion", method=RequestMethod.POST)
	public String recibeObjeto(Map <String, Object> model, Persona persona){
		
		log.debug("El valor del nombre es : " + persona.getNombre());
		
		personaService.agregarPersona(persona);
		
		
		return "redirect:/";
		
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map <String, Object> model){
		
		List<Persona>  personas = personaService.obtenerTodas();
		
		model.put("personas", personas);
		
		
		
		return "home";
	}
	
	
	@RequestMapping(value="/buscarPersona", method=RequestMethod.POST)
	public String buscarPersona(Map <String, Object> model,
			@RequestParam Long id){
		
		Persona persona = personaService.buscarPersonaXId(id);
		
		model.put("personaEncontrada", persona);
		
		return "home";
	}
	
	

}
