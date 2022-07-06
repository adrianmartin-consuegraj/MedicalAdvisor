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
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCentro;
import servicios.ServicioCita;

/**
 * Servlet implementation class RecuperarCentros
 */
@WebServlet("/RecuperarCitasParaGestionar")
public class RecuperarCitasParaGestionar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarCitasParaGestionar() {
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
		
		// - para recuperar las Citas a Gestionar de la BD
		ServicioCita sCita = new ServicioCita();
		List<Cita> gestionCitas = new ArrayList<Cita>();
	
		
		try {
			
			System.out.println("Entra bien por aqui = RECUPERAR CITA para GESTIONAR");
			
			gestionCitas = sCita.RecuperarTodasCitasPorGestionar();
			
			//*Guardamos los CITAS en la Sesión
			session.setAttribute("gestionCitas", gestionCitas);
			
				//COMPROBACIÓN
				for (int i=0; i<gestionCitas.size(); i++){
					System.out.println("CITA " + (i+1) + ": "+ gestionCitas.get(i).getUsuario() + " en " + gestionCitas.get(i).getCentro() + " con especialidad: " + gestionCitas.get(i).getEspecialidad());
				}
			
			//Dispacher
			salida = ("/GestionCitas.jsp");
				
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
