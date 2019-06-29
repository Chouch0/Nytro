<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> libreria = (Collection<VideogiocoBean>) request.getAttribute("libreria");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Libreria"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="/NYTRO/Libreria" method="get">
	<label>Seleziona un criterio di ordinamento
	 <select name="order">
	  <option value="" selected>Nessuno</option>
	  <option value="Data_Rilascio">Data di rilascio</option>
	  <option value="Titolo">Titolo</option>
	  <option value="Voto_Medio">Voto medio delle recensioni</option>
	  <option value="PEGI">PEGI</option>
	</select> 
	</label>
	<input type="submit" value="Vai"/>
	</form>
	
	<h1>Libreria</h1>
	
	<p>
		<%
			for(VideogiocoBean x : libreria){
		%>
			<%=x.toString() %> <form action="/NYTRO/Libreria?cancellaVideogioco=<%=x.getCodice()%>" method="post"> <input type="submit" value="Rimuovi"/></form>
			<br/>		
		<%
			}
		%>
	</p>
	
<%@include file="footer.html"%>							