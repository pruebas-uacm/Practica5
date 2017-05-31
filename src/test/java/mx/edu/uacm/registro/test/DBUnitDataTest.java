package mx.edu.uacm.registro.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import mx.edu.uacm.registro.Application;


import javax.sql.DataSource;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.dbunit.DatabaseUnitException;
import java.io.FileNotFoundException;
import java.io.IOException;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class DBUnitDataTest {
	
	public static final Logger log = Logger.getLogger(DBUnitDataTest.class);
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void extraerDatasetsTest()  throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		log.debug("prueba de humo");
		//Este metodo solo sirve para extraer los datasets
		//DBUnitData.extraerDatasetsUnoxUno(dataSource.getConnection());
		
	}
	
}