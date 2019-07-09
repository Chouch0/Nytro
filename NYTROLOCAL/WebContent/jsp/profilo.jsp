<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="nytro.model.AccountBean, java.util.ArrayList"%>

<%  
	AccountBean account = (AccountBean) session.getAttribute("account");
	ArrayList<String> contributo = (ArrayList<String>) request.getAttribute("contributo");
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Profilo"/>	</jsp:include>	


<h1><%=account.getUsername() %></h1>

<p>
	<%=account.toString()%>
</p>

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
</script>

<h1>Cambia cose</h1>

<h2>Cambia password</h2>
<form name="cambiamoPassword" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="password" name="cambiaPassword" oninput="validaPassword()" placeholder="Password" required> 
<input type="password" name="cambiaPasswordConferma" oninput="validaPassword()" placeholder="Conferma Password" required> 
<p id="errorPssw"></p>
<input id="subPassword" type="submit" value="Vai" disabled>
</form>

<h2>Cambia e-mail</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="email" name="cambiaEmail" placeholder="e-mail" required>
<input type="submit" value="Vai">
</form>

<h2>Cambia e-mail recupero</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="email" name="cambiaEmailRecupero" placeholder="e-mail recupro" required>
<input type="submit" value="Vai">
</form>

<h2>Cambia cellulare</h2>
<form name="cambiamoTelefono" action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input id="cambiaTelefono" type="tel" name="phone" oninput="validaTelefono()" placeholder="Cellulare">
<p id="errorPhone"></p>
<input id="subPhone" type="submit" value="Vai" disabled>
</form>

<h2>Cambia immagine del profilo</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="file" name="photo" size="50"/>
<input type="submit" value="Vai">
</form>

<%if(account.getRuolo()==0){ %>
<h2>Cambia nome</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaNome" placeholder="Nome" required>
<input type="submit" value="Vai">
</form>

<h2>Cambia cognome</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaCognome" placeholder="Cognome" required>
<input type="submit" value="Vai">
</form>

<%} %>

<%if(account.getRuolo()==1){ %>
<h2>Cambia data di nascita</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="hidden" name="cambiaDataNascita" value="true">
<label>Data di nascita<input type="date" name="newDataDiNascita" min="1900-01-01" max="2032-12-31"></label>
<input type="submit" value="Vai">
</form>

<h2>Cambia genere</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="radio" name="cambiaGenere" value="m" required> Maschile<br>
<input type="radio" name="cambiaGenere" value="f"> Femminile<br>
<input type="submit" value="Vai">
</form>
<%} %>

<%if(account.getRuolo()==2){ %>
<h2>Cambia nome casa editrice</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaNomeCasaEditrice" placeholder="Nome casa editrice" required>
<input type="submit" value="Vai">
</form>

<h2>Cambia CEO</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaCEO" placeholder="Nome CEO" required>
<input type="submit" value="Vai">
</form>

<h2>Cambia sito web</h2>
<form action="<%=response.encodeURL("/NYTRO/AggiornaProfilo")%>" method="post" enctype="multipart/form-data">
<input type="text" name="cambiaSitoWEB" placeholder="Sito web" required>
<input type="submit" value="Vai">
</form>
<%}%>

<%if(account.getRuolo()==0){ %>
<h1>Insights</h1>

<h2>Ricavi piattaforma</h2>
<form action="<%=response.encodeURL("/NYTRO/Profilo")%>">
<input type="hidden" name="contributoAnnuale" value="true">
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
			<%if(i%3==0){ %>
				<br/>
			<%} else { %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<%} %>
		<%} %>
	</p>
	<%} %>
<%} %>

<%if(account.getRuolo()==2){ %>
<h1>Insights</h1>

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

<%@include file="footer.jsp"%>						