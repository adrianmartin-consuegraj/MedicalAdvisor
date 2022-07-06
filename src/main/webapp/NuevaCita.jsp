<%@page import="domain.Centro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page errorPage="Fin.jsp"%>


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
	//Sesión - Recuperamos el objeto Centros
	List<Centro> centros = (List<Centro>) session.getAttribute("centros");
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

	<!-- ======= Contact Section ======= -->
	<section id="contact" class="contact">

		<div class="section-title">
			<h2>Solicitar nueva cita</h2>


			<form action="PedirCita" method="POST" role="form" class="php-email-form" onsubmit="return validateForm()">

				<div class="container">
					<div class="row mt-5">

						<div class="col-5 form-group mt-3 mt-md-0" style="text-align: end">
							<label class="fs-5 text-primary">Tipo</label>
						</div>

						<div class="col-md-3 form-group mt-3 mt-md-0"
							style="justify-content: center">

							<select class="form-control" name="inputTipo" id="inputTipo" required style="text-align: center" onchange="seleccionCentros(this)">
								<option value="0" hidden selected>Tipo de Centro</option>
								<option value="1">Hospital</option>
								<option value="2">Clínica</option>
							</select>
						</div>



						<div class="col-5 form-group mt-3 mt-md-0" style="text-align: end">
							<label class="fs-5 text-primary">Centro</label>
						</div>

						<div class="col-md-3 form-group mt-3 mt-md-0"
							style="justify-content: center">

							<select class="form-control" name="inputCentro" id="inputCentro" disabled style="text-align: center" onchange="seleccionEspecialidades(this)">
								<option selected>Centros</option>
								<!-- AÑADIR CENTROS AQUI -->
							</select>
						</div>


						<div class="col-5 form-group mt-3 mt-md-0" style="text-align: end">
							<label class="fs-5 text-primary">Especialidades</label>
						</div>

						<div class="col-md-3 form-group mt-3 mt-md-0"
							style="justify-content: center">

							<select class="form-control" name="inputEspecialidad" id="inputEspecialidad" style="text-align: center" disabled>
								<option value="0" selected>Especialidades</option>
								<option value="1">Alergologia</option>
								<option value="2">Neurologia</option>
								<option value="3">Radiodiagnostico</option>
								<option value="4">Analisis clinicos y microbiologia</option>
								<option value="5">Reumatologia</option>
								<option value="6">Odontologia</option>
								<option value="7">Analisis clinicos y microbiologia</option>
								<option value="8">Endocrinologia y nutricion</option>
								<option value="9">Nefrologia</option>
								<option value="10">Oftalmologia</option>
								<option value="11">Patologia Vertebral</option>
								<option value="12">Rehabilitacion y fisioterapia</option>
								<option value="13">Psiquiatria</option>
								<option value="14">Hematologia y hemoterapia</option>
								<option value="15">Inmunologia</option>
								<option value="16">Medicina de Familia</option>
								<option value="17">Aparato digestivo</option>
								<option value="18">Cardiologia</option>
								<option value="19">Ginecologia y obstetricia</option>
							</select>
						</div>



						<div class="col-5 form-group mt-3 mt-md-0" style="text-align: end">
							<label class="fs-5 text-primary">Fecha</label>
						</div>

						<div class="col-md-3 form-group mt-3 mt-md-0"
							style="justify-content: center">

							<input class="form-control" id="inputFecha" type="date"
								name="inputFecha" maxlength="60"
								style="text-align: center; border-radius: 5px;">
						</div>

					</div>

				</div>

				<br>
				<div class="text-center">
					<button class="btn-sm" type="submit">Pedir cita</button>
				</div>



			</form>
		</div>
	</section>
	<!-- End Contact Section -->



	<!-- JAVASCRIPT -->
	<script type="text/javascript">

	function seleccionCentros(tipo){
		
		//alert("yeee");
			            
			            if (tipo.value !=0){
			     
			            	//HOSPITALES
			            	if(tipo.value==1){
			            		inputCentro.removeAttribute('disabled');
			            	
			            		//1~ Borramos valores antiguos almacenados en la select Centros
		            			for(i=(inputCentro.length-1); i>=0; i--){
		                    		aBorrar = inputCentro.options[i];
		                        	aBorrar.parentNode.removeChild(aBorrar);
		                     	}
		            		
			            		//2~ Insertamos valores en la select Centros
		            			<%for (int i = 0; i < centros.size(); i++) {

								if (centros.get(i).getCategoria().equalsIgnoreCase("Hospitales")) {%>
						            		var opcion = document.createElement("option");
						            		opcion.value="<%=centros.get(i).getNombre()%>";
							            	opcion.text="<%=centros.get(i).getNombre()%>";
							            	inputCentro.appendChild(opcion);
						        <%}
								}%>
							
			            		}//Fin Hospitales
			            	
			            	//CLINICAS
			            	if(tipo.value==2){
			            		inputCentro.removeAttribute('disabled');
				            	
				            	//1~ Borramos valores antiguos almacenados en la select Centros
			            		for(i=(inputCentro.length-1); i>=0; i--){
			                    	aBorrar = inputCentro.options[i];
			                        aBorrar.parentNode.removeChild(aBorrar);
			                     }
			            		
				            	//2~ Insertamos valores en la select Centros
			            		<%for (int i = 0; i < centros.size(); i++) {

									if (centros.get(i).getCategoria().equalsIgnoreCase("Clinicas")) {%>
			            					var opcion = document.createElement("option");
						            		opcion.value="<%=centros.get(i).getNombre()%>";
							            	opcion.text="<%=centros.get(i).getNombre()%>";
											inputCentro.appendChild(opcion);
									<%}
								}%>
		}

			} else {
				inputCentro.setAttribute('disabled', 'disabled');
			}

		}

		function seleccionEspecialidades(centro) {

			if (centro.value != 0) {

				var tipoEspecialidad = document.getElementById("inputEspecialidad");
				tipoEspecialidad.removeAttribute('disabled');

			} else {
				tipoEspecialidad.setAttribute('disabled', 'disabled');
			}

		}
		
		
		function validateForm(){
			
			var res = true;
			var mensaje = "";

			var tipoCentro = document.getElementById("inputTipo").value;
			var seleccionCentro = document.getElementById("inputCentro").value;
			var tipoEspecialidad = document.getElementById("inputEspecialidad").value;
			var fecha = document.getElementById("inputFecha").value;

			if (tipoCentro==0) {
				res = false;
				mensaje += "· Se debe seleccionar un Tipo de Centro.\n";
			}
			if (seleccionCentro=="") {
				res = false;
				mensaje += "· Se debe seleccionar un Centro.\n";
			}
			if (tipoEspecialidad==0) {
				res = false;
				mensaje += "· Se debe seleccionar una Especialidad.\n";
			}

			if (fecha=="") {
				res = false;
				mensaje += "· Se debe seleccionar una fecha válida.\n";
			}

			alert("Tipo centro: " . tipoCentro.value);
			alert("Centro: " . seleccionCentro);
			alert("Especialidad: " . tipoEspecialidad);
			alert("Fecha" . fecha);

			if (res == false) {
				alert(mensaje);
				//return res;
			} else{
				event.preventDefault();
			}

			//return res;
			
		}
	</script>

</body>
</html>