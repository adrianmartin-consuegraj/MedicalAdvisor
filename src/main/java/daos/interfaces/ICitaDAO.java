package daos.interfaces;

import java.sql.Date;
import java.util.List;
import domain.Cita;
import exceptions.DAOException;

public interface ICitaDAO extends ErroresBD {
	
	void añadirCita(String usuario, String centro, String especialidad, Date fecha) throws DAOException;
	public int  borrarCita(Cita cita)throws DAOException;
	public void eliminarCitaPorId(String cita)throws DAOException;
	public int  modificarCita(Cita cita)throws DAOException;
	public Cita recuperarCita(Cita cita)throws DAOException;
	public Cita  recuperarCitaPorId(String cita)throws DAOException;
	public List<Cita> eliminarCitaPorId()throws DAOException;
	
}
