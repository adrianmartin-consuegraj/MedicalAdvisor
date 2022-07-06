package bbdd;
import java.sql.*;

import exceptions.DAOException;
import exceptions.ServiceException;
/**
 * 
 * @author Angel
 * para los monstruos de la informática
 */
public class ConexionOracle{
// atributos
/**
 * objeto de conexion a la base de datos oracle
 */
private Connection objconexion;
// metodos
/**
 * permite capturar el objeto de conexion a la base de datos oracle que se creo
 * en el costructor
 * @return una conexion a la base de datos oracle
 */
public Connection  getConexion(){return objconexion;}
/**
 * crea un objeto de conexion a la base de datos oracle(con SID local) que se encuentra en el nodo
 * de red servidor(opcinal IP), que se accede por el puerto 1521 con el usuario de la base de 
 * datos Angel y password Numancia 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */

/**
 * crea un objeto de conexion a la base de datos oracle
 * urlbd: url de la base de datos Ejemplo "jdbc:oracle:thin:@servidor:1521:local" 
 * usuario: usuario de la base de datos 
 * password: contaseña del usuario en la base de datos 
 * @throws ServiceException 
 * @throws SQLException 
 * @throws ClassNotFoundException 
 */
public ConexionOracle(String urlbd,String usuario,String password) throws DAOException {

	// necesitamos la libreria  ojdbc14.jar coon los drives y objetos jdbc 
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	throw new DAOException("no se ha cargado los controladores jdbc",e);
}

	try {
		objconexion=DriverManager.getConnection(urlbd,usuario,password);
	} catch (SQLException e) {
		throw new DAOException("no se ha establecido la conexion a la bbdd",e);
	}





}// fin constructor

public ConexionOracle() throws DAOException {

	// necesitamos la libreria  ojdbc14.jar coon los drives y objetos jdbc 
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
} catch (ClassNotFoundException e) {
	throw new DAOException("no se ha cargado los controladores jdbc",e);
}

	
		objconexion=new ConexionOracle("jdbc:oracle:thin:@alfon-bb574d6f5:1521:oracle",
				"adrian","adrian").getConexion();
	





}// fin constructor
}// fin de la clase