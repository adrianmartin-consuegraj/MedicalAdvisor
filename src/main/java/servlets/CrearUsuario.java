package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Centro;
import domain.Cita;
import domain.Distrito;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioAcceso;
import servicios.ServicioCentro;
import servicios.ServicioCita;
import servicios.ServicioComentario;
import servicios.ServicioDistrito;
import servicios.ServicioEspecialidad;
import servicios.ServicioFavorito;
import servicios.ServicioServicio;
import servicios.ServicioUsuario;

/**
 * Servlet implementation class RecuperarUsuarios
 */
@WebServlet("/CrearUsuario")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CrearUsuario() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//SESIÓN 1- Iniciamos la sesión 
		HttpSession session = request.getSession();

		//Dispacher
		String salida = "";

		// - para recuperar el Usuario de la BD (siempre que coincida con el introducido en el form)
		ServicioUsuario sUsuario = new ServicioUsuario();
		Usuario usuario = new Usuario();

		String user = "";
		String password = "";
		String password2 = "";
		String distrito = "";

		List<Usuario> usuariosRecuperados = new ArrayList<Usuario>();

		// - para recuperar la ubicación (distrito) del Usuario
		ServicioDistrito sDistrito = new ServicioDistrito();

		// - para recuperar los Centros Favoritos del Usuario
		// - primero recuperamos los "id de los Centros" favoritos asociados al Usuario de la tabla FAVORITOS
		ServicioFavorito sFavorito = new ServicioFavorito();
		List<Integer> idCentrosFav = new ArrayList<Integer>();

		// - ahora recuperamos todos los datos de los centros asociados a los "id de los Centros" de la tabla CENTROS
		ServicioCentro sCentro = new ServicioCentro();

		// - para recuperar las citas programadas del Usuario
		ServicioCita sCita = new ServicioCita();
		Cita cita = new Cita();
		List <Cita> citasRecuperadas = new ArrayList<Cita>();



		// - para recuperar todos los Centros de la BD
		//ServicioCentro sCentro = new ServicioCentro();
		List<Centro> centros = new ArrayList<Centro>();

		// - para recuperar los detalles de todos los Centros
		ServicioAcceso sAcceso = new ServicioAcceso();
		ServicioEspecialidad sEspecialidad = new ServicioEspecialidad();
		ServicioServicio sServicio = new ServicioServicio();
		ServicioComentario sComentario = new ServicioComentario();

		//condición EXISTE USUARIO
		boolean existe = false;

		//0~Recuperamos los datos del Usuario (nombre, password y password2) introducidos por el form en RegistrarUsuario.jsp

		try {

			user = (String) request.getParameter("inputUsuario");
			password = (String) request.getParameter("inputPassword");
			password2 = (String) request.getParameter("inputPassword2");
			distrito = (String) request.getParameter("inputDistrito");
			
			Integer numDistrito = Integer.valueOf(distrito);
			
			Distrito distr = new Distrito();
			distr.setNumero(numDistrito);
			
		
//			System.out.println("USUARIO: " + user);
//			System.out.println("PASSWORD: " + password);
//			System.out.println("PASSWORD 2: " + password2);
//			System.out.println("DISTRITO STRING: " + distrito);
//			System.out.println("DISTRITO INT: " + numDistrito);

			//2~Comprobamos que las passwords coinciden

			if (!password.equals(password2)) {
				request.setAttribute("mensaje", "- Las contraseñas introducida no coinciden . . .");
				salida = ("/Fin.jsp");

			}else {

				usuario.setNombre(user);
				usuario.setPassword(password);
				usuario.setTipo("usuario");
				usuario.setUbicacion(distr);
				

				//2~Recuperamos USUARIOS de la BD
				usuariosRecuperados = sUsuario.RecuperarTodosUsuarios();

				for(int i=0; i<usuariosRecuperados.size(); i++) {
					//				System.out.println("Usuario form:" + usuario.getNombre());
					//				System.out.println("Password form:" + usuario.getPassword());
					//				System.out.println("USUARIO recuperado:" + usuariosRecuperados.get(i).getNombre());
					//				System.out.println("PASSWORD recuperada: " + usuariosRecuperados.get(i).getPassword());

					if(usuariosRecuperados.get(i).getNombre().equals(usuario.getNombre())) {
						existe = true;
						//					System.out.println("USUARIO si EXISTE");
						//usuario = usuariosRecuperados.get(i);
					}
				}



				if(existe==true) {

					request.setAttribute("mensaje", "- El usuario ya está registrado . . .");
					salida = ("/Fin.jsp");

				} else {
					//insertamos el USUARIO y PASSWORD en la BD para recuperarlo en el Servlet "ValidarUsuario"
					
					sUsuario.insertarUsuario(usuario);
					
					
					//*Guardamos el objeto USUARIO en la Sesión
					session.setAttribute("usuario", usuario);

					salida = ("/ValidarUsuario");


				}

			}



		} catch (DomainException | ServiceException e) {

			if(e.getCause()==null){
				salida=("/Fin.jsp"); //Poner en salida = "/Inicio.jsp"
				request.setAttribute("mensaje", e.getMessage() );
			}else{
				salida=("/Fin.jsp");
				request.setAttribute("mensaje","Error interno");//Error interno
			}
		}

		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}

}
