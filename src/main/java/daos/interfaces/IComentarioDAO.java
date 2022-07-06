package daos.interfaces;

import java.sql.Date;
import java.util.List;
import domain.Comentario;
import exceptions.DAOException;

public interface IComentarioDAO extends ErroresBD {
	
	void añadirComentario(int idCentro, String nombreUsuario, Date fecha, String mensaje) throws DAOException;
	public int  borrarComentario(Comentario comentario)throws DAOException;
	public void eliminarComentarioPorIdCentro(String comentario)throws DAOException;
	public int  modificarComentario(Comentario comentario)throws DAOException;
	public Comentario recuperarComentario(Comentario comentario)throws DAOException;
	public Comentario recuperarComentarioPorIdCentro(String comentario)throws DAOException;
	public List<Comentario> eliminarComentarioPorIdCentro()throws DAOException;	
	
}
