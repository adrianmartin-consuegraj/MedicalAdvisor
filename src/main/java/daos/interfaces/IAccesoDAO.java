package daos.interfaces;

import java.util.List;
import domain.Acceso;
import exceptions.DAOException;

public interface IAccesoDAO extends ErroresBD {
	
	void añadirAcceso(String idCentro, String renfe, String metro, String coche) throws DAOException;
	public int  borrarAcceso(Acceso acceso)throws DAOException;
	public void eliminarAccesoPorIdCentro(String acceso)throws DAOException;
	public int  modificarAcceso(Acceso acceso)throws DAOException;
	public Acceso recuperarAcceso(Acceso acceso)throws DAOException;
	public Acceso  recuperarAccesoPorIdCentro(String acceso)throws DAOException;
	public List<Acceso> eliminarAccesoPorIdCentro()throws DAOException;
	
}
