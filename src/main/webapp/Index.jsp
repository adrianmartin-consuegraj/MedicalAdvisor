<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page errorPage="Fin.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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

	<!-- ======= Appointment Section ======= -->
	<section id="appointment" class="appointment section-bg">
		<div class="container">

			<div class="section-title">
				<h2>MedicalAdvisor</h2>
				<p>Tu plataforma de gestión médica en Madrid.</p>
			</div>

			<form action="ValidarUsuario" method="POST" role="form" class="php-email-form">

				<div class="row">

					<div class="col-md-4 form-group"></div>

					<!-- USUARIO -->
					<div class="col-md-4 form-group mt-3 mt-md-0">
						<input class="form-control" id="inputUsuario" type="text"
							name="inputUsuario" placeholder="Usuario" maxlength="60">
						<div class="validate"></div>
					</div>

					<div class="col-md-4 form-group mt-3 mt-md-0"></div>
				</div>

				<div class="row">
					<div class="col-md-4 form-group mt-3"></div>

					<!-- PASSWORD -->
					<div class="col-md-4 form-group mt-3">
						<input class="form-control" id="inputPassword" type="password"
							name="inputPassword" placeholder="Password" maxlength="60">
						<div class="validate"></div>
					</div>

					<div class="col-md-4 form-group mt-3"></div>

				</div>

				<div class="mb-3">
					<div class="loading">Loading</div>
					<div class="error-message"></div>
					<div class="sent-message">Your appointment request has been
						sent successfully. Thank you!</div>
				</div>

				<div class="text-center">
					<a href="RegistrarUsuario.jsp" class="nav-link scrollto">Registrate</a>
				</div>
				
				<br>
				
				<div class="text-center">
					<button type="submit">Entrar</button>
				</div>
			</form>

		</div>
	</section>
	<!-- End Appointment Section -->

	<div style="background-color: #f1f7fd; height: 500px"></div>

</body>
</html>