package mx.edu.uacm.registro.test.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mx.edu.uacm.registro.Application;
import mx.edu.uacm.registro.domain.Factura;
import mx.edu.uacm.registro.domain.Persona;
import mx.edu.uacm.registro.domain.repository.FacturaRepository;
import mx.edu.uacm.registro.domain.repository.PersonaRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class PersonaTest {
	
	public static final Logger log = Logger.getLogger(PersonaTest.class);
	
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	FacturaRepository facturaRepository;
	
	@Autowired
	DataSource dataSource;
	
	@Before
	public  void configurar() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
		 dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		 FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		 
		 IDataSet dataSetFactura = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/factura.xml"));
		 
		 IDataSet dataSetPersona = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/persona.xml"));
		
		
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetFactura);
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetPersona);
		 

		 DatabaseOperation.INSERT.execute(connection, dataSetPersona);
		 DatabaseOperation.INSERT.execute(connection, dataSetFactura);
		
		 
		 
		
		 
		 
	}
	

	
	@Test
	public void actualizarPersonaTest(){
		
		log.debug("Entrando al metodo actualizarPersonaTest");
		//Dato de Prueba
		String nombrePersona = "Teacher";
		String nombreABuscar = "Profesor";
		
		//Buscar a la persona con mi dato de prueba
		Persona personaObtenida = personaRepository.findByNombre(nombreABuscar);
		
		//Asegurando que el obj Persona no sea nulo
		Assert.assertNotNull(personaObtenida);
		
		//modificando el nombre de la persona obtenida
		personaObtenida.setNombre(nombrePersona);
		
		//creando mi factura
		Factura fac1 = new Factura(new Date(), 1000.0);
		
		//Se reliza la relacion manytoone
		fac1.setPersona(personaObtenida);
		
		//creando mi coleccion de facturas
		List<Factura> facturas = new ArrayList<Factura>();
		
		//agregando mi factura a la coleccion de factuas
		facturas.add(fac1);
		
		//pasando las facturas a la persona
		personaObtenida.setFacturas(facturas);
		
		//persistencia de la entidad persona
		personaRepository.save(personaObtenida);
		
		
		//Obtener a la persona una vez que se actualizo el nombre
		personaObtenida = personaRepository.findByNombre(nombrePersona);
		
		Assert.assertTrue(
				nombrePersona.equals(
						personaObtenida.getNombre()));
		
		log.debug(personaObtenida.getFacturas().size());
		
	}
	
	@After
	public  void terminar() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		
		Connection conn = dataSource.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(conn);
		DatabaseConfig dbConfig = connection.getConfig();
		 dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		 FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		 
		 IDataSet dataSetFactura = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/factura.xml"));
		 
		 IDataSet dataSetPersona = builder.build(new File(System.getProperty("user.dir") + System.getProperty("file.separator")
			+ "datasets/persona.xml"));
		 
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetFactura);
		 DatabaseOperation.DELETE_ALL.execute(connection, dataSetPersona);
		
	}

}
