<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection, nytro.model.AccountBean"%>
<%
	Collection<VideogiocoBean> libreria = (Collection<VideogiocoBean>) request.getAttribute("libreria");
	String libreriaAmicoDaVisualizzare=request.getParameter("libreriaAmicoDaVisualizzare");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Libreria"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
<link href="/NYTRO/css/libreriaStyle.css" type="text/css" rel="stylesheet">	

<div id="lista">
	<h1>Libreria - 
		<%if(libreriaAmicoDaVisualizzare!=null && !libreriaAmicoDaVisualizzare.equals("")) {%>
			<%=libreriaAmicoDaVisualizzare %>
		<%} else { 
			AccountBean account = (AccountBean) request.getSession().getAttribute("account");%>
			<%=account.getUsername() %>
		<%} %>
	</h1>
	<div id="ordina">
	<form action="<%=response.encodeURL("/NYTRO/Libreria")%>" method="get">
	 <%if(libreriaAmicoDaVisualizzare!=null && !libreriaAmicoDaVisualizzare.equals("")){ %>
	 	<input type="hidden" name="libreriaAmicoDaVisualizzare" value="<%=libreriaAmicoDaVisualizzare %>"/>
	 <%} %>
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
					System.out.println("\t\t"+x);
			%>
		<ul>
		<div class="gioco">
			<li><div class="copertina">
			<%if (x.getImg() != null){%>
					<img src="/NYTRO/image?codice=<%= x.getCodice()%>" alt="<%=x.getTitolo()%>">
			<%} else {%>
					<img src="/NYTRO/img/no-cover.jpg" alt="<%=x.getTitolo()%>">
			<%} %></div></li>			
			<li><a href="/NYTRO/img/Nytro.png" download><button id="download">Download</button></a></li>
			<li class="titolo"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>"><%=x.getTitolo()%></a></li>
			<li><%=x.getISIN() %></li>
			<li>Voto: <%=x.getVotoMedio() %></li>
			<li><form action="<%=response.encodeURL("/NYTRO/Libreria?cancellaVideogioco="+x.getCodice())%>" method="post"> <input type="submit" value="Rimuovi"/></form>
			</li>
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
<%@include file="footer.jsp"%>				