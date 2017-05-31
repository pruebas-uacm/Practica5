package mx.edu.uacm.registro.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import mx.edu.uacm.registro.domain.Persona;
import mx.edu.uacm.registro.domain.repository.PersonaRepository;
import mx.edu.uacm.registro.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService {
	

	private static final String E003 = "E003";


	private static final int LOGITUD_MAX = 70;


	public static final Logger log = Logger.getLogger(PersonaServiceImpl.class);
	

	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	MessageSource messages;
	
	
	public String agregarPersona(Persona persona) {
		
		if(log.isDebugEnabled())
			log.debug("Estoy entrando al metodo agregarPersona");
		
		if(persona.getNombre().length()<=LOGITUD_MAX) {
			personaRepository.save(persona);
		} else {
			
			return messages.getMessage(E003, null, LocaleContextHolder.getLocale());
		}
		
		
		return null;

	}

	public Persona buscarPersonaXNombre(String nombre) {
		
		if(log.isDebugEnabled())
			log.debug("Entrando al metodo buscarPersonaXNombre");
		
		Persona p = personaRepository.findByNombre(nombre);
		
		return p;
	}

	public List<Persona> obtenerTodas() {
	
		return Lists.newArrayList(personaRepository.findAll());
	}

	public Persona buscarPersonaXId(Long id) {
		
		return personaRepository.findOne(id);
	}

}
