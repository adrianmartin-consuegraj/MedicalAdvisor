<%@page import="domain.Centro"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.Cita"%>
<%@page import="domain.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


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
	//Sesión - Recuperamos los Centros
	List<Centro> centros = (List<Centro>) session.getAttribute("centros");
	
	//Tipo de Centros a recuperar
	String tipo = request.getParameter("tipo");
	
	//para VOLVER ATRÁS (Dispacher)
	String salida = request.getHeader("referer");
	
	String estrellaCompleta = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star-fill' viewBox='0 0 16 16'><path d='M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z'/></svg>";
	String estrellaMitad = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star' viewBox='0 0 16 16'><path d='M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z'/></svg>";
	String estrellaVacia = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star' viewBox='0 0 16 16'><path d='M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z'/></svg>";
	%>


	<%
	//Sesión - Recuperamos Usuario
		Usuario usuario = (Usuario) session.getAttribute("usuario");
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
				<%
					if(tipo.equals("Hospitales")){
						%>		
						<li><a class="nav-link scrollto active" href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>
				<%	
					} else{
						%>
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>	
						<%
					}
				%>
				
				<!-- CLINICAS ACTIVE link -->
				<%
					if(tipo.equals("Clinicas")){
						%>		
						<li><a class="nav-link scrollto active" href="Centros.jsp?tipo=Clinicas">Clínicas</a></li>
				<%	
					} else{
						%>
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Clinicas">Clínicas</a></li>
						<%
					}
				%>
				
				<!-- CLINICAS ACTIVE link -->
				
				<%
					if(tipo.equals("Farmacias")){
						%>		
						<li><a class="nav-link scrollto active" href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>
					
				<%	
					} else{
						%>
						<li><a class="nav-link scrollto" href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>
						<%
					}
				%>

					<%
					if (usuario.getTipo().equalsIgnoreCase("admin")){
						%>

					<li class="dropdown"><a href="#"><span>Admin</span> <i
							class="bi bi-chevron-down"></i></a>
						<ul>
							<li><a class="nav-link scrollto"
								href="RecuperarCitasParaGestionar">Gestión Citas</a></li>
						</ul></li>
					<%
					}
				%>

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
              <table class="table table-light table-striped">
					<thead>
						<tr>
							<th class="col-4 text-center text-primary">Nombre</th>
							<th class="col-3 text-center text-primary">Dirección</th>
							<th class="col-3 text-center text-primary">Teléfono</th>
							<th class="col-2 text-center text-primary">Puntuación</th>

						</tr>
					</thead>
					<tbody>

						<tr>

							<%
							for (int i = 0; i < centros.size(); i++) {
								
								if(centros.get(i).getCategoria().equalsIgnoreCase(tipo)){
							
							double valorPuntuacion = centros.get(i).getPuntuacion();
							String puntuacion = "";
							
							//5 estrellas
							if(valorPuntuacion>=4.99 && valorPuntuacion<5.01){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaCompleta;
							}
							
							//4.5 estrellas
							if(valorPuntuacion>=4.50 && valorPuntuacion<4.98){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaMitad;
							}
							
							//4 estrellas
							if(valorPuntuacion>=4.00 && valorPuntuacion<4.50){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaVacia;
							}
							
							//3.5 estrellas
							if(valorPuntuacion>=3.50 && valorPuntuacion<4.00){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaMitad + estrellaVacia;
							}
							
							//3 estrellas
							if(valorPuntuacion>=3.00 && valorPuntuacion<3.50){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaCompleta + estrellaVacia + estrellaVacia;
							}
							
							//2.5 estrellas
							if(valorPuntuacion>=2.50 && valorPuntuacion<3.00){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaMitad + estrellaVacia + estrellaVacia;
							}
							
							//2 estrellas
							if(valorPuntuacion>=2.00 && valorPuntuacion<2.50){
								puntuacion = estrellaCompleta + estrellaCompleta + estrellaVacia + estrellaVacia + estrellaVacia;
							}
							
							//1.5 estrellas
							if(valorPuntuacion>=1.50 && valorPuntuacion<2.00){
								puntuacion = estrellaCompleta + estrellaMitad + estrellaVacia + estrellaVacia + estrellaVacia;
							}
							
							//1 estrellas
							if(valorPuntuacion>=1.00 && valorPuntuacion<1.50){
								puntuacion = estrellaCompleta + estrellaVacia + estrellaVacia + estrellaVacia + estrellaVacia;
							}
							
							//0.5 estrellas
							if(valorPuntuacion>=0.50 && valorPuntuacion<1.00){
								puntuacion = estrellaMitad + estrellaVacia + estrellaVacia + estrellaVacia + estrellaVacia;
							}
							
							//0 estrellas
							if(valorPuntuacion>=0.00 && valorPuntuacion<0.50){
								puntuacion = estrellaVacia + estrellaVacia + estrellaVacia + estrellaVacia + estrellaVacia;
							}
							
							%>

							<td class="text-center col-3"><a
								href="PerfilCentro.jsp?idCentro=<%=centros.get(i).getIdCentro()%>"><%=centros.get(i).getNombre()%></a></td>
							<td class="text-center"><%=centros.get(i).getDireccion()%></td>
							<td class="text-center"><%=centros.get(i).getTelefono()%></td>
							<td class="text-center"><%=puntuacion%></td>

						</tr>
						<%
								}
						}
						%>
					</tbody>
				</table>
            </div>
          </div>

          
          
        </div>

      </div>
    </section><!-- End Services Section -->
    
    <!-- ############################################################################################################### -->

	<div id="preloader"></div>
	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/purecounter/purecounter.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>



</body>
</html>