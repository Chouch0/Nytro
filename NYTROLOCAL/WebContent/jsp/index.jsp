<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Home"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
	<div class="evidenza">
		<h1>In evidenza</h1>
		<img href="">
	</div>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#home").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							 <!-- Inclusione statica di footer.html" -->