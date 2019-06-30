<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, nytro.model.AccountBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> catalogoCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("catalogoCasaEditrice");
	String isinCasaEditrice = (String) request.getAttribute("isinCasaEditrice");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Catalogo casa editrice"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="/NYTRO/CatalogoCasaEditrice" method="get">
		<input type="hidden" name="isinCasaEditrice" value="<%=isinCasaEditrice%>">
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
	 
	<h1>Catalogo completo dei videogiochi della casa editrice</h1>
	 
	<p>
		<%
			for(VideogiocoBean x : catalogoCasaEditrice){
		%>
			<%=x.toString() %><br/>	<span class = "buttonLink"><a href="/NYTRO/Videogioco?codiceVideogioco=<%=x.getCodice()%>">Informazioni</a></span><br/>
			<!--  Se utilizzo un bottone non riesco a passare il parametro codice utilizzando lo scriptlet -->	
		<%
			}
		%>
	</p>
	
<%@include file="footer.html"%>							