package daos.interfaces;

import java.util.List;
import domain.Especialidad;
import exceptions.DAOException;

public interface IEspecialidadDAO extends ErroresBD {
	
	void añadirEspecialidad(int centro, String especialidad) throws DAOException;
	public int  borrarEspecialidad(Especialidad especialidad)throws DAOException;
	public void eliminarEspecialidadPorIdCentro(String especialidad)throws DAOException;
	public int  modificarEspecialidad(Especialidad especialidad)throws DAOException;
	public Especialidad recuperarEspecialidad(Especialidad especialidad)throws DAOException;
	public Especialidad recuperarEspecialidadPorId(String especialidad)throws DAOException;
	public List<Especialidad> eliminarEspecialidadPorIdCentro()throws DAOException;
	
}
