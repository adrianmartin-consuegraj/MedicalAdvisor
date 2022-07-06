package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCita;


/**
 * Servlet implementation class EliminarCita
 */
@WebServlet("/EliminarCita")
public class EliminarCita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCita() {
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
		
		//Recuperamos el usuario
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		//Dispacher
		String salida = "";
		
		// - para borrar la cita del Usuario de la BD
		ServicioCita sCita = new ServicioCita();
	
		//0~Recuperamos los datos de la cita a eliminar (nombre y contraseña) introducidos por el form en Index.jsp
		String idCita = request.getParameter("cita");
		//System.out.println("CITA RECUPERADA PARA BORRAR: " + idCita);
		
		try {
			
			sCita.EliminarCita(idCita);
		
	
				try {
				
					//4~Recuperamos CITAS del usuario
					usuario.setCitas(sCita.RecuperarCitasUsuario(usuario.getNombre()));
					
					
					if(usuario.getCitas().size()>0) {
						//CITAS = SI existen
						System.out.println("EXISTEN CITAS RECUPERADAS (DESPUES DE BORRAR UNA CITA)");
				
//						for (int i=0; i<usuario.getCitas().size(); i++) {
//							System.out.println("CITAS RECUPERADAS: " + usuario.getCitas().get(i).getIdCita() + " a las " + usuario.getCitas().get(i).getFecha() + " en " + usuario.getCitas().get(i).getCentro());
//						}

					} else {
						//CITAS = NO existen
						System.out.println("NO EXISTEN CITAS (DESPUES DE BORRAR UNA CITA)");
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
