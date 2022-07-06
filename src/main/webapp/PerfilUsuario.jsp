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

	boolean hospitales = false;
	boolean clinicas = false;
	boolean farmacias = false;
	%>

	<!-- ======= Header ======= -->
	<br>
	<header id="header"
		class="fixed-top d-flex align-items-center fixed-top">
		<div id="topbar" class="d-flex align-items-center fixed-top"
			style="justify-content: center;">
			<div class="contact-info d-flex align-items-center">
				<a href="ValidarUsuario" class="appointment-btn scrollto text-light"
					style="margin: 1px";><span class="d-none d-md-inline"></span>Volver</a>
			</div>
		</div>
	</header>

	<!-- End Header -->

	<!-- ======= CABECERA SECTION (perfil y citas) ======= -->
	<section id="doctors services footer" class="doctors services footer">
		
		<div class="container footer-top">

			<div class="row">

				<div class="col-lg-6 mt-4 mt-lg-0">
					<div class="member d-flex align-items-center"
						style="justify-content: center">
						<div class="pic">
							<img src="assets/img/doctors/doctors-2.jpg" class="img-fluid"
								alt="">
						</div>
						<div class="member-info">
							<h4><%=usuario.getNombre().toUpperCase()%></h4>
							<span>Ubicación: <%=usuario.getUbicacion().getNombre()%></span>
							<p>
								Tipo:
								<%=usuario.getTipo()%></p>

						</div>
					</div>
				</div>
				

				<div class="col-lg-6 mt-4 mt-lg-0">
					
					<div class="member d-flex align-items-center" style="justify-content: center">
						
						</div>
						
						<div class="member-info">
							<h3 style="text-align: center">Citas</h3>
							

                    <div class="container" style="justify-content: center">
                        <div class="row">

                            <%
                            if (usuario.getCitas() != null) {

                                for (int i = usuario.getCitas().size() - 1; i > -1; i--) {
                            %>

                            <div class="col-8" style="text-align: end">
                                <li style="justify-content: end; list-style: none"><i
                                    class="bx bx-chevron-right"></i> <%=usuario.getCitas().get(i).getFecha()%>
                                    en <%=usuario.getCitas().get(i).getCentro()%></li>

                            </div>

                            <div class="col-2">
                                <p class="fs-6">
                                    <a
                                        href="EliminarCita?cita=<%=usuario.getCitas().get(i).getIdCita()%>"
                                        class="fs-6"><svg xmlns="http://www.w3.org/2000/svg"
                                            width="16" height="16" fill="currentColor"
                                            class="bi bi-x-lg text-danger" viewBox="0 0 16 16">
<path fill-rule="evenodd"
                                                d="M13.854 2.146a.5.5 0 0 1 0 .708l-11 11a.5.5 0 0 1-.708-.708l11-11a.5.5 0 0 1 .708 0Z" />
<path fill-rule="evenodd"
                                                d="M2.146 2.146a.5.5 0 0 0 0 .708l11 11a.5.5 0 0 0 .708-.708l-11-11a.5.5 0 0 0-.708 0Z" />
</svg></a>
                                </p>
                            </div>

                            <%
                            }

                            } else {

                            String noCitas = (String) session.getAttribute("citas");
                            %>

                            <p>
                                CITAS:
                                <%=noCitas%></p>
                            <%
                            }
                            %>

                        </div>
                        <div class="row-6 text-center">


                            <a href="NuevaCita.jsp"
                                class="appointment-btn scrollto text-light"
                                style="margin: 1px";><span class="d-none d-md-inline"></span>Añadir
                                cita</a>
                        </div>
                    </div>
                    <br>
	</div>
					</div>
				</div>
					

                




			</div>
	</section>
	<!-- End CABECERA SECTION -->



	<!-- ======= Frequently Asked Questions Section ======= -->
	<section id="faq" class="faq section-bg">
		<div class="container">

			<div class="section-title">
				<h2>Favoritos</h2>
			</div>

			<div class="faq-list">
				<ul>
					<li data-aos="fade-up" data-aos-delay="100"
						style="text-align: center"><a data-bs-toggle="collapse"
						class="collapse" data-bs-target="#faq-list-1"> Hospitales <i
							class="bx bx-chevron-down icon-show"></i><i
							class="bx bx-chevron-up icon-close"></i>
					</a>


						<div id="faq-list-1" class="collapse show"
							data-bs-parent=".faq-list">
							<br>
							<%
							if (usuario.getFavoritos() != null) {

								for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

									if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Hospitales")) {

								hospitales = true;
									}
								}
							}

							if (hospitales == false) {
							%>

							<label class="text-center col-12">No tienes ningún
								Hospital guardado como favorito.</label>

							<%
							} else {
							%>

							<table class="table table-light table-striped">
								<thead>
									<tr>
										<th class="text-center text-primary col-3">Nombre</th>
										<th class="text-center text-primary col-3">Dirección</th>
										<th class="text-center text-primary col-3">Teléfono</th>
										<th class="text-center text-primary col-3">Quitar</th>
									</tr>
								</thead>
								<tbody>

									<tr>
										<!-- INICIO CONDICION FAVS -->
										<%
										for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

											usuario.getFavoritos().getCentro().get(i).getCategoria();

											if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Hospitales")) {
										%>

										<td class="text-center col-3"><a
											href="PerfilCentro.jsp?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"><%=usuario.getFavoritos().getCentro().get(i).getNombre()%></a></td>
										<td class="text-center col-3"><%=usuario.getFavoritos().getCentro().get(i).getDireccion()%></td>
										<td class="text-center col-3"><%=usuario.getFavoritos().getCentro().get(i).getTelefono()%></td>
										<td class="text-center col-3"><a
											href="EliminarCentroFav?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"
											class="fs-6"><svg xmlns="http://www.w3.org/2000/svg"
													width="16" height="16" fill="currentColor"
													class="bi bi-x-lg text-danger" viewBox="0 0 16 16">
  <path fill-rule="evenodd"
														d="M13.854 2.146a.5.5 0 0 1 0 .708l-11 11a.5.5 0 0 1-.708-.708l11-11a.5.5 0 0 1 .708 0Z" />
  <path fill-rule="evenodd"
														d="M2.146 2.146a.5.5 0 0 0 0 .708l11 11a.5.5 0 0 0 .708-.708l-11-11a.5.5 0 0 0-.708 0Z" />
</svg></a></td>
									</tr>
									<%
									}
									}
									%>

								</tbody>
							</table>

							<%
							}
							%>
						</div></li>



					<li data-aos="fade-up" data-aos-delay="100"
						style="text-align: center"><a data-bs-toggle="collapse"
						data-bs-target="#faq-list-2" class="collapsed">Clínicas <i
							class="bx bx-chevron-down icon-show"></i><i
							class="bx bx-chevron-up icon-close"></i>
					</a>
						<div id="faq-list-2" class="collapse" data-bs-parent=".faq-list">
							<br>

							<%
							if (usuario.getFavoritos() != null) {

								for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

									if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Clinicas")) {

								clinicas = true;
									}
								}
							}

							if (clinicas == false) {
							%>

							<label class="text-center col-12">No tienes ninguna
								Clínica guardada como favorita.</label>

							<%
							} else {
							%>

							<table class="table table-light table-striped">
								<thead>
									<tr>
										<th class="text-center text-primary col-3">Nombre</th>
										<th class="text-center text-primary col-3">Dirección</th>
										<th class="text-center text-primary col-3">Teléfono</th>
										<th class="text-center text-primary col-3">Quitar</th>
									</tr>
								</thead>
								<tbody>

									<tr>
										<%
										for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

											usuario.getFavoritos().getCentro().get(i).getCategoria();

											if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Clinicas")) {
										%>

										<td class="text-center col-3"><a
											href="PerfilCentro.jsp?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"><%=usuario.getFavoritos().getCentro().get(i).getNombre()%></a></td>
										<td class="text-center col-3"><%=usuario.getFavoritos().getCentro().get(i).getDireccion()%></td>
										<td class="text-center col-3"><%=usuario.getFavoritos().getCentro().get(i).getTelefono()%></td>
										<td class="text-center col-3"><a
											href="EliminarCentroFav?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"
											class="fs-6"><svg xmlns="http://www.w3.org/2000/svg"
													width="16" height="16" fill="currentColor"
													class="bi bi-x-lg text-danger" viewBox="0 0 16 16">
  <path fill-rule="evenodd"
														d="M13.854 2.146a.5.5 0 0 1 0 .708l-11 11a.5.5 0 0 1-.708-.708l11-11a.5.5 0 0 1 .708 0Z" />
  <path fill-rule="evenodd"
														d="M2.146 2.146a.5.5 0 0 0 0 .708l11 11a.5.5 0 0 0 .708-.708l-11-11a.5.5 0 0 0-.708 0Z" />
</svg></a></td>
									</tr>
									<%
									}
									}
									%>

								</tbody>
							</table>
							<%
							}
							%>
						</div></li>





					<li data-aos="fade-up" data-aos-delay="200"
						style="text-align: center"><a data-bs-toggle="collapse"
						data-bs-target="#faq-list-3" class="collapsed">Farmacias <i
							class="bx bx-chevron-down icon-show"></i><i
							class="bx bx-chevron-up icon-close"></i>
					</a>
						<div id="faq-list-3" class="collapse" data-bs-parent=".faq-list">
							<br>

							<%
							if (usuario.getFavoritos() != null) {

								for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

									if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Farmacias")) {

								farmacias = true;
									}
								}

							}

							if (farmacias == false) {
							%>

							<label class="col-12 text-center">No tienes ninguna
								Farmacia guardada como favorita.</label>

							<%
							} else {
							%>

							<table class="table table-light table-striped">
								<thead>
									<tr>
										<th class="col-3 text-primary text-center">Nombre</th>
										<th class="col-3 text-primary text-center">Dirección</th>
										<th class="col-3 text-primary text-center">Teléfono</th>
										<th class="col-3 text-primary text-center">Quitar</th>
									</tr>
								</thead>
								<tbody>

									<tr>
										<%
										for (int i = 0; i < usuario.getFavoritos().getCentro().size(); i++) {

											usuario.getFavoritos().getCentro().get(i).getCategoria();

											if (usuario.getFavoritos().getCentro().get(i).getCategoria().equalsIgnoreCase("Farmacias")) {
										%>

										<td class="text-center col-3"><a
											href="PerfilCentro.jsp?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"><%=usuario.getFavoritos().getCentro().get(i).getNombre()%></a></td>
										<td class="col-3 text-center"><%=usuario.getFavoritos().getCentro().get(i).getDireccion()%></td>
										<td class="col-3 text-center"><%=usuario.getFavoritos().getCentro().get(i).getTelefono()%></td>
										<td class="col-3 text-center"><a
											href="EliminarCentroFav?idCentro=<%=usuario.getFavoritos().getCentro().get(i).getIdCentro()%>"
											class="fs-6"><svg xmlns="http://www.w3.org/2000/svg"
													width="16" height="16" fill="currentColor"
													class="bi bi-x-lg text-danger" viewBox="0 0 16 16">
  <path fill-rule="evenodd"
														d="M13.854 2.146a.5.5 0 0 1 0 .708l-11 11a.5.5 0 0 1-.708-.708l11-11a.5.5 0 0 1 .708 0Z" />
  <path fill-rule="evenodd"
														d="M2.146 2.146a.5.5 0 0 0 0 .708l11 11a.5.5 0 0 0 .708-.708l-11-11a.5.5 0 0 0-.708 0Z" />
</svg></a></td>
									</tr>
									<%
									}
									}
									%>

								</tbody>
							</table>
							<%
							}
							%>
						</div></li>


				</ul>
			</div>

		</div>
	</section>
	<!-- End Frequently Asked Questions Section -->


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