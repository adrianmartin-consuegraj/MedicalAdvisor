package daos.interfaces;

import java.util.List;
import domain.Acceso;
import domain.Servicio;
import exceptions.DAOException;

public interface IServicioDAO extends ErroresBD {
	
	void añadirServicio(String idCentro, String aparcamiento, String television, String restaurantes) throws DAOException;
	public int  borrarServicio(Acceso servicio)throws DAOException;
	public void eliminarServicioPorIdCentro(String servicio)throws DAOException;
	public int  modificarServicio(Servicio servicio)throws DAOException;
	public Servicio recuperarServicio(Servicio servicio)throws DAOException;
	public Servicio  recuperarServicioPorIdCentro(String servicio)throws DAOException;
	public List<Servicio> eliminarServicioPorIdCentro()throws DAOException;
	
}
