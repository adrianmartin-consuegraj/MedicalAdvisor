package daos.interfaces;

import java.util.List;
import domain.Cita;
import domain.Distrito;
import exceptions.DAOException;

public interface IDistritoDAO extends ErroresBD {
	
	public void insertarDistrito(Distrito distrito)throws DAOException;
	public int  borrarDistrito(Distrito distrito)throws DAOException;
	public int  borrarDistritoPorId(String distrito)throws DAOException;
	public int  modificarDistrito(Distrito distrito)throws DAOException;
	public Distrito recuperarDistrito(Distrito distrito)throws DAOException;
	public Distrito  recuperarDistritoPorId(String distrito)throws DAOException;
	public List<Distrito> recuperarTodosDistritos()throws DAOException;
	
}
