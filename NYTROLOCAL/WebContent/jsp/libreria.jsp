<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> libreria = (Collection<VideogiocoBean>) request.getAttribute("libreria");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Libreria"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/libreriaStyle.css" type="text/css" rel="stylesheet">	

<div id="lista">
	<h1>Libreria</h1>
	<div id="ordina">
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
	</div>
	<%if(libreria!=null) {%>
		<div id="tabella">
			<%
				for(VideogiocoBean x : libreria){
			%>
		<ul>
		<div class="gioco">
			<li><div class="copertina">
			<%if (x.getImg() != null){%>
					<img src="/NYTRO/image?codice=<%= x.getCodice()%>" alt="<%=x.getTitolo()%>">
			<%} else {%>
					<img src="/NYTRO/img/no-cover.jpg" alt="<%=x.getTitolo()%>">
			<%} %></div></li>
			<li><form action="/NYTRO/Libreria?cancellaVideogioco=<%=x.getCodice()%>" method="post"> <input type="submit" value="Rimuovi"/></form>
			</li>
			<li class="titolo"><a href="/NYTRO/Videogioco?codiceVideogioco=<%=x.getCodice()%>"><%=x.getTitolo() %></a></li>
			<li><%=x.getISIN() %></li>
			<li>Voto: <%=x.getVotoMedio() %></li>
			<li><a href="/NYTRO/img/Nytro.png" download>Download</a></li>
		</div>
		</ul>	
			<%
				}
			%>
		
	<%} %>
	</div>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#libreria").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							