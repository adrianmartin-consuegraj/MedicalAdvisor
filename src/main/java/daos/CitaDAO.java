package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import recursos.DbQuery;
import recursos.Recursos;
import daos.interfaces.ICitaDAO;
import domain.Cita;
import exceptions.DAOException;

public class CitaDAO implements ICitaDAO {
	
	
	private Connection con;

	public CitaDAO(Connection con) {
		this.con = con;
	}

	@Override
	public void añadirCita(String usuario, String centro, String especialidad, Date fecha) throws DAOException {
		
		PreparedStatement st = null;
		PreparedStatement sti = null;
		//ResultSet rs=null;
		
		try {
			st = con.prepareStatement(DbQuery.getInsertarCita());
			//st.setString(1, "");
			st.setString(1, usuario);
			st.setString(2, centro);
			st.setString(3, especialidad);
			st.setDate(4, fecha);
			// rutina de verificacion de mas de una FK
			
			// ejecutamos el insert
			st.executeUpdate();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" cliente ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}
	}
	
	
	
	
	public void pedirCita(String usuario, String centro, String especialidad, Date fecha) throws DAOException {
		
		PreparedStatement st = null;
		PreparedStatement sti = null;
		//ResultSet rs=null;
		
		try {
			st = con.prepareStatement(DbQuery.getPedirCita());
			//st.setString(1, "");
			st.setString(1, usuario);
			st.setString(2, centro);
			st.setString(3, especialidad);
			st.setDate(4, fecha);
			// rutina de verificacion de mas de una FK
			
			// ejecutamos el insert
			st.executeUpdate();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
				throw new DAOException(" cliente ya existe");
			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
			Recursos.closePreparedStatement(sti);
		}
	}
	
	
	public List<Cita> recuperarTodasCitasPorGestionar() throws DAOException {
		
		PreparedStatement st = null;
		ResultSet rs = null ;
		List<Cita> citas = new ArrayList <Cita>();
		
			try {
				
				st = con.prepareStatement(DbQuery.getRecuperarTodasCitasPorGestionar());
				rs=st.executeQuery();
				
				while (rs.next()){
	
					Cita citaRecuperada = new Cita(rs.getInt(1),
							        rs.getString(2),
							        rs.getString(3),
							        rs.getString(4),
							        rs.getDate(5)); 
					
					citas.add(citaRecuperada);
				}
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return citas;
	}
	
	
	public List<Cita> recuperarCitasUsuario(String usuario) throws DAOException {
		
		PreparedStatement st = null;
		ResultSet rs = null ;
		List<Cita> citas = new ArrayList <Cita>();
		
			try {
				st = con.prepareStatement(DbQuery.getRecuperarCitasUsuario());
				st.setString(1, usuario);
				rs=st.executeQuery();
				
				while (rs.next()){
	
					Cita citaRecuperada = new Cita(rs.getInt(1),
							        rs.getString(2),
							        rs.getString(3),
							        rs.getDate(4)); 
					
					citas.add(citaRecuperada);
				}
				
				
			} catch (SQLException e) {
				throw new DAOException(DB_ERR, e);
			} finally {
			Recursos.closeResultSet(rs);
			Recursos.closePreparedStatement(st);
			
		}
		
		return citas;
	}

	
	

	@Override
	public int borrarCita(Cita cita) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
	@Override
	public void eliminarCitaPorId(String cita) throws DAOException {
		
		PreparedStatement st = null;
		//int borrado = 0;
		
		try {
			st = con.prepareStatement(DbQuery.getEliminarCitaPorId());
			st.setString(1, cita);
			st.executeUpdate();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" DAO: No permitido borrar Cita");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		//return borrado;
		
	}
		
		
	
	public void eliminarCitaGestionPorId(int idCita) throws DAOException {
		
		PreparedStatement st = null;
		//int borrado = 0;
		
		try {
			st = con.prepareStatement(DbQuery.getEliminarCitaGestionPorId());
			st.setInt(1, idCita);
			st.executeUpdate();
			
		} catch (SQLException e) {
			if (e.getErrorCode() == ORACLE_DELETE_FK) {
				throw new DAOException(" DAO: No permitido borrar Cita");
				
			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
			    throw new DAOException(cadena1);
			} else {
				throw new DAOException(DB_ERR, e);
			}
		} finally {
			Recursos.closePreparedStatement(st);
		}
		
	}
		
		
		

	@Override
	public int modificarCita(Cita cita) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cita recuperarCita(Cita cita) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cita recuperarCitaPorId(String cita) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cita> eliminarCitaPorId() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	

	

	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void insertarCliente(Cliente cliente)throws DAOException{
//		PreparedStatement st = null;
//		PreparedStatement sti = null;
//		ResultSet rs=null;
//		try {
//			st = con.prepareStatement(DbQuery.getInsertarCliente());
//			st.setString(1, cliente.getCodCli());
//			st.setString(2, cliente.getRazonSocial());
//			st.setString(3, cliente.getTelf());
//			st.setString(4, cliente.getDireccion());
//			st.setString(5, cliente.getOferta());
//			st.setString(6, cliente.getAlbFact());
//			st.setString(7, cliente.getIva().getcodIva());
//			st.setString(8, cliente.getTarifa().getCodTarifa());
//			st.setString(9, cliente.getFormaPago().getCodigo());
//			// rutina de verificacion de mas de una FK
//			
//			
//			
//			// para el iva
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
//			  sti.setString(1, cliente.getIva().getcodIva());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" el iva del cliente no existe");
//			 }  finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//			 
//			
//			  // para la tarifa   
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarTarifa());
//			  sti.setString(1, cliente.getTarifa().getCodTarifa());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		    } finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//					 
//			  // para la forma de Pago 
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
//			  sti.setString(1, cliente.getFormaPago().getCodigo());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		     }  finally {
//			  Recursos.closeResultSet( rs);	
//		     }
//			
//			// ejecutamos el insert.			
//			st.executeUpdate();
//		} catch (SQLException e) {
//			if (e.getErrorCode() == ORACLE_DUPLICATE_PK) {
//				throw new DAOException(" cliente ya existe");
//			}else if (e.getErrorCode() ==ORACLE_FALLO_FK ){
//			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
//			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			    throw new DAOException(cadena1);
//			} else {
//				throw new DAOException(DB_ERR, e);
//			}
//		} finally {
//			Recursos.closePreparedStatement(st);
//			Recursos.closePreparedStatement(sti);
//		}	
//	}
//	
//	public void insertarClienteProcedure(String codcli,
//
//										String razonsocial,
//										String telf,
//										String direccion,
//										String oferta,
//										String albfact,
//										String iva,
//										String tarifa,
//										String formapago)throws DAOException {
//
//CallableStatement st = null;
//
//			try {
//			
//			st = con.prepareCall(DbQuery.getInsertarClienteProcedure());
//			st.setString(1, codcli);
//			st.setString(2, razonsocial);
//			st.setString(3, telf);
//			st.setString(4, direccion);
//			st.setString(5, oferta);
//			st.setString(6,albfact);
//			st.setString(7, iva);
//			st.setString(8, tarifa);
//			st.setString(9, formapago);
//			
//			
//			// ejecutamos el procedimiento.			
//			st.execute();
//			} catch (SQLException e) {
//			if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//			String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//			String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			throw new DAOException(cadena1);
//			} else {
//			throw new DAOException(DB_ERR, e);
//			}
//			} finally {
//			Recursos.closeCallableStatement(st);
//			
//			}	
//
//}	
//	public int modificarCliente(Cliente cliente)throws DAOException{
//		PreparedStatement st = null;
//		PreparedStatement sti = null;
//		ResultSet rs=null;
//		int modificado = 0;
//		try {
//			st = con.prepareStatement(DbQuery.getModificarCliente());
//			st.setString(1, cliente.getRazonSocial());
//			st.setString(2, cliente.getTelf());
//			st.setString(3, cliente.getDireccion());
//			st.setString(4, cliente.getOferta());
//			st.setString(5, cliente.getAlbFact());
//			st.setString(6, cliente.getIva().getcodIva());
//			st.setString(7, cliente.getTarifa().getCodTarifa());
//			st.setString(8, cliente.getFormaPago().getCodigo());
//			st.setString(9, cliente.getCodCli());
//			// rutina de verificacion de mas de una FK
//			
//			
//			
//			// para el iva
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
//			  sti.setString(1, cliente.getIva().getcodIva());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" el iva del cliente no existe");
//			 }  finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//			 
//			
//			  // para la tarifa   
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarTarifa());
//			  sti.setString(1, cliente.getTarifa().getCodTarifa());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		    } finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//					 
//			  // para la forma de Pago 
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
//			  sti.setString(1, cliente.getFormaPago().getCodigo());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		     }  finally {
//			  Recursos.closeResultSet( rs);	
//		     }
//			
//			// ejecutamos el insert.			
//			modificado=st.executeUpdate();
//		} catch (SQLException e) {
//			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
//			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
//			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			    throw new DAOException(cadena1);
//			} else {
//				throw new DAOException(DB_ERR, e);
//			}
//		} finally {
//			Recursos.closePreparedStatement(st);
//			Recursos.closePreparedStatement(sti);
//		}	
//		return modificado;
//	}
//
//	public int borrarCliente(Cliente cliente) throws DAOException{
//		PreparedStatement st = null;
//		int borrado = 0;
//		try {
//			st = con.prepareStatement(DbQuery.getBorrarCliente());
//			st.setString(1, cliente.getCodCli());
//			borrado = st.executeUpdate();
//		} catch (SQLException e) {
//			if (e.getErrorCode() == ORACLE_DELETE_FK) {
//				throw new DAOException(" No permitido borrar Cliente");
//				
//			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			    throw new DAOException(cadena1);
//			} else {
//				throw new DAOException(DB_ERR, e);
//			}
//		} finally {
//			Recursos.closePreparedStatement(st);
//		}
//		return borrado;
//	}
//	@Override
//	
//
//
//	public int borrarClienteById(String cliente) throws DAOException {
//		// TODO Auto-generated method stub
//		
//		PreparedStatement st = null;
//		int borrado = 0;
//		try {
//			st = con.prepareStatement(DbQuery.getBorrarCliente());
//			st.setString(1, cliente);
//			borrado = st.executeUpdate();
//		} catch (SQLException e) {
//			if (e.getErrorCode() == ORACLE_DELETE_FK) {
//				throw new DAOException(" No permitido borrar Cliente");
//				
//			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			    throw new DAOException(cadena1);
//			} else {
//				throw new DAOException(DB_ERR, e);
//			}
//		} finally {
//			Recursos.closePreparedStatement(st);
//		}
//		return borrado;
//		
//	}
//	public Cliente recuperarCliente(Cliente cliente) throws DAOException{
//		PreparedStatement st = null;
//		ResultSet rs =null ;
//		Cliente objeto=null;
//		
//			try {
//				st = con.prepareStatement(DbQuery.getRecuperarCliente());
//				st.setString(1,cliente.getCodCli() );
//				rs=st.executeQuery();
//				if (rs.next()){
//	
//					objeto=new Cliente(rs.getString(1),
//							        rs.getString(2),
//							        rs.getString(3),
//							        rs.getString(4),
//							        rs.getString(5),
//							        rs.getString(6),
//							        new Iva(rs.getString(7)),
//							        new Tarifa(rs.getString(8)),
//							        new FormaPago(rs.getString(9))); 
//					
//					
//				}		
//				
//			} catch (SQLException e) {
//				throw new DAOException(DB_ERR, e);
//			} finally {
//			Recursos.closeResultSet(rs);
//			Recursos.closePreparedStatement(st);
//			
//		}
//		
//		return objeto;
//			
//		}
//
//	@Override
//	public Cliente recuperarClienteById(String cliente) throws DAOException {
//		PreparedStatement st = null;
//		ResultSet rs =null ;
//		Cliente objeto=null;
//		
//			try {
//				st = con.prepareStatement(DbQuery.getRecuperarCliente());
//				st.setString(1,cliente );
//				rs=st.executeQuery();
//				if (rs.next()){
//	
//					objeto=new Cliente(rs.getString(1),
//							        rs.getString(2),
//							        rs.getString(3),
//							        rs.getString(4),
//							        rs.getString(5),
//							        rs.getString(6),
//							        new Iva(rs.getString(7)),
//							        new Tarifa(rs.getString(8)),
//							        new FormaPago(rs.getString(9))); 
//					
//					
//				}		
//				
//			} catch (SQLException e) {
//				throw new DAOException(DB_ERR, e);
//			} finally {
//			Recursos.closeResultSet(rs);
//			Recursos.closePreparedStatement(st);
//			
//		}
//		
//		return objeto;
//	}
//	
//	public List<Cliente> recuperarTodosCliente()throws DAOException {
//		PreparedStatement st = null;
//		ResultSet rs = null;
//		List<Cliente> list = new ArrayList<Cliente>();
//		try {
//			st = con.prepareStatement(DbQuery.getRecuperarTodosCliente());
//			rs = st.executeQuery();
//			while (rs.next()) {
//	Cliente cliente = new Cliente(rs.getString("cod_cli"),
//				                  rs.getString("razon_social"),
//				                  rs.getString("telf"),
//				                  rs.getString("direccion"),
//				                  rs.getString("oferta"),
//				                  rs.getString("alb_fact"),
//				                  new Iva(rs.getString("cod_iva")),
//				                  new Tarifa(rs.getString("cod_tarifa")),
//				                  new FormaPago(rs.getString("forma_pago"))
//						);
//				
//				list.add(cliente);
//			}
//		} catch (SQLException e) {
//			throw new DAOException(DB_ERR, e);
//		} finally {
//			Recursos.closeResultSet(rs);
//			Recursos.closePreparedStatement(st);
//		}
//		return list;
//
//	}
//
//	public int modificarClienteConcurrente(Cliente clienteActual,
//			                              Cliente clienteInicial) 
//			                                throws DAOException {
//		PreparedStatement st = null;
//		PreparedStatement sti = null;
//		ResultSet rs=null;
//		int modificado = 0;
//		try {
//			st = con.prepareStatement(DbQuery.getModificarClienteConcurrente());
//			st.setString(1, clienteActual.getRazonSocial());
//			st.setString(2, clienteActual.getTelf());
//			st.setString(3, clienteActual.getDireccion());
//			st.setString(4, clienteActual.getOferta());
//			st.setString(5, clienteActual.getAlbFact());
//			st.setString(6, clienteActual.getIva().getcodIva());
//			st.setString(7, clienteActual.getTarifa().getCodTarifa());
//			st.setString(8, clienteActual.getFormaPago().getCodigo());
//			
//			// para los where   -- ojo con campos que pueden ser nulos
//			
//			st.setString(9, clienteInicial.getCodCli());
//			st.setString(10, clienteInicial.getRazonSocial());
//			if(clienteInicial.getTelf()== null)
//				st.setString(11, "null");	
//			else
//			st.setString(11, clienteInicial.getTelf());
//			st.setString(12, clienteInicial.getDireccion());
//			st.setString(13, clienteInicial.getOferta());
//			st.setString(14, clienteInicial.getAlbFact());
//			st.setString(15, clienteInicial.getIva().getcodIva());
//			st.setString(16, clienteInicial.getTarifa().getCodTarifa());
//			st.setString(17, clienteInicial.getFormaPago().getCodigo());
//			
//			// rutina de verificacion de mas de una FK
//			
//			
//			
//			// para el iva
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarIva());
//			  sti.setString(1, clienteActual.getIva().getcodIva());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" el iva del cliente no existe");
//			 }  finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//			 
//			
//			  // para la tarifa   
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarTarifa());
//			  sti.setString(1, clienteActual.getTarifa().getCodTarifa());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		    } finally {
//				  Recursos.closeResultSet( rs);	
//			 }
//					 
//			  // para la forma de Pago 
//			try{
//			  sti = con.prepareStatement(DbQuery.getRecuperarFormaPago());
//			  sti.setString(1, clienteActual.getFormaPago().getCodigo());
//			  rs=sti.executeQuery();
//			  if(!rs.next())	
//			  throw new DAOException(" La Tarifa del  cliente no existe");
//		     }  finally {
//			  Recursos.closeResultSet( rs);	
//		     }
//			
//			// ejecutamos el insert.			
//			modificado=st.executeUpdate();
//		} catch (SQLException e) {
//			 if (e.getErrorCode() ==ORACLE_FALLO_FK ){
//			   throw new DAOException("Operacion no disponible temporalmente,repita proceso");
//			}else if  (e.getErrorCode()>=20000 && e.getErrorCode()<=20999){
//				String cadena=e.toString().substring(e.toString().indexOf("ORA", 0)+10);
//				String cadena1=cadena.substring(0,cadena.indexOf("ORA", 0));
//			    throw new DAOException(cadena1);
//			} else {
//				throw new DAOException(DB_ERR, e);
//			}
//		} finally {
//			Recursos.closePreparedStatement(st);
//			Recursos.closePreparedStatement(sti);
//		}	
//		return modificado;
//		
//		
//	}


	
	

	

	
	
	
}
