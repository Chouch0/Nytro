<%@page import="nytro.model.AmministratoreBean"%>
<%@page import="nytro.model.CasaEditriceBean"%>
<%@page import="nytro.model.GiocatoreBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="nytro.model.AccountBean, java.util.ArrayList, nytro.model.VideogiocoBean"%>

<%  
	AccountBean account = (AccountBean) session.getAttribute("account");
	ArrayList<String> contributo = (ArrayList<String>) request.getAttribute("contributo");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Profilo"/>	</jsp:include>	
<link href="/NYTRO/css/profiloStyle.css" type="text/css" rel="stylesheet">	
<div id="informazioniAccount">
<div id="account">
	<h1><%=account.getUsername() %></h1>
	<div id="imgProfilo">
	<% if(account.getImgProfilo() != null) {%>
		<img src="/NYTRO/image?id=<%=account.getUsername() %>" alt="<%=account.getUsername()%>">
	<%} else {%>
		<img src="/NYTRO/img/default-profile.png" alt="<%=account.getUsername()%>">
	<%} %>
	</div>
	<p class="opzione" onclick='show(document.getElementById("cambiaImg"))'>Cambia immagine del profilo</p>
	<form id="cambiaImg" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
		<input type="file" name="photo" size="50"/>
		<input type="submit" value="Vai">
	</form>
</div>
<div id="modifiche1">
<h2 class="opzione" onclick='show(document.getElementById("pwd"))'>Modifica password</h2>
<p>Per modificare la password dovrai prima inserire quella attuale.</p>
<form id="pwd" name="cambiamoPassword" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="password" name="vecchiaPassword" placeholder="Vecchia password*" required>
<input type="password" name="cambiaPassword" oninput="validaPassword()" placeholder="Password*" required> 
<input type="password" name="cambiaPasswordConferma" oninput="validaPassword()" placeholder="Conferma Password*" required> 
<p id="errorPssw"></p>
<input id="subPassword" type="submit" value="Vai" disabled>
</form>

<h2 class="opzione" onclick='show(document.getElementById("email"))'>Modifica e-mail</h2>
<p><%=account.getEmail() %></p>
<form id="email" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="email" name="cambiaEmail" placeholder="e-mail" required>
<input type="submit" value="Vai">
</form>
<h2 class="opzione" onclick='show(document.getElementById("emailRec"))'>Modifica e-mail recupero</h2>
<p><%=account.getEmailRecupero() %></p>
<form id="emailRec" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="email" name="cambiaEmailRecupero" placeholder="e-mail recupro" required>
<input type="submit" value="Vai">
</form>
</div>
<div id ="modifiche2">
<%if(account.getCellulare() != null) {%>
	<h2 class="opzione" onclick='show(document.getElementById("cell"))'>Modifica cellulare</h2>
	<p><%=account.getCellulare() %></p>
<%}else {%>
	<h2 class="opzione" onclick='show(document.getElementById("cell"))'>Inserisci cellulare</h2>
	<p>Molte persone hanno un solo livello di protezione dei loro account: la password.<br/>Con la verifica in due passaggi, se un malintenzionato supera il livello della password, dovrà comunque avere il tuo telefono per accedere al tuo account.</p>
<%} %>
<form id="cell" name="cambiamoTelefono" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input id="cambiaTelefono" type="tel" name="phone" oninput="validaTelefono()" placeholder="Cellulare">
<p id="errorPhone"></p>
<input id="subPhone" type="submit" value="Vai" disabled>
</form>

<%if(account.getRuolo()==0){ 
AmministratoreBean x = (AmministratoreBean) account;%>

<h2 class="opzione" onclick='show(document.getElementById("nomeAdmin"))'>Cambia nome</h2>
<p><%=x.getNome() %></p>
<form id="nomeAdmin" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaNome" placeholder="Nome" required>
<input type="submit" value="Vai">
</form>

<h2 class="opzione" onclick='show(document.getElementById("cognome"))'>Cambia cognome</h2>
<p><%=x.getCognome() %></p>
<form id="cognome" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaCognome" placeholder="Cognome" required>
<input type="submit" value="Vai">
</form>

<%} %>

