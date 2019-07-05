<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Collection, nytro.model.AccountBean"%>
<%
	Collection<AccountBean> amici = (Collection<AccountBean>) request.getAttribute("amici");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Friendlist"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
			
	<h1>Lista amici</h1>
	
	<p>
		<%
			for(AccountBean x : amici){
		%>
			<%=x.toString()+"\n" %> <%
				if (x.getImgProfilo() != null){
					BufferedImage img = ImageIO.read(x.getImgProfilo()); 
					System.out.println("Diverso da null");
				}%>
			<form action="/NYTRO/RimuoviFriend" method="post">
				<input type="hidden" name="eliminatoAmico" value="<%=x.getUsername()%>">
				<input type="submit" value="Rimuovi">
			</form>
			<br/>		
		<%
			}
		%>
	</p>
	
	<form action="/NYTRO/AggiungiFriend" method="post">
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
	
<%@include file="footer.html"%>							