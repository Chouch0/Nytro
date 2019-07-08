<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection"%>
<%
	Collection<VideogiocoBean> videogiochi = (Collection<VideogiocoBean>) request.getAttribute("videogiochi");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Pubblicazioni"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>" method="get">
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
	
	<h1>Lista videogiochi</h1>
	
	<%if(videogiochi!=null){ %>
		<p>
			<%
				for(VideogiocoBean x : videogiochi){
			%>
				<%=x.toString() %><span class = "buttonLink"><a href="<%=response.encodeURL("/NYTRO/Videogioco?codiceVideogioco="+x.getCodice())%>">Informazioni</a></span>	
				<%if(x.getDataRimozione()==null) {%>
					<a href="<%=response.encodeURL("/NYTRO/Pubblicazioni?cancelVideogioco="+x.getCodice())%>">Rimuovi videogioco</a>
				<%} %>
				<br/>
			<%
				}
			%>
		</p>
	<%} %>
	
	<script>
	
	function addPagamentoFields() {
		var container = document.getElementById("aggiungiVideogiocoForm");
		//Ripulisco dai vecchi figli
		while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
		
		var inputPrezzo = document.createElement("input");
		inputPrezzo.type="number";
		inputPrezzo.name="aggPrezzo";
		inputPrezzo.min="0";
		inputPrezzo.step="0.01";
		inputPrezzo.required = true;
		
		container.appendChild(document.createTextNode("Prezzo:"));
		container.appendChild(inputPrezzo);
		container.appendChild(document.createElement("br"));
	}
	 
	
	function addDemoFields() {
		var container = document.getElementById("aggiungiVideogiocoForm");
		//Ripulisco dai vecchi figli
		while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
		
		var inputCodiceVideogiocoPrincipale = document.createElement("input");
		inputCodiceVideogiocoPrincipale.type="number";
		inputCodiceVideogiocoPrincipale.name="aggCodiceVideogiocoPrincipale";
		inputCodiceVideogiocoPrincipale.min="1";
		inputCodiceVideogiocoPrincipale.step="1";
		inputCodiceVideogiocoPrincipale.required = true;
		
		var inputDurata = document.createElement("input");
		inputDurata.type="number";
		inputDurata.name="aggDurata";
		inputDurata.min="1";
		inputDurata.step="1";
		inputDurata.required = true;
		
		container.appendChild(document.createTextNode("Codice videogioco Principale:"));
		container.appendChild(inputCodiceVideogiocoPrincipale);
		container.appendChild(document.createElement("br"));
		
		container.appendChild(document.createTextNode("Durata:"));
		container.appendChild(inputDurata);
		container.appendChild(document.createElement("br"));
		
	}
	
	function addFreeToPlayFields() {
		var container = document.getElementById("aggiungiVideogiocoForm");
		//Ripulisco dai vecchi figli
		while (container.hasChildNodes()) {
            container.removeChild(container.lastChild);
        }
		
		var inputOnline = document.createElement("input");
		inputOnline.type="radio";
		inputOnline.name="aggModalita";
		inputOnline.value="online";
		inputOnline.required = true;
		
		var inputOffline = document.createElement("input");
		inputOffline.type="radio";
		inputOffline.name="aggModalita";
		inputOffline.value="offline";
		inputOffline.required = true;
		
		container.appendChild(document.createTextNode("Modalità"));
		container.appendChild(inputOnline);
		container.appendChild(document.createTextNode("online"));
		container.appendChild(inputOffline);
		container.appendChild(document.createTextNode("offline"));
		container.appendChild(document.createElement("br"));
	}
	</script>
	
	<h2>Aggiungi videogioco</h2>
	<form action="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>" method="post" enctype="multipart/form-data">
		<input type="hidden" name="aggiungiVideogioco" value="true">
		<label>Titolo: <input type="text" name="aggTitolo" placeholder="Titolo*" required></label>
		<label>PEGI: <input type="number" name="aggPegi" min="1" max="18" step="1" required></label>
		<label>Genere: <input type="text" name="aggGenere" placeholder="Genere*" required></label>
		<label><br/>Tipologia:<br/>
			<input type="radio" name="tipologia" value="aPagamento" onclick="addPagamentoFields()" required> Videogioco a pagamento<br/>
			<input type="radio" name="tipologia" value="freeToPlay" onclick="addFreeToPlayFields()" required> Videogioco free to play<br/>
			<input type="radio" name="tipologia" value="demo" onclick="addDemoFields()" required> Videogioco demo <br/> 
		</label>
		<input type="file" name="photo" size="50">
		<div id="aggiungiVideogiocoForm"></div>
		<input type="submit" value="Aggiungi">
	</form>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
		$("document").ready(function prova(){
			$("#user").addClass("selected");
		})
	</script>
<%@include file="footer.jsp"%>							