<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection, nytro.model.AccountBean, nytro.model.RecensioneBean"%>
<%
	VideogiocoBean videogiocoDetailed = (VideogiocoBean) request.getAttribute("videogiocoDetailed");
	Collection<RecensioneBean> recensioni = (Collection<RecensioneBean>) request.getAttribute("recensioni");
	AccountBean account = (AccountBean) session.getAttribute("account");
	String possibileAggiungereAllaLibreria = (String) request.getAttribute("possibileAggiungereAllaLibreria");
	String possibileAggiungereAgliAcquisti = (String) request.getAttribute("possibileAggiungereAgliAcquisti");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="<%=videogiocoDetailed.getTitolo() %>"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<h1><%=videogiocoDetailed.getTitolo() %></h1>
	
	<p>
		<%=videogiocoDetailed.toString()%>
		<%if(possibileAggiungereAgliAcquisti!=null && possibileAggiungereAgliAcquisti.equalsIgnoreCase("true") && 
				videogiocoDetailed.getClass().getSimpleName().equals("VideogiocoPagamentoBean")){ %>
			<a href="GestoreCarrello?action=addCart&codiceVideogioco=<%=videogiocoDetailed.getCodice()%>">Inserisci nel carrello</a><br/>
		<%} %>
	</p>
	
	<form action="/NYTRO/Videogioco" method="get">
	<input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
	<label>Seleziona un criterio di ordinamento
	 <select name="orderRecensioni">
	  <option value="" selected>Nessuno</option>
	  <option value="Num_Recensione">Numero di recensione del videogioco</option>
	  <option value="Username">Username di chi ha rilasciato la recensione</option>
	  <option value="Voto">Voto della recensione</option>
	</select> 
	</label>	
	<input type="submit" value="Vai"/>
	</form>
	
	<h2>Recensioni</h2>
	<%if(recensioni!=null){ 
		for(RecensioneBean x : recensioni){%>
		
		<%=x.toString()%>
		<%if(x.getUsername().equals(account.getUsername())) {%>
			<form action="/NYTRO/Videogioco" method="post">
			 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
			 <input type="hidden" name="rimuovereRecensione" value="true">
			 <input type="submit" value="Rimuovi recensione">
			</form>
		<%} %>
		<br/>
	<%	}
	  }
	%>
	
	<%if(account.getRuolo()==1) {%>
		<h3>Inserisci recensione</h3>
		
		<form action="/NYTRO/Videogioco" method="post">
		 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
		 <textarea rows="4" cols="50" name="commentoRecensione"> </textarea> 
		 <label>Inserisci voto: <input type="number" name="votoRecensione" min="1" max="5" step="0.25"></label>
		 <input type="submit" value="Inserisci">
		</form>
				
		<%} %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#esplora").addClass("selected");
		})
	</script>
<%@include file="footer.html"%>							