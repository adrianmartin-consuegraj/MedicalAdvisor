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
import domain.Usuario;
import domain.Valoracion;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioAcceso;
import servicios.ServicioCentro;
import servicios.ServicioComentario;
import servicios.ServicioEspecialidad;
import servicios.ServicioServicio;
import servicios.ServicioValoracion;

/**
 * Servlet implementation class RecuperarCentros
 */
@WebServlet("/ValorarCentro")
public class ValorarCentro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValorarCentro() {
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
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.getAttribute("usuario");

		//Dispacher
		String salida = "";

		// - para recuperar los Centros de la BD
		ServicioCentro sCentro = new ServicioCentro();
		ServicioValoracion sValoracion = new ServicioValoracion();
		List<Centro> centros = new ArrayList<Centro>();
		ServicioAcceso sAcceso = new ServicioAcceso();
		ServicioEspecialidad sEspecialidad = new ServicioEspecialidad();
		ServicioServicio sServicio = new ServicioServicio();
		ServicioComentario sComentario = new ServicioComentario();

		//0~Recuperamos los datos pasados por URL (Centro y Valoración del Centro)
		String centro = request.getParameter("centro");
		int idCentro = Integer.parseInt(centro);
		String valoracion = request.getParameter("valoracion");
		int valoracionUsuario = Integer.parseInt(valoracion);

		List<Valoracion> valoraciones = new ArrayList<Valoracion>();

		boolean yaValorado = false;

		int puntuacion = 0;
		int cont = 0;

//		System.out.println("========================================================= VALORACIONES");
//		System.out.println("- usuario: " + usuario.getNombre());
//		System.out.println("- ID centro: " + centro);
//		System.out.println("- valoración: " + valoracionUsuario);

		try {

			//1. Recuperamos las Valoraciones de todos los Centros (recupera datos INT de la tabla Valoraciones)
			valoraciones = sValoracion.RecuperarValoracion();

			//			System.out.println(" +++++++++++++ Valoraciones ya existentes +++++++++++++");

			if(valoraciones.size()>0) {
				for(int i=0; i<valoraciones.size(); i++) {
					//					System.out.println("---------------- Valoración " + (i+1) + ": · ID CENTRO: " + valoraciones.get(i).getIdCentro() + ", USUARIO: " + valoraciones.get(i).getUsuario() + ", PUNTUACIÓN: " + valoraciones.get(i).getPuntuacion() + "----------------");
					if (valoraciones.get(i).getIdCentro() == idCentro && valoraciones.get(i).getUsuario().equals(usuario.getNombre())) {
						yaValorado = true;
					}
				}

			}

			if (yaValorado) {
//				System.out.println(">>>>> SI EXISTE VALORACION");

				//2a. Actualizamos la valoración del Centro seleccionado en la tabla VALORACIONES
				sValoracion.ActualizarValoracion(idCentro, usuario.getNombre(), valoracionUsuario);


			}else {
//				System.out.println(">>>>> NO EXISTE VALORACION"); //QUITAR ESTA OPCIÓN PORQUE SIEMPRE HABRÁ VALORACIÓN INICIAL

				//2b. Insertamos la valoración dada al Centro seleccionado
				sValoracion.InsertarValoracion(idCentro, usuario.getNombre(), valoracionUsuario);
//				System.out.println("Rating added properly.");
			}

//			System.out.println("A) Puntuación: " + puntuacion + " & Contador: " + cont);
			//3. Recuperamos las Valoraciones de todos los Centros (VALORES ACTUALIZADOS) ------> NO HACER ESTO PORQUE SE RECUPERA DOBLE
			valoraciones = sValoracion.RecuperarValoracion();

//			System.out.println(" ######## puntuación: " + valoracionUsuario);
//
//			System.out.println("");
//			System.out.println("________ACTUALIZACIÓN DE LA VALORACIÓN EN LA TABLA 'CENTROS'________");
//			System.out.println(" +++++++++++++ Valoraciones ya existentes de este centro +++++++++++++");


			//4a. Calculamos la puntuacion/valoración del Centro en cuestión

//			System.out.println("- antes del FOR: " + puntuacion + " & Contador: " + cont);

			if(!valoraciones.isEmpty()) {
				
				for(int i=0; i<valoraciones.size(); i++) {

//					System.out.println("==================================");
//					System.out.println("- Puntuación antes: " + puntuacion);
//					System.out.println("- Contador antes: " + cont);
//					System.out.println("- Valor recuperado del FOR (e IF): " + valoraciones.get(i).getPuntuacion());
//					System.out.println("==================================");

					if (valoraciones.get(i).getIdCentro() == idCentro) {
						
//						System.out.println("- Valor recuperado de la Base de Datos. USUARIO: " + valoraciones.get(i).getUsuario() + " & PUNTUACIÓN:" + valoraciones.get(i).getPuntuacion());

						puntuacion = puntuacion + valoraciones.get(i).getPuntuacion();
						cont++;
						
//						System.out.println("+ Puntuación después de recuperar valor de la Base de Datos: " + puntuacion);
//						System.out.println("+ Contador después de recuperar de la Base de Datos: " + cont);

					}
				}
			}

			puntuacion = puntuacion/cont;

//			System.out.println("PUNTUACION CENTRO: "+ puntuacion + " & dividio entre: "+ cont);


			//4b. Actualizamos la valoración del Centro en la tabla CENTROS
			sCentro.ModificarPuntuacionCentro(idCentro, puntuacion);

//			System.out.println("========================================================= TRAMO FINAL");

			salida = ("/ValidarUsuario");

		} catch (DomainException | ServiceException e) {

			if(e.getCause()==null){
				salida=("/Fin.jsp");
				request.setAttribute("mensaje", e.getMessage() );
			}else{
				salida=("/Fin.jsp");
				request.setAttribute("mensaje","Error interno");//Error interno
			}
		}

		getServletContext().getRequestDispatcher(salida).forward(request, response);

	}

}
