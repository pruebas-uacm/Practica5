package mx.edu.uacm.registro.service;


import java.util.List;

import mx.edu.uacm.registro.domain.Persona;

public interface PersonaService {
	
	
	String agregarPersona(Persona persona);
	
	Persona buscarPersonaXNombre(String nombre);

	List<Persona> obtenerTodas();

	Persona buscarPersonaXId(Long id);

}
