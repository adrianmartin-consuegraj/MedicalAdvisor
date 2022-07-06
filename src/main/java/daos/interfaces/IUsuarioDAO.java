package daos.interfaces;


import java.util.List;

import domain.Usuario;
import exceptions.DAOException;

public interface IUsuarioDAO extends ErroresBD {
	
	public void insertarUsuario(Usuario usuario)throws DAOException;
	public int  borrarUsuario(Usuario usuario)throws DAOException;
	public int  borrarUsuarioPorId(String usuario)throws DAOException;
	public int  modificarUsuario(Usuario usuario)throws DAOException;
	public Usuario recuperarUsuario(Usuario usuario)throws DAOException;
	public Usuario  recuperarUsuarioPorId(String usuario)throws DAOException;
	public List<Usuario>  recuperarTodosUsuario()throws DAOException;
	
}
