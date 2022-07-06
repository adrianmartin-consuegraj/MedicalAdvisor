package daos.interfaces;

import java.util.List;
import domain.Cita;
import domain.Favorito;
import exceptions.DAOException;

public interface IFavoritoDAO extends ErroresBD {
	
	public void insertarFavorito(Favorito favorito)throws DAOException;
	public int  borrarFavorito(Favorito favorito)throws DAOException;
	//public int  borrarFavoritoPorUsuarioYCentro(String favorito)throws DAOException;
	public int  modificarFavorito(Favorito favorito)throws DAOException;
	public Cita recuperarFavorito(Favorito favorito)throws DAOException;
	//public Cita  recuperarFavoritoPorCentro(String favorito)throws DAOException;
	public List<Favorito> recuperarTodosFavoritos()throws DAOException;
	List<Integer> recuperarTodosFavoritos(String usuario) throws DAOException;
	
}
