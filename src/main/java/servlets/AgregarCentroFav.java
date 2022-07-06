package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
import util.Fecha;


/**
 * Servlet implementation class AgregarCentroFav
 */
@WebServlet("/AgregarCentroFav")
public class AgregarCentroFav extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgregarCentroFav() {
		super();
		// TODO Auto-generated constructor stub
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

		//Recuperamos el usuario
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		//Recuperamos el ID del Centro
		String id = request.getParameter("idCentro");
		int idCentro = Integer.parseInt(id);

		//para VOLVER ATRÁS
//		String ref = request.getHeader("referer");
//		String referencia = ref.substring(36);
//		String referenciaVolver = (String) session.getAttribute("referenciaVolver");
		
		//Dispacher
		String salida = "";
//		String ref = request.getHeader("referer");
//		String referencia = ref.substring(36);
//		session.setAttribute("referencia", referencia);

		// - para añadir el Centro a Favoritos en la BD
		ServicioFavorito sFavorito = new ServicioFavorito();
		
		System.out.println("Usuario:" + usuario.getNombre());
		System.out.println("Centro: " + idCentro);
		
		boolean noExisteFav = false;

		try {
			//añadido IF (quizá este mal) --
			if (usuario.getFavoritos()!= null) {
				System.out.println(">El usuario SI tiene favoritos guardados.");

				for (int i=0; i<usuario.getFavoritos().getCentro().size(); i++) {
					
					if(usuario.getFavoritos().getCentro().get(i).getIdCentro()==idCentro) {
						System.out.println(" - El usuario ya tiene guardado este centro.");
						System.out.println("   - idCentro usuario: " + usuario.getFavoritos().getCentro().get(i).getIdCentro() + " | idCentro seleccionado: " + idCentro);
						salida = "/Fin.jsp";
						request.setAttribute("mensaje", "El Centro ya está agregado a Favoritos");
						getServletContext().getRequestDispatcher(salida).forward(request, response);
		
					} else {
						//Insertamos el Centro en Favoritos
						System.out.println(" - El usuario no tiene guardado este centro.");
						System.out.println("   - idCentro usuario: " + usuario.getFavoritos().getCentro().get(i).getIdCentro() + " | idCentro seleccionado: " + idCentro);
						noExisteFav = true;
						
						
					}

				}


				// añadido ELSE (quizá este mal) --
			} else {
				//Insertamos el Centro en Favoritos
				System.out.println(">El usuario NO tiene favoritos guardados.");
				sFavorito.AgregarCentroFavPorId(usuario.getNombre(), idCentro);

				salida = "/ValidarUsuario";
			}
			
			if (noExisteFav) {
				sFavorito.AgregarCentroFavPorId(usuario.getNombre(), idCentro);

				salida = "/ValidarUsuario";
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
