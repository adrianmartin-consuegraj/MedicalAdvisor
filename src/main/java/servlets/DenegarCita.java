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
import servicios.ServicioCentro;
import servicios.ServicioCita;
import util.Fecha;


/**
 * Servlet implementation class AñadirCita
 */
@WebServlet("/DenegarCita")
public class DenegarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DenegarCita() {
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
		
		//Dispacher
		String salida = "";
		
		// - para añadir la cita del Usuario de la BD
		ServicioCita sCita = new ServicioCita();
		Cita cita = null;
	
		//0~Recuperamos los datos de la cita a añadir (centro y fecha) introducidos por el form en NuevaCita.jsp
		String idCita = request.getParameter("idCita");
		//0~Recuperamos todas las citas
		List<Cita> citasPendientes = null;
		
		if(session.getAttribute("gestionCitas")!=null) {
			citasPendientes = (List<Cita>) session.getAttribute("gestionCitas");
			
			System.out.println("ID CITA PARA BORRAR:" + idCita);
			
			try {
				
			Integer id = Integer.parseInt(idCita);
			
			for(int i=0; i<citasPendientes.size();i++) {
				if(citasPendientes.get(i).getIdCita()==id) {
					cita = new Cita();
					cita.setIdCita(citasPendientes.get(i).getIdCita());
					cita.setUsuario(citasPendientes.get(i).getUsuario());
					cita.setCentro(citasPendientes.get(i).getCentro());
					cita.setEspecialidad(citasPendientes.get(i).getEspecialidad());
					cita.setFecha(citasPendientes.get(i).getFecha());
					
				}
			}
		
			System.out.println("ID CITA GESTIÓN A BORRAR: " + cita.getIdCita());
			sCita.EliminarCitaGestion(cita.getIdCita());
			
			
			salida = ("/RecuperarCitasParaGestionar");
				
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

}
