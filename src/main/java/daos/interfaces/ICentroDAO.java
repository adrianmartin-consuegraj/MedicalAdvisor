package daos.interfaces;

import java.util.List;
import domain.Centro;
import domain.Cita;
import domain.Favorito;
import exceptions.DAOException;

public interface ICentroDAO extends ErroresBD {
	
	public void insertarCentro(Centro centro)throws DAOException;
	public int  borrarCentro(Centro centro)throws DAOException;
	public int  borrarCentroPorId(String centro)throws DAOException;
	public int  modificarCentro(Cita cita)throws DAOException;
	public Centro recuperarCentro(Centro centro)throws DAOException;
	public Centro  recuperarCentroPorId(String centro)throws DAOException;
	public List<Centro> recuperarTodasCentros()throws DAOException;
	public List<Centro> recuperarTodasCentrosFavoritosPorIdCentro(List<Integer> idCentros)throws DAOException;
	public Centro recuperarTodasCentrosFavoritosPorIdCentro(int idCentro) throws DAOException;
	Centro recuperarCentroFavoritoPorIdCentro(int idCentro) throws DAOException;
	
}
