<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Contatti"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
	<div id="contattiPagina">
		<h1>Contatti</h1>
		</div>
		<div id="contatti">	
		<section>
		<img id="imgContatti" alt="Nytro" src="/NYTRO/img/logo.png">
		<pre>
		<b>Sede principale</b>
		Via Vittoria, 01 - 84001 Altopiano Blu
		
		<b>Sedi secondarie</b>
		Via Vulcanica, 03 – 84003 Primisola
		Via Gelata, 02 - 84002 Ebanopoli
		
		<b>Telefono</b>
		+39 089 0101 010
		+39 089 0303 030
		+39 089 0202 020
		
		<b>Fax:</b>
		+39 089 123321
		
		<b>Email</b>
		info@nytro.it
		</pre>
		</section>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#contatti").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>							<!-- Inclusione statica di footer.html" -->