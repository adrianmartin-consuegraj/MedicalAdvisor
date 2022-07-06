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
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCentro;

/**
 * Servlet implementation class RecuperarCentros
 */
@WebServlet("/RecuperarCentrosPorTipo")
public class RecuperarCentrosPorTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperarCentrosPorTipo() {
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
		
		//
		
		//Recuperamos el usuario
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.getAttribute("usuario");
		
		//Dispacher
		String salida = "";
		
		// - para recuperar los Centros de la BD
		ServicioCentro sCentro = new ServicioCentro();
		List<Centro> centros = new ArrayList<Centro>();
	
		//0~Recuperamos el tipo de Centro
		String tipo = request.getParameter("tipo");
		
		System.out.println("TIPO RECUPERADO: " + tipo);
		
		
		try {
			
			System.out.println("Entra bien por aqui = RECUPERAR CENTROS");
			
			centros = sCentro.RecuperarCentrosPorTipo(tipo);
			
			for (int i=0; i>centros.size(); i++) {
				System.out.println("recuperado$$$$: " + centros.get(i).getNombre());
			}
	
			//*Guardamos los CENTROS en la Sesión
			session.setAttribute("centros", centros);
			
			salida = ("/Centros.jsp");
				
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
