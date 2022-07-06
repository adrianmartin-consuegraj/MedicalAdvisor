<%@page import="domain.Cita"%>
<%@page import="domain.Usuario"%>
<%@page import="domain.Centro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page errorPage="ErrorInterno.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>MedicalAdvisor</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

</head>
<body>

	<%
	
	//Sesión - Recuperamos Usuario
	Usuario usuario = (Usuario) session.getAttribute("usuario");
	
	//Sesión - Recuperamos el objeto citasPendientes (gestionCitas)
	//List<Centro> centros = (List<Centro>) session.getAttribute("centros");
	List<Cita> citasPendientes = null;
	
	if (session.getAttribute("gestionCitas")!=null){
		citasPendientes = (List<Cita>) session.getAttribute("gestionCitas");
	} 
		
	
	%>
	
	<!-- ======= Top Bar ======= -->
	<br>
	<div id="topbar" class="d-flex align-items-center fixed-top"
		style="justify-content: end;">
		<div class="contact-info d-flex align-items-center">
			<i class="bi bi-envelope"></i>Hola<a href="PerfilUsuario.jsp"
				class="nav-link scrollto"><%=usuario.getNombre().toUpperCase()%></a>
			<a href="CerrarSesion" class="appointment-btn scrollto text-light"
				style="margin: 1px";><span class="d-none d-md-inline"></span>Desconectarse</a>

			<% 	
					if (usuario.getCitas().size()>0){					
						%>
			<i class="bi bi-phone">Próxima Cita: <label
				class="nav-link scrollto"> <%= usuario.getCitas()==null ? usuario.getNombre() : usuario.getCitas().get(usuario.getCitas().size()-1).getFecha() %>
					en <%= usuario.getCitas().get(usuario.getCitas().size()-1).getCentro() %></label></i>
			<% 	
					} else{
						%>
						<i class="bi bi-phone">Próxima Cita:<label class="nav-link scrollto">Ninguna programada</label></i>	
			<%
					}
					%>
		</div>
	</div>

	
	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div class="container d-flex align-items-center">

			<h1 class="logo me-auto">
				<a href="index.html">MedicalAdvisor</a>
			</h1>
			<!-- Uncomment below if you prefer to use an image logo -->
			<!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<li><a class="nav-link scrollto" href="ValidarUsuario">Inicio</a></li>
					
					<!-- Hospital ACTIVE link -->
					
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>	
						
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Clinicas">Clínicas</a></li>
						
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>
						

					<li class="dropdown"><a class="active" href="# active"><span>Admin</span> <i class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a class="nav-link scrollto"
								href="RecuperarCitasParaGestionar">Gestión Citas</a></li>
						</ul></li>
					

				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav>
			<!-- .navbar -->
		</div>
	</header>
	<!-- End Header -->

	<!-- ############################################################################################################### -->
	
	
	
	<!-- ======= Services Section ======= -->
    <section id="services" class="services">
      <div class="container">

        <div class="section-title">
          <br>
        </div>

          <div class="col-lg-12 col-md-6 d-flex align-items-stretch mt-4 mt-md-0" style="justify-content: center">
            <div class="icon-box"> 
            
            <%
						
						if (citasPendientes == null) {
						
							%>
							<label class="text-center col-12">No tienes ninguna cita pendiente de gestionar.</label>
							<%
						
						} else{
							
						%>
            
            
              <table class="table table-light table-striped">
					<thead>
						<tr>
							<th class="text-center text-primary col-3">Usuario</th>
							<th class="text-center text-primary col-3">Centro</th>
							<th class="text-center text-primary col-3">Especialidad</th>
							<th class="text-center text-primary col-3">Fecha</th>
							<th class="text-center text-primary col-3">Validar</th>
							<th class="text-center text-primary col-3">Denegar</th>

						</tr>
					</thead>
					<tbody>
						
						<tr>
									<!-- INICIO CONDICION GESTION CITAS -->
									
									<%
									for (int i = 0; i < citasPendientes.size(); i++) {
									%>

									<td class="text-center col-3"><%=citasPendientes.get(i).getUsuario()%></a></td>
									<td class="text-center col-3"><%=citasPendientes.get(i).getCentro()%></td>
									<td class="text-center col-3"><%=citasPendientes.get(i).getEspecialidad()%></td>
									<td class="text-center col-3"><%=citasPendientes.get(i).getFecha()%></td>
									<td class="text-center col-3"><a href="ValidarCita?idCita=<%=citasPendientes.get(i).getIdCita()%>" class="fs-6"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-check2" viewBox="0 0 16 16">
  										<path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"/>
										</svg></td>
									<td class="text-center col-3"><a href="DenegarCita?idCita=<%=citasPendientes.get(i).getIdCita()%>" class="fs-6"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg text-danger" viewBox="0 0 16 16">
  										<path fill-rule="evenodd" d="M13.854 2.146a.5.5 0 0 1 0 .708l-11 11a.5.5 0 0 1-.708-.708l11-11a.5.5 0 0 1 .708 0Z"/>
  										<path fill-rule="evenodd" d="M2.146 2.146a.5.5 0 0 0 0 .708l11 11a.5.5 0 0 0 .708-.708l-11-11a.5.5 0 0 0-.708 0Z"/>
										</svg></td>
									
								</tr>
								<%
				
								}
								%>

							</tbody>
						</table>
					
					<%
				}
				
				%>
						
		
						
            </div>
          </div>

          
        </div>
        
    </section><!-- End Services Section -->
	
	
	
	


</body>
</html>