<%if(account.getRuolo()==1){
GiocatoreBean x = (GiocatoreBean) account;
if(x.getDataNascita() == null) {%>
	<h2 class="opzione" onclick='show(document.getElementById("nascita"))'>Inserisci data di nascita</h2>
	<p>Inserire la tua data di nascita ti darà la possibilità di accedere a videogiochi soggetti a limiti di età.</p>
<%}%>
<form id="nascita" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="hidden" name="cambiaDataNascita" value="true">
<label>Data di nascita<input type="date" name="newDataDiNascita" min="1900-01-01" max="2032-12-31"></label>
<input type="submit" value="Vai">
</form>
<% if(x.getGenere() == null) {%>
	<h2 class="opzione" onclick='show(document.getElementById("genere"))'>Inserisci genere</h2>
	<p>Inserire il tuo genere ci darà la possibilità di consigliarti videogiochi più adatti alle tue esigenze.</p>
<%}%>
<form id="genere" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="radio" name="cambiaGenere" value="m" required> Maschile<br>
<input type="radio" name="cambiaGenere" value="f"> Femminile<br>
<input type="submit" value="Vai">
</form>
<%} %>

<%if(account.getRuolo()==2){ 
CasaEditriceBean x = (CasaEditriceBean) account;%>
<h2 class="opzione" onclick='show(document.getElementById("casa"))'>Modifica il nome della casa editrice</h2>
<p><%=x.getNomeCasaEditrice() %></p>
<form id="casa" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaNomeCasaEditrice" placeholder="Nome casa editrice" required>
<input type="submit" value="Vai">
</form>

<h2 class="opzione" onclick='show(document.getElementById("ceo"))'>Modifica CEO</h2>
<p><%=x.getCEO() %></p>
<form id="ceo" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaCEO" placeholder="Nome CEO" required>
<input type="submit" value="Vai">
</form>

<h2 class="opzione" onclick='show(document.getElementById("sito"))'>Modifica sito web</h2>
<p><%=x.getSitoWeb() %></p>
<form id="sito" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaSitoWEB" placeholder="Sito web" required>
<input type="submit" value="Vai">
</form>
<%}%>
</div>
<%if(account.getRuolo()==0){ %>
<div id="insights">
<div id="in">
<h1>Insights</h1>
</div>
<div class="admin">
<h2>Ricavi piattaforma</h2>
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="contributoAnnuale" value="true">
<label>Data di inizio<input type="date" name="startDate" min="2000-01-01" max="2032-12-31"  required></label>
<label>Data di fine<input type="date" name="endDate" min="2000-01-01" max="2032-12-31"  required></label>
<input type="submit" value="Vai"/>
</form>

	<%int i = 0;
	if(contributo!=null){ %>
	<p>
		<%for(String x : contributo) {
			i++;
		%>
			<%=x.toString()%>
			<%if(i%3==0){ %>
				<br/>
			<%} else { %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%} %>
		<%} %>
	</p>
	<%} %>
</div>
<div id ="admin">
<div class="admin">
<h2>Videogioco più giocato dai ragazzi</h2>
<%
	VideogiocoBean piuGiocatoMaschi = (VideogiocoBean) request.getAttribute("piuGiocatoMaschi");
	if(piuGiocatoMaschi!=null){
%>
	<p><b><%=piuGiocatoMaschi.getTitolo()%></b></p>
	<p><%=piuGiocatoMaschi.getGenere().toString().substring(1, piuGiocatoMaschi.getGenere().toString().length()-1) %></p>
<%} %>
<h2>Videogioco più giocato dalle ragazze</h2>	
<%
	VideogiocoBean piuGiocatoFemmine = (VideogiocoBean) request.getAttribute("piuGiocatoFemmine");
	if(piuGiocatoFemmine!=null){
%>
	<p><b><%=piuGiocatoFemmine.getTitolo()%></b></p>
	<p><%=piuGiocatoFemmine.getGenere().toString().substring(1, piuGiocatoFemmine.getGenere().toString().length()-1) %></p>
<%} %>
</div>
<div class=admin>
<h2>Videogiochi rimossi in un determinato anno</h2>	
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="listaVideogiochiRimossi" value="true">
<label>Anno di rimozione<input type="number" name="annoRimozione" min="2000" step="1" required></label>
<input type="submit" value="Vai"/>
</form>

<%ArrayList<VideogiocoBean> videogiochiRimossiInAnno = (ArrayList<VideogiocoBean>) request.getAttribute("videogiochiRimossiInAnno");
if(videogiochiRimossiInAnno!=null){
	for(VideogiocoBean x : videogiochiRimossiInAnno){%>
	<%=x.toString()%>
<%}
} %>

<h2>Numero di giocatori compreso entro un certo range di età</h2>	
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="rangeEtaGiocatori" value="true">
<label>Età minima:<input type="number" name="minEta" min="0" step="1" required></label>
<label>Età massima:<input type="number" name="maxEta" min="0" step="1" required></label>
<input type="submit" value="Vai"/>
</form>

<%String quantiGiocatori = (String) request.getAttribute("quantiGiocatori");
if(quantiGiocatori!=null && !quantiGiocatori.equals("")){%>
	<%=quantiGiocatori %> <!-- Sto passando una stringa complessa, con testo e numero, in modo tale da mantenere minEta e maxEta -->
<%} %>
<h2>Videogioco più giocato dai giocatori di età compreso entro un certo range</h2>	
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="rangeEtaGiocatoriVideogioco" value="true">
<label>Età minima:<input type="number" name="minEtaVideogioco" min="0" step="1" required></label>
<label>Età massima:<input type="number" name="maxEtaVideogioco" min="0" step="1" required></label>
<input type="submit" value="Vai"/>
</form>
	
</div>
<%VideogiocoBean videogiocoPiuGiocatoDa = (VideogiocoBean) request.getAttribute("videogiocoPiuGiocatoDa");
if(videogiocoPiuGiocatoDa!=null){%>
	<%=videogiocoPiuGiocatoDa.toString() %> 
<%} %>	

<%} %>
</div>
<%if(account.getRuolo()==2){ %>
<div id="insights">
<h2>Contributo casa editrice</h2>
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="contributoAnnualeCasaEditrice" value="true">
<label>Data di inizio<input type="date" name="startDate" min="2000-01-01" max="2032-12-31"></label>
<label>Data di fine<input type="date" name="endDate" min="2000-01-01" max="2032-12-31"></label>
<input type="submit" value="Vai"/>
</form>
	<%int i = 0;
	if(contributo!=null){ %>
	<p>
		<%for(String x : contributo) {
			i++;
		%>
			<%=x.toString()%>
			<%if(i%2==0){ %>
				<br/>
			<%} else { %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%} %>
		<%} %>
	</p>
	<%} %>
<%} %>
</div>
</div>
<script type="text/javascript">
		
		var borderOk = '2px solid green';
		var borderNo = '2px solid red';
		var passwordOk = false;
		
		function validaPassword() {
			var inputpw = document.forms['cambiamoPassword']['cambiaPassword'];
			var inputpwconf = document.forms['cambiamoPassword']['cambiaPasswordConferma'];
			
			var password = inputpw.value;
			
			if (password.length >= 8 && password.toUpperCase() != password
					&& password.toLowerCase() != password && /[0-9]/.test(password)) {
				inputpw.style.border = borderOk;
	
				if (password == inputpwconf.value) {
					inputpwconf.style.border = borderOk;
					document.getElementById("errorPssw").innerHTML = "";
					passwordOk = true;
				} else {
					inputpwconf.style.border = borderNo;
					document.getElementById("errorPssw").innerHTML = "Le due password devono coincidere"
					passwordOk = false;
				}
			} else {
				inputpw.style.border = borderNo;
				inputpwconf.style.border = borderNo;
				document.getElementById("errorPssw").innerHTML = "La password deve contenere almeno una maiuscola, un numero e almeno 8 caratteri";
				passwordOk = false;
			}
			if(passwordOk) {
				document.getElementById("subPassword").disabled = false;
			} else {
				document.getElementById("subPassword").disabled = true;
			}
		}
		
		var phoneOk=false;
		
		function validaTelefono() {
			var input = document.forms['cambiamoTelefono']['phone'];
			if(input.value.match(/^\d{10}$/)) {
				input.style.border = borderOk;
				document.getElementById("errorPhone").innerHTML = "";
				document.getElementById("subPhone").disabled = false;
				phoneOk = true;
			} else {
				input.style.border = borderNo;
				document.getElementById("errorPhone").innerHTML = "Formato sbagliato";
				document.getElementById("subPhone").disabled = true;
				phoneOk = false;
			}
			
			if(phoneOk) {
				document.getElementById("subPhone").disabled = false;
			} else {
				document.getElementById("subPhone").disabled = true;
			}
		}
		
		function show(elem) {
			if(elem.style.display == "none")
				elem.style.display ="block";
			else
				elem.style.display ="none";	
		}
	</script>
<%@include file="footer.jsp"%>						