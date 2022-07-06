package servicios;

import domain.Usuario;

import java.util.ArrayList;
import java.util.List;

import daos.TransaccionesManager;
import daos.UsuarioDAO;
import exceptions.DAOException;
import exceptions.ServiceException;

public class ServicioUsuario {


	public ServicioUsuario() {
	}
	
	//INSERTAR
	public void insertarUsuario(Usuario usuario) throws ServiceException{
		
		TransaccionesManager trans =  null;
		UsuarioDAO usuarioDAO=null;
		
		try {
			
			trans =  new TransaccionesManager();
			
			usuarioDAO = trans.getUsuarioDAO();
			usuarioDAO.insertarUsuario(usuario);
			
			
		
			trans.closeCommit();
		} catch (DAOException e) {

			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
	}

	//RECUPERAR
	public Usuario RecuperarUsuario(Usuario usuario) throws ServiceException {
		TransaccionesManager trans = null;

		try {
			
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuario = usuarioDAO.recuperarUsuario(usuario);
			
            if(usuario==null){
            	usuario = null;
            } 
            	
			trans.closeCommit();
       
            
		} catch (DAOException e) {
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return usuario;
	}

	public List<Usuario> RecuperarTodosUsuarios() throws ServiceException {
		TransaccionesManager trans = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			
			trans = new TransaccionesManager();
			UsuarioDAO usuarioDAO = trans.getUsuarioDAO();
			usuarios = usuarioDAO.recuperarTodosUsuario();
			
			trans.closeCommit();
       
            
		} catch (DAOException e) {
			try{
				if(trans!=null)
				trans.closeRollback();
			}catch (DAOException e1){
				throw new ServiceException(e.getMessage(),e1);//Error interno
			}

			if(e.getCause()==null){
				throw new ServiceException(e.getMessage());//Error Lógico
			}else{

				throw new ServiceException(e.getMessage(),e);//Error interno
			}

		}
		return usuarios;
	}
	
	
	
//	public void insertarCliente(String codcli,
//			String razonsocial,
//			String telf,
//			String direccion,
//			String oferta,
//			String albfact,
//			String  iva,
//			String  tarifa,
//			String formapago) throws ServiceException{
//
//		TransaccionesManager trans = null;
//		ClienteDAO clienteDAO=null;
//		try {
//
//			trans = new TransaccionesManager();
//			clienteDAO = trans.getClienteDAO();
//			clienteDAO.insertarClienteProcedure(codcli,
//					razonsocial,
//					telf,
//					direccion,
//					oferta,
//					albfact,
//					iva,
//					tarifa,
//					formapago);
//
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//	}
//	
//	
//	public int modificarClienteConcurrente(Cliente clienteActual, Cliente clienteInicial) throws ServiceException{
//		TransaccionesManager trans = null;
//		int modificar=0;
//		ClienteDAO clienteDAO=null;
//		Cliente cliente=null;
//		try {
//			
//			 trans = new TransaccionesManager();
//			clienteDAO = trans.getClienteDAO();
//			modificar = clienteDAO.modificarClienteConcurrente(clienteActual,clienteInicial);
//            if(modificar==0){
//            	throw new DAOException("El cliente ha sido modificado por otro usuario");
//            }
//        
//            
//            
//            /* otra forma con bloqueo explícito  del cliente inicial en la transacción
//            * ****************************************************
//            cliente=clienteDAO.recuperarClienteBloqueo(clienteInicial);
//            if(cliente!= null && cliente.toString().equals(clienteInicial.toString())){
//            	modificar = clienteDAO.modificarCliente(clienteActual);
//            } else{
//            	throw new DAOException("El cliente ha sido modificado por otro usuario");
//            }
//            */
//            	
//            	
//            
//            
//            	
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//		
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return modificar;
//	}
//	public int modificarCliente(Cliente cliente) throws ServiceException{
//		TransaccionesManager trans = null;
//		int modificar=0;
//		try {
//			 trans = new TransaccionesManager();
//			ClienteDAO clienteDAO = trans.getClienteDAO();
//			modificar = clienteDAO.modificarCliente(cliente);
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//		
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return modificar;
//	}
//
//	
//	public int borrarCliente(Cliente cliente) throws ServiceException{
//		TransaccionesManager trans = null;
//		int borrado=0;
//		try {
//			trans = new TransaccionesManager();
//			ClienteDAO clienteDAO = trans.getClienteDAO();
//			borrado = clienteDAO.borrarCliente(cliente);
//
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return borrado;
//	}
//
//	public Cliente recuperarClienteCompleto(Cliente cliente) throws ServiceException{
//		TransaccionesManager trans = null;
//
//		try {
//			trans = new TransaccionesManager();
//			ClienteDAO clientedao = trans.getClienteDAO();
//			cliente = clientedao.recuperarCliente(cliente);
//            if(cliente!=null){
//			IvaDAO ivadao = trans.getIvaDAO();
//			Iva iva = ivadao.recuperarIva(cliente.getIva());
//
//			TarifaDAO tarifadao = trans.getTarifaDAO();
//			Tarifa tarifa = tarifadao.recuperarTarifa(cliente.getTarifa());
//
//			FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
//			FormaPago formapago = formapagodao.recuperarFormaPago(cliente.getFormaPago());
//			// completo el cliente
//			cliente.setIva(iva);
//			cliente.setTarifa(tarifa);
//			cliente.setFormaPago(formapago);
//
//            }else{
//            	cliente=null;
//			trans.closeCommit();
//            }
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return cliente;
//	}
//	public Cliente recuperarClienteCompletoById(String codCli) throws ServiceException{
//		TransaccionesManager trans = null;
//        Cliente cliente=null;
//		try {
//			trans = new TransaccionesManager();
//			ClienteDAO clientedao = trans.getClienteDAO();
//			cliente = clientedao.recuperarClienteById(codCli);
//            if(cliente!=null){
//			IvaDAO ivadao = trans.getIvaDAO();
//			Iva iva = ivadao.recuperarIva(cliente.getIva());
//
//			TarifaDAO tarifadao = trans.getTarifaDAO();
//			Tarifa tarifa = tarifadao.recuperarTarifa(cliente.getTarifa());
//
//			FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
//			FormaPago formapago = formapagodao.recuperarFormaPago(cliente.getFormaPago());
//			// completo el cliente
//			cliente.setIva(iva);
//			cliente.setTarifa(tarifa);
//			cliente.setFormaPago(formapago);
//            }
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return cliente;
//	}
//	public Cliente recuperarCliente(Cliente cliente) throws ServiceException{
//		TransaccionesManager trans = null;
//
//		try {
//			trans = new TransaccionesManager();
//			ClienteDAO clientedao = trans.getClienteDAO();
//			cliente = clientedao.recuperarCliente(cliente);
//
//
//
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return cliente;
//	}
//
//	public List<Cliente> recuperarTodosClienteCompleto() throws ServiceException{
//		TransaccionesManager trans = null;
//		List<Cliente> list = new ArrayList<Cliente>();
//		try {
//
//			trans = new TransaccionesManager();
//			ClienteDAO clienteDAO = trans.getClienteDAO();
//			list = clienteDAO.recuperarTodosCliente();
//			if(list.size()!=0){		
//				for(int i=0;i<list.size();i++){
//					IvaDAO ivadao = trans.getIvaDAO();
//					Iva iva = ivadao.recuperarIva(list.get(i).getIva());
//					TarifaDAO tarifadao = trans.getTarifaDAO();
//					Tarifa tarifa = tarifadao.recuperarTarifa(list.get(i).getTarifa());
//					FormaPagoDAO formapagodao = trans.getFormaPagoDAO();
//					FormaPago formapago = formapagodao.recuperarFormaPago(list.get(i).getFormaPago());
//					list.get(i).setIva(iva);
//					list.get(i).setTarifa(tarifa);
//					list.get(i).setFormaPago(formapago);
//
//				}
//			}	
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return list;
//	}
//	public List<Cliente> recuperarTodosCliente() throws ServiceException{
//		TransaccionesManager trans = null;
//		List<Cliente> list = new ArrayList<Cliente>();
//		try {
//
//			trans = new TransaccionesManager();
//			ClienteDAO clienteDAO = trans.getClienteDAO();
//			list = clienteDAO.recuperarTodosCliente();
//
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//		return list;
//	}
//	public void insertarClienteProcedure(String codcli,
//			String razonsocial,
//			String telf,
//			String direccion,
//			String oferta,
//			String albfact,
//			String  iva,
//			String  tarifa,
//			String formapago) throws ServiceException{
//
//		TransaccionesManager trans = null;
//		ClienteDAO clienteDAO=null;
//		try {
//
//			trans = new TransaccionesManager();
//			clienteDAO = trans.getClienteDAO();
//			clienteDAO.insertarClienteProcedure(codcli,
//					razonsocial,
//					telf,
//					direccion,
//					oferta,
//					albfact,
//					iva,
//					tarifa,
//					formapago);
//
//
//			trans.closeCommit();
//		} catch (DAOException e) {
//
//			try{
//				if(trans!=null)
//				trans.closeRollback();
//			}catch (DAOException e1){
//				throw new ServiceException(e.getMessage(),e1);//Error interno
//			}
//
//			if(e.getCause()==null){
//				throw new ServiceException(e.getMessage());//Error Lógico
//			}else{
//
//				throw new ServiceException(e.getMessage(),e);//Error interno
//			}
//
//		}
//	}
}
