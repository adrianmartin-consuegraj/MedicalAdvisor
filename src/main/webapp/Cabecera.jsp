<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.Cita"%>
<%@page import="domain.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@include file="Cabecera.jsp"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>PRUEBA</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>

	<%
	//Sesión - Recuperamos Usuario
		Usuario usuario = (Usuario) session.getAttribute("usuario");
	%>

  <!-- ======= Top Bar ======= -->
	<br>
	<div id="topbar" class="d-flex align-items-center fixed-top" style="justify-content: end;">
      <div class="contact-info d-flex align-items-center">
        <i class="bi bi-envelope"></i>Hola<a href="PerfilUsuario.jsp" class="nav-link scrollto"><%=usuario.getNombre().toUpperCase()%></a> 
        <a href="CerrarSesion" class="appointment-btn scrollto text-light" style="margin: 1px";><span class="d-none d-md-inline"></span>Desconectarse</a>
        
        <% 	
					if (usuario.getCitas().size()>0){					
						%>
					<i class="bi bi-phone">Próxima Cita: <label class="nav-link scrollto">
					<%= usuario.getCitas()==null ? usuario.getNombre() : usuario.getCitas().get(usuario.getCitas().size()-1).getFecha() %>
							en <%= usuario.getCitas().get(usuario.getCitas().size()-1).getCentro() %></label></i>
					<% 	
					} else{
						%>
					<label class="fs-5 text-success">Próxima Cita: <label
						class="fs-5 text-dark">Ninguna programada.</label></label>
					<%
					}
					%>
      </div>
    </div>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top">
    <div class="container d-flex align-items-center">

      <h1 class="logo me-auto"><a href="index.html">MedicalAdvisor</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo me-auto"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul>
          <li><a class="nav-link scrollto active" href="ValidarUsuario">Inicio</a></li>
          <li><a class="nav-link scrollto" href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>
          <li><a class="nav-link scrollto" href="Centros.jsp?tipo=Clinicas">Clínicas</a></li>
          <li><a class="nav-link scrollto" href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>
          
          <%
					if (usuario.getTipo().equalsIgnoreCase("admin")){
						%>
						
						<li class="dropdown"><a href="#"><span>Admin</span> <i class="bi bi-chevron-down"></i></a>
            				<ul>
            					<li><a class="nav-link scrollto" href="RecuperarCitasParaGestionar">Gestión Citas</a></li>
          					 </ul>
						</li>
				<%
					}
				%>
				
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->
    </div>
  </header><!-- End Header -->


  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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