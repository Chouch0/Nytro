<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.Collection"%>
<%
	Collection<String> amici = (Collection<String>) request.getAttribute("amici");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Friendlist"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
			
	<h1>Lista amici</h1>
	
	<p>
		<%
			for(String x : amici){
		%>
			<%=x.toString() %><br/>		
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
	
<%@include file="footer.html"%>							