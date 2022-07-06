package daos.interfaces;

import java.util.List;
import domain.Cita;
import domain.Vacuna;
import exceptions.DAOException;

public interface IVacunaDAO extends ErroresBD {
	
	public void insertarVacuna(Vacuna vacuna)throws DAOException;
	public int  borrarVacuna(Vacuna vacuna)throws DAOException;
	//public int  borrarCitaPorId(String vacuna)throws DAOException;
	public int  modificarVacuna(Vacuna vacuna)throws DAOException;
	public Vacuna recuperarVacuna(Vacuna vacuna)throws DAOException;
	//public Vacuna  recuperarVacunaPorId(String vacuna)throws DAOException;
	public List<Vacuna> recuperarTodasVacunas()throws DAOException;
	
}
