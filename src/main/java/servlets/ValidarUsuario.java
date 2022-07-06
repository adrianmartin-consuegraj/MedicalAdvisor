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
@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidarUsuario() {
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

		//0~Recuperamos los datos del Usuario (nombre y contraseña) introducidos por el form en Index.jsp
		try {

			if(session.getAttribute("usuario")!=null) {

				//			System.out.println("USUARIO YA EXISTE");
				usuario = (Usuario) session.getAttribute("usuario");

			} else {
				//			System.out.println("USUARIO RECUPERADO POR 1A VEZ");
				usuario.setNombre(request.getParameter("inputUsuario"));
				usuario.setPassword(request.getParameter("inputPassword"));

			}

			//1~Recuperamos USUARIO de la BD
			usuariosRecuperados = sUsuario.RecuperarTodosUsuarios();

			for(int i=0; i<usuariosRecuperados.size(); i++) {
//				System.out.println("Usuario form:" + usuario.getNombre());
//				System.out.println("Password form:" + usuario.getPassword());
//				System.out.println("USUARIO recuperado:" + usuariosRecuperados.get(i).getNombre());
//				System.out.println("PASSWORD recuperada: " + usuariosRecuperados.get(i).getPassword());

				if(usuariosRecuperados.get(i).getNombre().equals(usuario.getNombre()) && usuariosRecuperados.get(i).getPassword().equals(usuario.getPassword())) {
					existe = true;
//					System.out.println("USUARIO si EXISTE");
					usuario = usuariosRecuperados.get(i);
				}
			}

			if(existe==true) {

//				System.out.println("USUARIO SI EXISTE --");
				//USUARIO = SI existe

				try {

					//2~Recuperamos la UBICACION (Distrito) del usuario
					Distrito distritoRecuperado = sDistrito.RecuperarDistrito(usuario.getUbicacion());
					usuario.setUbicacion(distritoRecuperado);

					//System.out.println("=================================================");
					//System.out.println("DISTRITO RECUPERADO: " + usuario.getUbicacion().getNombre());

					//3a~Recuperamos los centros FAVORITOS del usuario
					idCentrosFav = sFavorito.RecuperarFavoritos(usuario.getNombre());

					//				for(int i=0; i<idCentrosFav.size(); i++) {
					//					System.out.println("VALORES INTEGER FAVS: " + idCentrosFav.get(i));
					//				}

					if (idCentrosFav.size()>0) {

						//3b~Recuperamos los datos (por el idCentro) de los Centros favoritos del Usuario
						usuario.setFavoritos(sCentro.RecuperarCentrosPorIdCentro(idCentrosFav));

						//System.out.println("Nº CENTROS FAVS recuperados: " + usuario.getFavoritos().getCentro().size());

						//					for (int i=0; i<usuario.getFavoritos().getCentro().size(); i++) {
						//						System.out.println("CENTRO RECUPERADO: " + usuario.getFavoritos().getCentro().get(i).getNombre());
						//					}
					} else {
						//FAVS = NO existen
						usuario.setFavoritos(null);
						//System.out.println("NO EXISTEN FAVS");
						//session.setAttribute("favs", "No hay favoritos");

					}

				}catch (ServiceException e) {
					request.setAttribute("favs", "No hay favoritos");
				}


				try {

					//4~Recuperamos CITAS del usuario
					citasRecuperadas = sCita.RecuperarCitasUsuario(usuario.getNombre());

					if(citasRecuperadas!=null) {
//						System.out.println("Si hay citas");

						usuario.setCitas(citasRecuperadas);


						//CITAS = SI existen
						//						System.out.println("EXISTEN CITAS");

						//						for (int i=0; i<usuario.getCitas().size(); i++) {
						//							System.out.println("CITAS RECUPERADAS: " + usuario.getCitas().get(i).getIdCita() + " a las " + usuario.getCitas().get(i).getFecha() + " en " + usuario.getCitas().get(i).getCentro());
						//						}

					} else {
//						System.out.println("No hay citas");
						usuario.setCitas(null);
						//CITAS = NO existen
						//						System.out.println("NO EXISTEN CITAS");
						//session.setAttribute("citas", "No hay citas");
						//						request.setAttribute("mensaje", "- No existen citas . . .");
						//						salida = ("/Fin.jsp");
					}


					//5a~Recuperamos todos los CENTROS
					//System.out.println("Entra bien por aqui = RECUPERAR CENTROS (BASE)");

					centros = sCentro.RecuperarCentros();

					//				for (int i=0; i>centros.size(); i++) {
					//					System.out.println("recuperado$$$$: " + centros.get(i).getNombre());
					//				}


					try {
						//5b~Recuperamos los detalles de todos los CENTROS

						//					System.out.println("Entra bien por aqui = RECUPERAR CENTROS (EXTRAS)");

						for (int i=0; i<centros.size(); i++) {

							centros.get(i).setAcceso(sAcceso.RecuperarAccesoPorIdCentro(centros.get(i).getIdCentro()));
							centros.get(i).setEspecialidades(sEspecialidad.RecuperarEspecialidadesPorIdCentro(centros.get(i).getIdCentro()));
							centros.get(i).setServicio(sServicio.RecuperarServicioPorIdCentro(centros.get(i).getIdCentro()));
							centros.get(i).setComentario(sComentario.RecuperarComentariosPorIdCentro(centros.get(i).getIdCentro()));

							//						System.out.println("Acceso en Metro: " + centros.get(i).getAcceso().getMetro());
							//						
							//							for(int k=0; k<centros.get(i).getEspecialidades().size(); k++) {
							//								
							//								System.out.println("Especialidades: " + centros.get(i).getNombre() + " tiene la especialidad: " + centros.get(i).getEspecialidades().get(k).getEspecialidad());
							//							}
							//							
							//						System.out.println("Servicio Restaurantes de Centro: " + centros.get(i).getServicio().getRestaurantes());
							//						
							//							for(int j=0; j<centros.get(i).getComentario().size(); j++) {
							//							
							//								System.out.println("Comentario: " + centros.get(i).getNombre() + " tiene el COMENTARIO: " + centros.get(i).getComentario().get(j).getMensaje());
							//							}

						}

						//					System.out.println("TODO GUT");

					}catch(ServiceException e) {
						request.setAttribute("centros", "No existen centros");
					}




				}catch(ServiceException e) {
					request.setAttribute("mensaje", "- No existen citas . . .");
					salida = ("/Fin.jsp");
				}

				//*Guardamos el objeto USUARIO en la Sesión
				session.setAttribute("usuario", usuario);

				//*Guardamos todos los CENTROS
				session.setAttribute("centros", centros);

				salida = ("/Inicio.jsp");

			} else {
//				System.out.println("Sale por aqui cuando no insertas nada!!!!!!!!!!!!!!!!!!!");
				//USUARIO = NO existe
//				System.out.println("USUARIO NO EXISTE -- ");
				//throw new ServiceException("- El usuario no existe");
				request.setAttribute("mensaje", "- El usuario no existe . . .");
				salida = ("/Fin.jsp");

			}



		} catch (DomainException | ServiceException e) {

			if(e.getCause()==null){
				salida=("/Fin.jsp"); //Poner en salida = "/Inicio.jsp"
				request.setAttribute("mensaje", e.getMessage() );
			}else{
				salida=("/Fin.jsp");
				request.setAttribute("mensaje","Error interno");//Error interno
				e.printStackTrace();
			}
		}

		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}

}
