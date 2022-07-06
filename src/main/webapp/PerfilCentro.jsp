<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.ZonedDateTime"%>
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
	//Sesión - Recuperamos el objeto Usuario
	Usuario usuario = (Usuario) session.getAttribute("usuario");

	//Fecha Actual (para introducir en comentario nuevo)
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date fecha = new Date(System.currentTimeMillis());

	//Dispacher - Para VOLVER ATRÁS
	String referenciaVolver = "";

	String ref = request.getHeader("referer");
	referenciaVolver = ref.substring(37);

	//Recuperamos el ID del Centro que vamos a mostrar la información
	String id = request.getParameter("idCentro");
	int idCentro = Integer.parseInt(id);

	//- para recuperar todos los Centros
	List<Centro> centros = (List<Centro>) session.getAttribute("centros");

	Centro perfilCentro = new Centro();

	for (int i = 0; i < centros.size(); i++) {
		if (centros.get(i).getIdCentro() == idCentro) {
			perfilCentro = centros.get(i);
		}
	}

	//Guardamos cada estrella
	String estrellaCompleta = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star-fill' viewBox='0 0 16 16'><path d='M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z'/></svg>";
	String estrellaMitad = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star' viewBox='0 0 16 16'><path d='M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z'/></svg>";
	String estrellaVacia = "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-star' viewBox='0 0 16 16'><path d='M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z'/></svg>";

	//Guardamos la posición de las estrellas
	String posicion1 = "<a href='Centros.jsp?tipo=Hospitales' class='text-success'>HOSPITALES</a>";

	double valorPuntuacion = perfilCentro.getPuntuacion();
	String puntuacion = "";

	//5 estrellas
	if (valorPuntuacion >= 4.99 && valorPuntuacion < 5.01) {

		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
					 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
					 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaCompleta + "</a>" + 
					 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaCompleta + "</a>" + 
					 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaCompleta + "</a>";
	}

	//4.5 estrellas
	if (valorPuntuacion >= 4.50 && valorPuntuacion < 4.98) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaMitad + "</a>";
	}

	//4 estrellas
	if (valorPuntuacion >= 4.00 && valorPuntuacion < 4.50) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//3.5 estrellas
	if (valorPuntuacion >= 3.50 && valorPuntuacion < 4.00) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaMitad + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//3 estrellas
	if (valorPuntuacion >= 3.00 && valorPuntuacion < 3.50) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//2.5 estrellas
	if (valorPuntuacion >= 2.50 && valorPuntuacion < 3.00) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaMitad + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//2 estrellas
	if (valorPuntuacion >= 2.00 && valorPuntuacion < 2.50) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//1.5 estrellas
	if (valorPuntuacion >= 1.50 && valorPuntuacion < 2.00) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaMitad + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//1 estrellas
	if (valorPuntuacion >= 1.00 && valorPuntuacion < 1.50) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaCompleta + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//0.5 estrellas
	if (valorPuntuacion >= 0.50 && valorPuntuacion < 1.00) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaMitad + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}

	//0 estrellas
	if (valorPuntuacion >= 0.00 && valorPuntuacion < 0.50) {
		puntuacion = "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=1'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=2'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=3'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=4'>" + estrellaVacia + "</a>" + 
				 "<a href='ValorarCentro?centro=" + perfilCentro.getIdCentro() + "&valoracion=5'>" + estrellaVacia + "</a>";
	}
	%>

	<!-- ======= Header ======= -->
	<br>
	<header id="header"
		class="fixed-top d-flex align-items-center fixed-top">
		<div id="topbar" class="d-flex align-items-center fixed-top"
			style="justify-content: center;">
			<div class="contact-info d-flex align-items-center">
				<a href="<%=referenciaVolver%>"
					class="appointment-btn scrollto text-light" style="margin: 1px";><span
					class="d-none d-md-inline"></span>Volver</a>
			</div>
		</div>
	</header>

	<!-- End Header -->

	<!-- ======= Center Section ======= -->

	<section id="contact why-us departments"
		class="contact why-us departments">
		<div class="container">

			<div class="section-title">
				<h2><%=perfilCentro.getNombre().toUpperCase()%></h2>

				<div class="row mt-5" style="justify-content: center">
					<div class="col-lg-10  mt-5 mt-lg-0">

						<p><%=perfilCentro.getDescripcion()%></p>
					</div>


				</div>
			</div>


			<div class="row">

				<div class="col-lg-7 d-flex align-items-stretch"
					style="justify-content: center">
					<div class="icon-boxes d-flex flex-column justify-content-center">
						<div class="row">
							<div class="col-xl-4 d-flex align-items-stretch">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="bi bi-geo-alt"></i>
									<h4>Dirección:</h4>
									<p><%=perfilCentro.getDireccion()%></p>
								</div>
							</div>
							<div class="col-xl-4 d-flex align-items-stretch">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="bi bi-phone"></i>
									<h4>Teléfono:</h4>
									<p><%=perfilCentro.getTelefono()%></p>
								</div>
							</div>

							<div class="col-xl-4 d-flex align-items-stretch">
								<div class="icon-box mt-4 mt-xl-0">
									<i class="fas fa-award"></i>
									<h4>Valoración:</h4>
									<p><%=puntuacion%></p>
								</div>
							</div>

						</div>
					</div>
					<!-- End .content-->
				</div>

				<div class="col-lg-5 d-flex align-items-stretch"
					style="justify-content: center">
					<div class="content">
						<h3>¡Guárdame!</h3>
						<p>Agregame a tu lista de favoritos, así podrás consultar las
							últimas actualizaciones sobre el Centro más rapidamente.</p>
						<div class="text-center">
							<a href="AgregarCentroFav?idCentro=<%=idCentro%>" class="more-btn">Agregar a Favoritos<i class="bx bx-chevron-right"></i></a>
						</div>
					</div>
				</div>
			</div>

			<!-- End Why Us Section -->

		</div>

		<br>

		<!-- ======= Departments Section ======= -->

		<div class="container">

			<div class="row gy-4">
				<div class="col-lg-4" style="text-align: end">
					<ul class="nav nav-tabs flex-column">
						<li class="nav-item"><a class="nav-link active show"
							data-bs-toggle="tab" href="#tab-1">Servicios</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#tab-2">Especialidades</a></li>
						<li class="nav-item"><a class="nav-link" data-bs-toggle="tab"
							href="#tab-3">Accesos</a></li>

					</ul>
				</div>
				<div class="col-lg-8">
					<div class="tab-content">
						<div class="tab-pane active show" id="tab-1">
							<div class="row gy-4">
								<div class="col-lg-8 details order-2 order-lg-1">
									<h3>Servicios</h3>
									<p>
										El centro
										<%=perfilCentro.getNombre()%>
										<%=perfilCentro.getServicio().getAparcamiento()%>
										tiene aparcamientos en sus instalaciones. El complejo
										<%=perfilCentro.getServicio().getRestaurantes()%>
										cuenta con restaurantes (o los tiene alrededor) y
										<%=perfilCentro.getServicio().getTelevision()%>
										hay televisores en las habitaciones del centro.
									</p>
								</div>
							</div>
						</div>

						<div class="tab-pane" id="tab-2">
							<div class="row gy-4">
								<div class="col-lg-8 details order-2 order-lg-1">
									<h3>Especialidades</h3>
									<p>
									<ul>
										<%
										for (int i = 0; i < perfilCentro.getEspecialidades().size(); i++) {
										%>
										<li class="form-control form-control-sm fs-6" value="1"><%=perfilCentro.getEspecialidades().get(i).getEspecialidad()%></li>
										<%
										}
										%>
									</ul>
									</p>
								</div>

							</div>
						</div>

						<div class="tab-pane" id="tab-3">
							<div class="row gy-4">
								<div class="col-lg-8 details order-2 order-lg-1">
									<h3>Accesos</h3>
									<div class="col-12 text-start">
										<label> <select class="form-control form-control-sm fs-6 text-primary" name="inputTipo" id="tipoAcceso" onchange="seleccionAccesos(this)">
												<option class="form-control form-control-sm fs-6" value="0" hidden selected>Tipo de Acceso</option>
												<option class="form-control form-control-sm fs-6" value="1">Renfe</option>
												<option class="form-control form-control-sm fs-6" value="2">Metro</option>
												<option class="form-control form-control-sm fs-6" value="3">Coche</option>
										</select>
									</div>

									<div class="col-12 text-start">
									<br>
										<p id="descripcionAcesso"></p>

									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>

		</div>

		<!-- End Departments Section -->

	</section>
	

	<!-- COMENTARIOS ------------------------------------------------------------------------------------------------------------------------ -->
	<div style="width: 90%;" class="container border border-primary borde">
		<br>
		<div class="row">
			<div class="col-12 text-center">
				<h3>Comentarios</h3>
				<br>

				<%
					for (int i = 0; i < perfilCentro.getComentario().size(); i++) {
					%>

				<div class="row">
					<div class="col-3 text-end">
						<label class="fs-6 text-primary"># <label
							class="fs-6 text-dark"><%=i + 1%></label>
					</div>

					<div class="col-2">
						<label class="fs-6 text-primary">Usuario: <label
							class="fs-6 text-dark"><%=perfilCentro.getComentario().get(i).getUsuario()%></label>
					</div>

					<div class="col-4">
						<label class="fs-6 text-primary">Fecha: <label
							class="fs-6 text-dark"><%=perfilCentro.getComentario().get(i).getFecha()%></label>
					</div>

					<%
						if (usuario.getTipo().equalsIgnoreCase("admin")) {
						%>


					<div class="col-2">
						<a class="fs-6 btn btn-primary"
							href="EliminarComentario?centro=<%=perfilCentro.getIdCentro()%>&usuario=<%=perfilCentro.getComentario().get(i).getUsuario()%>&fecha=<%=perfilCentro.getComentario().get(i).getFecha()%>&mensaje=<%=perfilCentro.getComentario().get(i).getMensaje()%>"
							class="fs-6">Eliminar</a>
					</div>

					<%
						}
						%>

				</div>
				<br>

				<div class="row text-center">
					<div class="col-12" style="text-align:-webkit-center">
					
						<textarea disabled class="form-control" name="message" rows="5" style="width: 700px; height: 100px; resize: none;"><%=perfilCentro.getComentario().get(i).getMensaje()%></textarea>
						<div class="validate"></div>
				
					</div>
				</div>
				<br>
				<%
					}
					%>
				<hr>

				<!-- AÑADIR COMENTARIO NUEVO -->
				<h4 class="text-center appointment" id="appointment section-bg">- Añadir nuevo
					comentario -</h4>

				<form action="AñadirComentario" method="POST">

					<!-- IdCentro, Usuario y Fecha (hidden) -->
					<div class="row text-center">

						<input class="form-control" style="justify-content: space-around" id="inputIdCentro" type="text" name="inputIdCentro" maxlength="60" value="<%=perfilCentro.getIdCentro()%>" hidden> <input name="inputUsuario" maxlength="60" value="<%=usuario.getNombre()%>" hidden> <input class="form-control" id="inputFecha" type="text"
							name="inputFecha" maxlength="60"
							value="<%=formatter.format(fecha)%>" hidden>

					</div>
					<br>

					<!-- Comentario ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ -->
					<div class="row text-center" style="justify-content: space-around">
					
					<textarea class="form-control" style="width: 700px; height: 100px; resize: none;"
									id="inputMensaje" name="inputMensaje" placeholder="..."></textarea>

					</div>
					
					<!-- Botones -->
					<div class="row">

						<div class="row align-items-center text-center">

							<div class="col-12 text-center">
								<br>
								<button class="btn appointment-btn" type="submit">Enviar</button>
							</div>

						</div>

					</div>
			</form>

		</div>
	</div>
	<br>
	</div>

	<br>




	<!-- ############################################################################################################### -->



	<!-- JAVASCRIPT -->
	<script type="text/javascript">

					
	function seleccionAccesos(tipo){
		
        if (tipo.value!=0){
 
        	//RENFE
        	if(tipo.value==1){
        		
        		document.getElementById("descripcionAcesso").innerHTML = "<%=perfilCentro.getAcceso().getRenfe()%>";
        			
        		}
        	
        	//METRO
        	if(tipo.value==2){
        	
    			document.getElementById("descripcionAcesso").innerHTML = "<%=perfilCentro.getAcceso().getMetro()%>";
        		
        		}
        		
        	//COCHE
        	if(tipo.value==3){
        	
    			document.getElementById("descripcionAcesso").innerHTML = "<%=perfilCentro.getAcceso().getCoche()%>";

				}

			} else {
				document.getElementById("descripcionAcesso").innerHTML = "...";
			}

		}
	</script>
	
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