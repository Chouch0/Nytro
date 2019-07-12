<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Collection, nytro.model.AccountBean"%>
<%
	Collection<AccountBean> amici = (Collection<AccountBean>) request.getAttribute("amici");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Friendlist"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/friendlistStyle.css" type="text/css" rel="stylesheet">			
	<div id="lista">
	<h1>Lista amici</h1>
	<%if(amici!=null) {%>
		<div id="tabella">
		<%
			for(AccountBean x : amici){
		%>
		<ul>
		<div class="amico">
			<li><div class="profilo">
			<%if (x.getImgProfilo() != null){%>
					<img src="/NYTRO/image?id=<%= x.getUsername()%>" alt="<%=x.getUsername()%>">
			<%} else {%>
					<img src="/NYTRO/img/default-profile.png" alt="<%=x.getUsername()%>">
			<%} %></div></li>
			<li><form action="<%=response.encodeURL("/NYTRO/Libreria")%>" method="post">
				<input type="hidden" name="libreriaAmicoDaVisualizzare" value="<%=x.getUsername()%>">
				<input type="submit" value="Visualizza libreria">
			</form></li>
			<li><form action="<%=response.encodeURL("/NYTRO/Friendlist")%>" method="post">
				<input type="hidden" name="eliminatoAmico" value="<%=x.getUsername()%>">
				<input type="submit" value="Rimuovi">
			</form></li>
			<li class="username"><%=x.getUsername() %></li>
			<li><%=x.getData() %> <%=x.getOra() %></li>
		</div>
		</ul>
			<%}
		} %>
		</div>
		<div id="aggiungi">
		<form action="<%=response.encodeURL("/NYTRO/Friendlist")%>" method="post">
		<label>
			Inserisci l'username del giocatore da aggiungere:
			<input type="text" name="futuroAmico" placeholder="Username">
			<input type="submit" value="Aggiungi">
		</label>
		</form>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script>
			$("document").ready(function prova(){
				$("#friendlist").addClass("selected");
			})
		</script>
		</div>
	</div>
<%@include file="footer.jsp"%>							