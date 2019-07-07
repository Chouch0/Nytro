<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Contatti"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
	<section>
		<h1>Contatti</h1>
		<pre>
		Sede principale
		Via Vittoria, 01 - 84001 Altopiano Blu
		
		Sedi secondarie
		Via Vulcanica, 03 – 84003 Primisola
		Via Gelata, 02 - 84002 Ebanopoli
		
		Telefono
		+39 089 0101 010
		+39 089 0303 030
		+39 089 0202 020
		
		Fax:
		+39 089 123321
		
		Email
		info@nytro.it
		</pre>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#contatti").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							<!-- Inclusione statica di footer.html" -->