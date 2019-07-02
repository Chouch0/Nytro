<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Registrazione"/>	</jsp:include>
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">


<form class="box" name="registrazione" action="/NYTRO/RegistrazioneUtente" method="post"> 
	<input class="field" name="username" type="text" oninput="validaUsername()" placeholder="Username*"> 
	<p id="errorUsr"></p>  
	<input class="field" type="password" name="password" oninput="validaPassword()" placeholder="Password*"> 
	<input class="field" type="password" name="passwordConferma" oninput="validaPassword()" placeholder="Conferma Password*"> 
	<p id="errorPssw"></p>
	<input class="field" type="text" name="email" oninput="validaEmail()" placeholder="Email*">	
	<p id="errorMail"></p>
	<input class="field" type="text" name="emailRec" oninput="validaEmailRec()" placeholder="Email di recupero*">
	<p id="errorMailRec"></p>
	<input class="field" id="telefono" type="tel" name="phone" oninput="validaTelefono()" placeholder="Cellulare">
	<p id="errorPhone"></p>
	<p id="errorSub">Compila tutti i campi</p>	
	<input type="submit" value="Registrati" id="sub" disabled>		
</form>
	
<script type="text/javascript">
	
	var borderOk = '2px solid green';
	var borderNo = '2px solid red';
	var usernameOk = false;
	var passwordOk = false;
	var emailOk = false;
	var emailRecOk = false;
	
	function validaUsername() {
		var ok = "<ok/>";
		var input = document.forms['registrazione']['username'];
		if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
			// verifica se esisten un utente con lo stresso username
		
			var xmlHttpReq = new XMLHttpRequest();
			xmlHttpReq.onreadystatechange = function() {
				if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == ok) {
					input.style.border = borderOk;
					document.getElementById("errorUsr").innerHTML = "";
					usernameOk = true;
				} else if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != ok){
					input.style.border = borderNo;
					document.getElementById("errorUsr").innerHTML = "Attenzione! Questo Username esiste già.";
					usernameOk = false;
				}
				checkForm();
			} 
			xmlHttpReq.open("GET", "/NYTRO/VerificaUsername?username=" + encodeURIComponent(input.value), true);
			xmlHttpReq.send();
		}
		else {
			input.style.border = borderNo;
			document.getElementById("errorUsr").innerHTML = "Attenzione! Deve contenere almeno 6 caratteri";
			usernameOk = false;
			checkForm();
		}
	}
	
	
	function validaPassword() {
		var inputpw = document.forms['registrazione']['password'];
		var inputpwconf = document.forms['registrazione']['passwordConferma'];
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
			document.getElementById("errorPssw").innerHTML = "La password deve contenere almeno una maiuscola e un numero";
			passwordOk = false;
		}
		checkForm();
	}

	
	function validaEmail() {
		var input = document.forms['registrazione']['email'];
		if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
			input.style.border = borderOk;
			document.getElementById("errorMail").innerHTML = "";
			emailOk = true;
		} else {
			input.style.border = borderNo;
			document.getElementById("errorMail").innerHTML = "Indirizzo mail non valido";
			emailOk = false;
		}
		checkForm();
	}
	
	function validaEmailRec() {
		var input = document.forms['registrazione']['emailRec'];
		if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)) {
			input.style.border = borderOk;
			document.getElementById("errorMailRec").innerHTML = "";
			emailOk = true;
		} else {
			input.style.border = borderNo;
			document.getElementById("errorMailRec").innerHTML = "Indirizzo mail non valido";
			emailOk = false;
		}
		checkForm();
	}
	
	
	function validaTelefono() {
		var input = document.forms['registrazione']['phone'];
		if(input.value.match(/^\d{10}$/)) {
			input.style.border = borderOk;
			document.getElementById("errorPhone").innerHTML = "";
			document.getElementById("sub").disabled = "true";
			phoneOk = true;
		} else {
			input.style.border = borderNo;
			document.getElementById("errorPhone").innerHTML = "Formato sbagliato";
			document.getElementById("sub").disabled = true;
			phoneOk = false;
		}
		checkForm();
	}
	
	function checkForm() {
		if(usernameOk && passwordOk && emailOk) {
			document.getElementById("sub").disabled = false;
			document.getElementById("errorSub").innerHTML = "";
		} else {
			document.getElementById("sub").disabled = true;
			document.getElementById("errorSub").innerHTML = "Compila i campi obbligatori";
		}
		
	}
	
</script>

<%@include file="footer.html"%>	