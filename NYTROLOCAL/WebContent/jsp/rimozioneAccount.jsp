<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"><jsp:param name="pageTitle" value="Rimozione account"/></jsp:include>
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">

<form class="box" name="rimozione" action="<%=response.encodeURL("/NYTRO/RimozioneAccount")%>" method="post">
	<input type="text" class="registrazione" name="username" required oninput="checkUsername()">
	<p id="errorUsr"></p>
	<input type="submit" value="Rimuovi Account" id="sub" disabled>
</form>

	<script>
	
		var borderOk = '2px solid green';
		var borderNo = '2px solid red';
		var usernameOk = false;
	
		function checkUsername() {
			var ok = "<ok/>";
			var input = document.forms['rimozione']['username'];
			if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
				// verifica se esiste un utente con lo stesso username
			
				var xmlHttpReq = new XMLHttpRequest();
				xmlHttpReq.onreadystatechange = function() {
					if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == ok) {
						input.style.border = borderNo;
						document.getElementById("errorUsr").innerHTML = "Questo Account Non Esiste!";
						usernameOk = false;
					} else if(xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != ok){
						input.style.border = borderOk;
						document.getElementById("errorUsr").innerHTML = "Account Trovato!";
						usernameOk = true;
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
			
			function checkForm() {
				if(usernameOk) {
					document.getElementById("sub").disabled = false; 
				} else {
					document.getElementById("sub").disabled = true;
				}
			}

		}
	
	
	</script>
	
<%@include file="footer.jsp"%>	