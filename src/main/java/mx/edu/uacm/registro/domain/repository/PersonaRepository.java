package mx.edu.uacm.registro.domain.repository;

import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.registro.domain.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

	 Persona findByNombre(String nombre);
}
