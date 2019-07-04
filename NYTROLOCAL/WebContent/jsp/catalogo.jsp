<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> catalogo = (Collection<VideogiocoBean>) request.getAttribute("catalogo");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Catalogo"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="/NYTRO/Catalogo" method="get">
	<label>Seleziona un criterio di ordinamento
	 <select name="order">
	  <option value="" selected>Nessuno</option>
	  <option value="Data_Rilascio">Data di rilascio</option>
	  <option value="Titolo">Titolo</option>
	  <option value="Voto_Medio">Voto medio delle recensioni</option>
	  <option value="PEGI">PEGI</option>
	</select> 
	</label>	
	<label>Seleziona la categoria di videogiochi che vuoi visualizzare
	 <select name="categoria">
	  <option value="" selected>Nessuno</option>
	  <option value="A pagamento">A pagamento</option>
	  <option value="Demo">Demo</option>
	  <option value="Free to play">Free to play</option>
	</select> 
	</label>
	<input type="submit" value="Vai"/>
	</form>
	
	<h1>Catalogo completo videogiochi</h1>
	
	<p>
		<%
			for(VideogiocoBean x : catalogo){
				if(x.getDataRimozione()== null){
		%>
			<%=x.toString() %><br/>	<span class = "buttonLink"><a href="/NYTRO/Videogioco?codiceVideogioco=<%=x.getCodice()%>">Informazioni</a></span><br/>
			<!--  Se utilizzo un bottone non riesco a passare il parametro codice utilizzando lo scriptlet -->	
		<%
				}
			}
		%>
	</p>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#esplora").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							