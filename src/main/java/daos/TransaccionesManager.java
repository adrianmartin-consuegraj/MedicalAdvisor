package daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bbdd.ConexionMySql;
import bbdd.ConexionOracle;

import exceptions.DAOException;
import exceptions.ServiceException;


public class TransaccionesManager {

	private static final String DB_ERR = "Error de la base de datos";
	private static final String DB_CON_ERR = "Error al conectar con la base de datos";

	private Connection con;

	// para  octener la conexion del pool de conexiones, ver WEB.xml
	/*	  public TransaccionesManager() throws DAOException {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/PoolDeConexiones");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		} catch (NamingException e) {
			throw new DAOException(DB_CON_ERR, e);
		}
	}
	 */
	// la conexion la establezco con la clase ConexionOracle
	public TransaccionesManager() throws DAOException {


//		con=new ConexionOracle("jdbc:oracle:thin:@localhost:1521:xe",
//				"adrian","adrian").getConexion();
		
		con=new ConexionMySql("jdbc:mysql://localhost:3307/medicaladvisor",
				"adrian","adrian").getConexion();
		
		try {	
			con.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException( DB_ERR ,e);	
		}
	}
	
	
	public void closeCommit()throws DAOException  {
		try {
			if(con!=null){
				con.commit();
				con.close();
			}


		} catch (SQLException e) {
			throw new DAOException (DB_ERR, e);
		}
	}
	public void commit()throws DAOException  {
		try {
			if(con!=null){
				con.commit();

			}


		} catch (SQLException e) {
			throw new DAOException (DB_ERR, e);
		}
	}
	public void closeRollback() throws DAOException {
		try {
			if(con!=null){
				con.rollback();
				con.close();
			}

		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		}
	}
	public void rollback() throws DAOException {
		try {
			if(con!=null){
				con.rollback();

			}

		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		}
	}
	public void close() throws DAOException {
		try {
			if(con!=null){
				con.close();

			}

		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		}
	

	}
	public Savepoint savepoint()throws DAOException{
		Savepoint s = null;
		try{
			if(con != null){
				s = con.setSavepoint();
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		}
		return s;
	}
	public void deshacerHastaSavepoint(Savepoint s)throws DAOException{
		try{
			if(con != null){
				con.rollback(s);
			}
		} catch (SQLException e) {
			throw new DAOException(DB_ERR, e);
		}
	}
	public Connection getConexion() {

		return con;
	}

	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAO(con);
	}
	
	public CitaDAO getCitaDAO() {
		return new CitaDAO(con);
	}
	
	public DistritoDAO getDistritoDAO() {
		return new DistritoDAO(con);
	}
	
	public FavoritoDAO getFavoritoDAO() {
		return new FavoritoDAO(con);
	}
	
	public CentroDAO getCentroDAO() {
		return new CentroDAO(con);
	}
	
	public VacunaDAO getVacunaDAO() {
		return new VacunaDAO(con);
	}
	
	
	public AccesoDAO getAccesoDAO() {
		return new AccesoDAO(con);
	}
	
	public EspecialidadDAO getEspecialidadDAO() {
		return new EspecialidadDAO(con);
	}
	
	public ServicioDAO getServicioDAO() {
		return new ServicioDAO(con);
	}
	
	public ComentarioDAO getComentarioDAO() {
		return new ComentarioDAO(con);
	}
	
	public ValoracionDAO getValoracionDAO() {
		return new ValoracionDAO(con);
	}
	
//	public SugerenciaDAO getSugerenciaDAO() {
//		return new SugerenciaDAO(con);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public IvaDAO getIvaDAO() {
//		return new IvaDAO(con);
//
//	}
//	public FormaPagoDAO getFormaPagoDAO() {
//		return new FormaPagoDAO(con);
//
//	}
//	public TarifaDAO getTarifaDAO() {
//		return new TarifaDAO(con);
//
//	}
//	public daos.ProveedorDAO getProveedorDAO() {
//	
//		return new ProveedorDAO(con);
//	}
//	public daos.ArticuloDAO getArticuloDAO() {
//		
//		return new ArticuloDAO(con);
//	}
//	public daos.FamiliaDAO getFamiliaDAO() {
//		
//		return new FamiliaDAO(con);
//	}
//	public daos.ExistenciaDAO getExistenciaDAO() {
//	
//		return  new ExistenciaDAO(con);
//	}
//	public daos.AlmacenDAO getAlmacenDAO() {
//		
//		return new AlmacenDAO(con);
//	}
//	//pedidos
//	public PedidoDAO getPedidoDAO() {
//		
//		return new PedidoDAO(con);
//	}
//	// LinPed
//	public LinPedDAO getLinPedDAO() {
//		
//		return new LinPedDAO(con);
//	}
//
//
//	public AlbaranDAO getAlbaranDAO() {
//		
//		return new AlbaranDAO(con);
//	}
//	public LinAlbDAO getLinAlbDAO() {
//		
//		return new LinAlbDAO(con);
//	}
//	public ContadorFactDAO getContadorFactDAO() {
//		
//		return new ContadorFactDAO(con);
//	}
//	public FacturaDAO getFacturaDAO() {
//		
//		return new FacturaDAO(con);
//	}
//	public ReciboDAO getReciboDAO() {
//		
//		return new ReciboDAO(con);
//	}





}
