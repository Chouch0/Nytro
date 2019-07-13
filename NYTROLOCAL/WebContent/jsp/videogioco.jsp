<%@page import="nytro.model.CasaEditriceBean"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection, nytro.model.AccountBean, nytro.model.RecensioneBean, nytro.model.GiocatoreBean"%>
<%
	VideogiocoBean videogiocoDetailed = (VideogiocoBean) request.getAttribute("videogiocoDetailed");
	Collection<RecensioneBean> recensioni = (Collection<RecensioneBean>) request.getAttribute("recensioni");
	Collection<AccountBean> amici = (Collection<AccountBean>) request.getAttribute("amici");
	AccountBean account = (AccountBean) session.getAttribute("account");
	String possibileAggiungereAllaLibreria = (String) request.getAttribute("possibileAggiungereAllaLibreria");
	String possibileAggiungereAgliAcquisti = (String) request.getAttribute("possibileAggiungereAgliAcquisti");
	if(videogiocoDetailed==null){
		String url = response.encodeURL("Videogioco");
		response.sendRedirect(url);
		return ;
	}
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="<%=videogiocoDetailed.getTitolo() %>"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<h1><%=videogiocoDetailed.getTitolo() %></h1>
	
	<p>
		<%=videogiocoDetailed.toString()%>
		
		<%
		if(account.getRuolo()==1){
			GiocatoreBean giocatore = (GiocatoreBean) request.getAttribute("account");
			if(videogiocoDetailed.getClass().getSimpleName().equals("VideogiocoPagamentoBean")){
				if(possibileAggiungereAgliAcquisti!=null && possibileAggiungereAgliAcquisti.equalsIgnoreCase("true")){
					String url = response.encodeURL("GestoreCarrello?action=addCart&codiceVideogioco="+videogiocoDetailed.getCodice());
					if(giocatore.getDataNascita() == null && videogiocoDetailed.getPEGI() >= 18){
						%><a href="<%=response.encodeURL("/NYTRO/Profilo")%>">E necessario inserire la data di nascita per procedere all'acquisto.</a><%
					} else if(giocatore.getDataNascita()!= null && videogiocoDetailed.getPEGI() >= 18){
						LocalDate data = LocalDate.parse(giocatore.getDataNascita());
						if (LocalDate.now().getYear() - data.getYear() < 18) {
						%><p>E necessario avere 18+ anni per procedere all'acquisto.</p> <%
						} 
					}else {
						%><a href="<%=url%>">Inserisci nel carrello</a><br/><%
						}
					}
				} else {
					if(possibileAggiungereAllaLibreria!=null && possibileAggiungereAllaLibreria.equalsIgnoreCase("true")){
						%><a href="<%=response.encodeURL("Libreria?aggiungiVideogioco="+videogiocoDetailed.getCodice())%>">Inserisci nella libreria</a><br/><%
					} 
				}
			} else {
				if(possibileAggiungereAllaLibreria!=null && possibileAggiungereAllaLibreria.equalsIgnoreCase("true")){
					 %><a href="<%=response.encodeURL("Libreria?aggiungiVideogioco="+videogiocoDetailed.getCodice())%>">Inserisci nella libreria</a><br/><%
				} 			
			}
		%>	
	</p>
	
	
	<%if(account.getRuolo()==1 && amici!=null){%>
		<h2>Lista degli amici che posseggono lo stesso gioco</h2>
		<%for(AccountBean x : amici){%>
		<%=x.toString()%><br/>		
	<%} 
	}%>
	
	
	<h2>Recensioni</h2>
	<p>
	<%if(recensioni!=null){ 
	%>
		<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="get">
		<input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
		<label>Seleziona un criterio di ordinamento
		 <select name="orderRecensioni">
		  <option value="" selected>Nessuno</option>
		  <option value="Num_Recensione">Numero di recensione del videogioco</option>
		  <option value="Username">Username di chi ha rilasciato la recensione</option>
		  <option value="Voto">Voto della recensione</option>
		 </select> 
		</label>	
		<input type="hidden" name="rangeRecensione" value="true">
		 <label>Voto minimo:<input type="number" name="min" min="1" max="5" step="1" value="1" required></label>
		 <label>Voto massimo:<input type="number" name="max" min="1" max="5" step="1" value="5" required></label>
		<input type="submit" value="Vai"/>
		</form>
	<%	
		for(RecensioneBean x : recensioni){%>
		
		
		<%=x.toString()%>
		<%if(x.getUsername().equals(account.getUsername())) {%>
			<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post">
			 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
			 <input type="hidden" name="rimuovereRecensione" value="true">
			 <input type="submit" value="Rimuovi recensione">
			</form>
		<%} %>
		<br/>
	<%	}
	  }
	%>
	</p>
	<%if(account.getRuolo()==1) {%>
		<h3>Inserisci recensione</h3>
		
		<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post">
		 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">		<!-- Mi serve perchè se no perdo il codice del videogioco -->
		 <textarea rows="4" cols="50" name="commentoRecensione" required> </textarea> 
		 <label>Inserisci voto: <input type="number" name="votoRecensione" min="1" max="5" step="0.25" required></label>
		 <input type="submit" value="Inserisci">
		</form>
				
		<%} %>
		
	<%if(account.getRuolo()==2) {
		CasaEditriceBean casa = (CasaEditriceBean) request.getAttribute("account");
		if (casa.getISIN().equals(videogiocoDetailed.getISIN())){%>
		<h3>Cambia immagine del videogioco</h3>
		
		<form action="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+videogiocoDetailed.getCodice())%>" method="post" enctype="multipart/form-data">
		 <input type="hidden" name="cambiaImmagineVideogioco" value="true" required>
	 	 <input type="file" name="photo" size="50"/>
		 <input type="submit" value="Vai">
		</form>
		
		<h3>Cambia trailer</h3>
		
		<form action="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+videogiocoDetailed.getCodice())%>" method="post">
	 	 <input type="text" name="cambiaTrailer" required placeholder="Trailer*"/>
		 <input type="submit" value="Vai">
		</form>
		
		<h3>Aggiungi genere</h3>
		
		<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post" enctype="multipart/form-data">
		 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
		 <input type="hidden" name="cambiaGenere" value="true">
	 	 <input type="text" name="newGenere" placeholder="Genere*" required/>
		 <input type="submit" value="Vai">
		</form>
		
		<%if(videogiocoDetailed.getClass().getSimpleName().equals("VideogiocoPagamentoBean")) {%>
			<h3>Cambia prezzo</h3>
		
			<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
			 <input type="hidden" name="cambiaPrezzo" value="true">
		 	 <input type="number" name="newPrezzo" min="0" step="0.01" required/>
			 <input type="submit" value="Vai">
			</form>
		<%} %>
		
		<%if(videogiocoDetailed.getClass().getSimpleName().equals("VideogiocoDemoBean")) {%>
			<h3>Cambia durata</h3>
		
			<form action="<%=response.encodeURL("/NYTRO/Videogioco")%>" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="codiceVideogioco" value="<%=videogiocoDetailed.getCodice()%>">
			 <input type="hidden" name="cambiaDurataDemo" value="true">
		 	 <input type="number" name="newDurataDemo" min="1" step="1" required/>
			 <input type="submit" value="Vai">
			</form>
		<%} %>
				
	<%}} %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#esplora").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>