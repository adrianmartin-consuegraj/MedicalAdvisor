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
import domain.Usuario;
import exceptions.DomainException;
import exceptions.ServiceException;
import servicios.ServicioCentro;
import servicios.ServicioFavorito;


/**
 * Servlet implementation class EliminarCita
 */
@WebServlet("/EliminarCentroFav")
public class EliminarCentroFav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCentroFav() {
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
		Usuario usuario = new Usuario();
		usuario = (Usuario) session.getAttribute("usuario");
		
		//Dispacher
		String salida = "";
		
		// - para borrar los Centros Favs del Usuario de la BD
		ServicioFavorito sFavorito = new ServicioFavorito();
		List<Integer> idCentrosFav = new ArrayList<Integer>();
		ServicioCentro sCentro = new ServicioCentro();
	
		//0~Recuperamos los datos del Centro a eliminar (nombre y contraseña) introducidos por el form en Index.jsp
		String idRecuperado = request.getParameter("idCentro");
		int idCentro = Integer.parseInt(idRecuperado);
		
		System.out.println("Centro RECUPERADO PARA BORRAR: " + idCentro);
		System.out.println("Usuario: " + usuario.getNombre());
		
		try {
			
			System.out.println("Entra bien por aqui 0");
			
			sFavorito.EliminarCentroFavPorId(usuario.getNombre(), idCentro);
	
			try {
				System.out.println("Entra bien por aqui 1");
				//3a~Recuperamos los centros FAVORITOS del usuario
				idCentrosFav = sFavorito.RecuperarFavoritos(usuario.getNombre());
			
//				for(int i=0; i<idCentrosFav.size(); i++) {
//					System.out.println("VALORES INTEGER FAVS: " + idCentrosFav.get(i));
//				}
				
				System.out.println("Bien hecho hasta aqui 1");
				
				if (idCentrosFav!=null) {
					
					//3b~Recuperamos los datos (por el idCentro) de los Centros favoritos del Usuario
					usuario.setFavoritos(sCentro.RecuperarCentrosPorIdCentro(idCentrosFav));
					
//					System.out.println("Nº CENTROS FAVS recuperados: " + usuario.getFavoritos().getCentro().size());
//					
//					for (int i=0; i<usuario.getFavoritos().getCentro().size(); i++) {
//						System.out.println("CENTRO RECUPERADO: " + usuario.getFavoritos().getCentro().get(i).getNombre());
//					}
				} else {
					//FAVS = NO existen
					System.out.println("NO EXISTEN FAVS");
					session.setAttribute("favs", "No hay favoritos");
					
				}
					
				}catch (ServiceException e) {
					request.setAttribute("favs", "No hay favoritos");
				}
			
			System.out.println("Bien hecho TODO");
				
			
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
