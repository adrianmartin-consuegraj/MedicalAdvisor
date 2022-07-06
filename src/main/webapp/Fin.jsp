<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Fin</title>

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/estilos.css">
<script type="text/javascript" src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>

</head>

<body>

	<%
	String salida = null;
	
	if(session.getAttribute("usuario")!=null){
		salida = "Inicio.jsp";
	} else{
		salida = "Index.jsp";
	}
	%>

		<div class="row">
			<div class="row align-items-center text-end">
			
				<div class="col-12" style="text-align: center;">
				<br>
				<h4 style="text-align: center;">Error_</h4>
					<h5 style="text-align: center;"><%=request.getAttribute("mensaje") %></h5>
					<br>
					<a href="<%=salida%>" class="btn btn-success">Volver al Inicio</a>
				</div>

			</div>
		</div>
		<br>

</body>
</html>