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
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCentro;
import servicios.ServicioCita;
import util.Fecha;


/**
 * Servlet implementation class AñadirCita
 */
@WebServlet("/PedirCita")
public class PedirCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedirCita() {
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
	
		//0~Recuperamos los datos de la cita a añadir (centro y fecha) introducidos por el form en NuevaCita.jsp
		String centro = request.getParameter("inputCentro");
		String especialidad = request.getParameter("inputEspecialidad");
		String fechaInput = request.getParameter("inputFecha");
		
		Date fecha = null;
		
		//Fecha
				try {
					fecha = Fecha.convertirADate(fechaInput, "yyyy-MM-dd");
					
				} catch (ParseException e) {
					salida=("/NuevaCita.jsp");
					request.setAttribute("mensaje","Formato fecha erróneo.");//Error interno
				}
		
	
		
		try {
			
			System.out.println("CITA NUEVA =========================");
			System.out.println("· usuario: " + usuario.getNombre());
			System.out.println("· centro: " + centro);
			System.out.println("· especialidad: " + especialidad);
			System.out.println("· fecha: " + fecha);
			//System.out.println("~ fechaInput: " + fechaInput);
			
			//Insertamos la cita
			sCita.pedirCita(usuario.getNombre(), centro, especialidad, fecha);
		
	
				try {
				
					//4~Recuperamos CITAS del usuario
					usuario.setCitas(sCita.RecuperarCitasUsuario(usuario.getNombre()));
					
					
					if(usuario.getCitas().size()>0) {
						//CITAS = SI existen
				
//						for (int i=0; i<usuario.getCitas().size(); i++) {
//							System.out.println("CITAS RECUPERADAS: " + usuario.getCitas().get(i).getIdCita() + " a las " + usuario.getCitas().get(i).getFecha() + " en " + usuario.getCitas().get(i).getCentro());
//						}

					} else {
						//CITAS = NO existenSS
						//session.setAttribute("citas", "No hay citas");
					}
					
					
				}catch(ServiceException e) {
					//request.setAttribute("citas", "No existen citas");
				}
				
			
			//*Guardamos el objeto USUARIO en la Sesión
			session.setAttribute("usuario", usuario);
			
			salida = ("/PerfilUsuario.jsp");
				
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
