<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nytro.model.VideogiocoBean, java.util.Collection"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Home"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/indexStyle.css" type="text/css" rel="stylesheet">

<%
Collection<VideogiocoBean> videogiochiPiuAcquistati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuAcquistati");
Collection<VideogiocoBean> videogiochiPiuGiocati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocati");

if(videogiochiPiuAcquistati==null || videogiochiPiuGiocati==null){
	response.sendRedirect("/NYTRO/Index");
	return ;
}
%>	

<h2>Videogiochi più acquistati</h2>
<%for(VideogiocoBean x : videogiochiPiuAcquistati) {%>
	<%=x.toString() %><br/>
<%} %>

<h2>Videogiochi più giocati</h2>

<%for(VideogiocoBean x : videogiochiPiuGiocati) {%>
	<%=x.toString() %><br/>
<%} %>


	<br/><br/><br/><br/><br/><br/><br/><br/>

	<div class="evidenza">
		<h1>In evidenza</h1>
		<div id="gioco1">
			<img src="https://s2.gaming-cdn.com/images/products/1843/orig/assassins-creed-origins-cover.jpg">
			<section class="prezzo">Prezzo xxx€</section>
			<div class="info">
				<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
				Ciao bellini
			</div>
		</div>
		<div id="gioco2">
			<img src="https://s2.gaming-cdn.com/images/products/1843/orig/assassins-creed-origins-cover.jpg">
			<section class="prezzo">Prezzo xxx€</section>
			<div class="info">
				<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
				Ciao bellini
			</div>
		</div>
		<div id="gioco3">
			<img src="https://s2.gaming-cdn.com/images/products/1843/orig/assassins-creed-origins-cover.jpg">
			<section class="prezzo">Prezzo xxx€</section>
			<div class="info">
				<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
				Ciao bellini
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#home").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							 <!-- Inclusione statica di footer.html" -->