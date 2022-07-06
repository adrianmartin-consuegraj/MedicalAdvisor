package bbdd;
import java.sql.*;

import exceptions.DAOException;
/**
 * 
 * @author Angel
 *
 */
public class ConexionMySql{
// atributos
/**
 * objeto de conexion a la base de datos Mysql
 *  */
private Connection objconexion;
// metodos
/**
 * permite capturar el objeto de conexion a la base de datos Mysql que se creo
 * en el costructor
 * @return una conexion a la base de datos Mysql
 */
public Connection  getConexion(){return objconexion;}
/**
 * crea un objeto de conexion a la base de datos Mysql(cr7) que se encuentra en el nodo
 * de red servidor(opcinal IP),  con el usuario de la base de 
 * datos Angel y password Numancia 
 * @throws DAOException 
 */
public ConexionMySql() throws DAOException{
try{
	// necesitamos la libreria  mysql-connector-java-5.1.20-bin coon los drives y objetos jdbc 
	Class.forName("com.mysql.jdbc.Driver");
// servidor es el nodo de red donde está la base de datos,se puede poner la IP
// cr7 ,es la base de datos
// angel es el usuario de oracle,con minusculas
// numancia es la password de angel, con minusculas.
// OJO MYSQL hace distinción entre MAYUSCILAS y MINUSCULA en la autentificación	
objconexion=DriverManager.getConnection("jdbc:mysql://LocalHost/prueba1",
                                        "adrian","adrian");//ojo MAYUSCULAS y Minusculas

}
catch(ClassNotFoundException e){
	throw new DAOException("no se ha cargado los controladores jdbc",e);

}
catch(SQLException e){
	throw new DAOException("no se ha establecido la conexion a la bbdd",e);
}												 

}// fin constructor
/**
 * crea un objeto de conexion a la base de datos MySQL
 * urlbd: url de la base de datos Ejemplo "jdbc:mysql://servidor/cr7" 
 * usuario: usuario de la base de datos CR7
 * password: contaseña del usuario en la base de datos 
 * @throws DAOException 
 */
public ConexionMySql(String urlbd,String usuario,String password) throws DAOException{
try{
	// necesitamos la libreria  mysql-connector-java-5.1.20-bin coon los drives y objetos jdbc 
	Class.forName("com.mysql.jdbc.Driver");

objconexion=DriverManager.getConnection(urlbd,usuario,password);//ojo MAYUSCULAS y Minusculas

}
catch(ClassNotFoundException e){
	throw new DAOException("no se ha cargado los controladores jdbc",e);
}
catch(SQLException e){
	throw new DAOException("no se ha establecido la conexion a la bbdd",e);
}												 


}// fin constructor

}// fin de la clase