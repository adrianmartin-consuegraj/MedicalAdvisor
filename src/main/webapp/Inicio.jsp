<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="domain.Cita"%>
<%@page import="domain.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@page errorPage="Fin.jsp"%>

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
					<li><a class="nav-link scrollto active" href="ValidarUsuario">Inicio</a></li>
					<li><a class="nav-link scrollto"
						href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>
					<li><a class="nav-link scrollto"
						href="Centros.jsp?tipo=Clinicas">Clínicas</a></li>
					<li><a class="nav-link scrollto"
						href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>

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

	<!-- ======= Hero Section ======= -->
	<section id="hero" class="d-flex align-items-center">
		<div class="container">
			<h1>¡Bienvenido a MedicalAdvisor!</h1>
			<h2>Descubre los hospitales, clínicas y las farmacias mejor valoradas.</h2>
			
		</div>
	</section>
	<!-- End Hero -->

	<main id="main">

		<!-- ======= Why Us Section ======= -->
		<section id="why-us" class="why-us">
			<div class="container">

				<div class="row">
					<div class="col-lg-4 d-flex align-items-stretch">
						<div class="content">
							<h3>¿Por qué elegir MedicalAdvisor?</h3>
							<p>Hemos organizado los principales hospitales, clínicas y farmacias de la Comunidad de Madrid para facilitar información tal
							como las especialidades, servicios y los accesos a dichos centros.</p>
							<div class="text-center">
								<a href="Centros.jsp?tipo=Hospitales" class="more-btn"> Descubrir <i
									class="bx bx-chevron-right"></i></a>
							</div>
						</div>
					</div>
					<div class="col-lg-8 d-flex align-items-stretch">
						<div class="icon-boxes d-flex flex-column justify-content-center">
							<div class="row">
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-receipt"></i>
										<h4>Compromiso legal</h4>
										<p>Toda la información proporcionada por el usuario será tratada con la máxima confidencialidad*</p>
										<h6> <small>*Sujeto al tipo de oferta.</small></h6>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-cube-alt"></i>
										<h4>Metodología innvadora</h4>
										<p>Tratamos siempre de "pensar fuera de la caja", trayendo soluciones a problemas
										que ni siquiera se han planteado aún.</p>
									</div>
								</div>
								<div class="col-xl-4 d-flex align-items-stretch">
									<div class="icon-box mt-4 mt-xl-0">
										<i class="bx bx-images"></i>
										<h4>Comparar es avanzar</h4>
										<p>Guarda y compara tus clínicas favoritas, con el objetivo de elegir la que mejor servicio te preste.</p>
									</div>
								</div>
							</div>
						</div>
						<!-- End .content-->
					</div>
				</div>

			</div>
		</section>
		<!-- End Why Us Section -->


		<!-- ======= Counts Section ======= -->
		<section id="counts" class="counts">
			<div class="container">

				<div class="row">

					<div class="col-lg-3 col-md-6">
						<div class="count-box">
							<i class="fas fa-user-md"></i> <span data-purecounter-start="0"
								data-purecounter-end="95" data-purecounter-duration="1"
								class="purecounter"></span>
							<p>Doctores</p>
						</div>
					</div>

					<div class="col-lg-3 col-md-6 mt-5 mt-md-0">
						<div class="count-box">
							<i class="far fa-hospital"></i> <span data-purecounter-start="0"
								data-purecounter-end="180" data-purecounter-duration="1"
								class="purecounter"></span>
							<p>Centros</p>
						</div>
					</div>

					<div class="col-lg-3 col-md-6 mt-5 mt-lg-0">
						<div class="count-box">
							<i class="fas fa-flask"></i> <span data-purecounter-start="0"
								data-purecounter-end="45" data-purecounter-duration="1"
								class="purecounter"></span>
							<p>Especialidades</p>
						</div>
					</div>

					<div class="col-lg-3 col-md-6 mt-5 mt-lg-0">
						<div class="count-box">
							<i class="fas fa-award"></i> <span data-purecounter-start="0"
								data-purecounter-end="247" data-purecounter-duration="1"
								class="purecounter"></span>
							<p>Valoraciones</p>
						</div>
					</div>

				</div>

			</div>
		</section>
		<!-- End Counts Section -->


	</main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<footer id="footer">

		<div class="footer-top">
			<div class="container">
				<div class="row">

					<div class="col-lg-3 col-md-6 footer-contact">
						<h3>MedicalAdvisor</h3>
						<p>
							28021 Madrid <br> Villaverde Alto <br> España <br>
							<br> <strong>Teléfono:</strong> +34 66 66 66 666<br> <strong>Email:</strong>
							info@medicaladvisor.com<br>
						</p>
					</div>

					<div class="col-lg-2 col-md-6 footer-links">
						<h4>Links</h4>
						<ul>
							<li><i class="bx bx-chevron-right"></i> <a href="#">Inicio</a></li>
							<li><i class="bx bx-chevron-right"></i> <a href="Centros.jsp?tipo=Hospitales">Hospitales</a></li>
							<li><i class="bx bx-chevron-right"></i> <a href="Centros.jsp?tipo=Clinicas">Clinicas</a></li>
							<li><i class="bx bx-chevron-right"></i> <a href="Centros.jsp?tipo=Farmacias">Farmacias</a></li>
							<li><i class="bx bx-chevron-right"></i> <a href="PerfilUsuario.jsp">Perfil</a></li>
						</ul>
					</div>

					<div class="col-lg-3 col-md-6 footer-links">
						<h4></h4>
						<ul>
							<li> <a href="#"></a></li>
							<li> <a href="#"></a></li>
							<li> <a href="#"></a></li>
							<li> <a href="#"></a></li>
							<li> <a href="#"></a></li>
						</ul>
					</div>

					<div class="col-lg-4 col-md-6 footer-newsletter">
						<h4>¡Apuntate a nuestra newsletter!</h4>
						<p>Mantente informado de las últimas novedades en el ámbito de la salud.</p>
						<form action="" method="post">
							<input type="email" name="email"><input type="submit"
								value="Suscribirse">
						</form>
					</div>

				</div>
			</div>
		</div>

		<div class="container d-md-flex py-4">

			<div class="me-md-auto text-center text-md-start">
				<div class="copyright">
					&copy; Copyright 2022 <strong><span>MedicalAdvisor</span></strong>. Todos los derechos reservados.</div>
				<div class="credits">
					<!-- All the links in the footer should remain intact. -->
					<!-- You can delete the links only if you purchased the pro version. -->
					<!-- Licensing information: https://bootstrapmade.com/license/ -->
					<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/medilab-free-medical-bootstrap-theme/ -->
					Diseñado por <a href="https://www.linkedin.com/in/adrianmartin-consuegraj/" target="_blank">Adrián Martín-Consuegra J.</a>
				</div>
			</div>
			<div class="social-links text-center text-md-right pt-3 pt-md-0">
				<a href="#" class="twitter"><i class="bx bxl-twitter"></i></a> <a
					href="#" class="facebook"><i class="bx bxl-facebook"></i></a> <a
					href="#" class="instagram"><i class="bx bxl-instagram"></i></a> <a
					href="#" class="google-plus"><i class="bx bxl-skype"></i></a> <a
					href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
			</div>
		</div>
	</footer>
	<!-- End Footer -->

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