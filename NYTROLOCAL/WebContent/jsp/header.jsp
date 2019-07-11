<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="nytro.model.AccountBean"%>
<%
	AccountBean account = (AccountBean) session.getAttribute("account");
	int ruolo=-1;
	if(account!=null)
		ruolo = account.getRuolo();
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Nytro - ${param.pageTitle}</title>
		<meta charset="UTF-8">
		<meta name="description" content="NYTRO">
		<meta name="author" content="Montano Michele, Nocera Sabato, Volpicelli Veronica">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
		<script src="/NYTRO/js/ricerca.js"></script>
		<link href="/NYTRO/css/style.css" type="text/css" rel="stylesheet">
		<link rel="icon" href="/NYTRO/img/logo.png">
	</head>
<body>
	<header>
	<!-- Logo del sito + Nome del sito-->
		<a href="<%=response.encodeURL("/NYTRO/jsp/index.jsp")%>"><img id="logoHeader" src="/NYTRO/img/logo.png" alt="Logo"/><img id="nytro" src="/NYTRO/img/Nytro.png" alt="Nytro"></a>
		<div id="opzioni">
			<%if(account==null){ 
				String url = response.encodeURL("/NYTRO/jsp/loginForm.jsp");
				String url2 = response.encodeURL("/NYTRO/RegistrazioneForm");
			%>					
				<a href="<%=url%>">Login</a> | 
				<a href="<%=url2%>">Registrazione</a>
			<%}else { 
				String url = response.encodeURL("/NYTRO/Profilo");
				String url2 = response.encodeURL("/NYTRO/Logout");
			%>
				<a href="<%=url%>"><%=account.getUsername()%></a> |
				<a href="<%=url2%>">Logout</a>			
			<%} %>
		</div>
	</header>
	<nav>
		<ul>
			<li>
				<a href="<%=response.encodeURL("/NYTRO/jsp/index.jsp")%>" id="home">Home</a>
			</li>
			<li>
				<a href="<%=response.encodeURL("/NYTRO/jsp/contatti.jsp")%>" id="contatti">Contatti</a>
			</li>
			<% if(account!=null && (ruolo==1)){ %>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/Libreria")%>" id="libreria">Libreria</a>
				</li>
				<li>
					<a href="<%=response.encodeURL("/NYTRO/Friendlist")%>" id="friendlist">Friendlist</a>
				</li>
			<% } %>
			<% if(account!=null && (ruolo==0 || ruolo==1 || ruolo == 2)){ %>
				<li>
				<div id = "menuEsplora">
					<a href="#" id="esplora">Esplora</a>
				<!-- 
				Il tag <menu> definisce una lista di comandi  
				Il funzionamento è il seguente: cliccando su "Catalogo" o su "Case Editrici" verrà richiamata la servlet corrispondente, la quale 
				si preoccuperà di caricare in modo adeguato la jsp corrispondente.
				-->
					<div class = "elementoEsplora">
						<a href="<%=response.encodeURL("/NYTRO/Catalogo")%>">Catalogo</a>
						<a href="<%=response.encodeURL("/NYTRO/ElencoCaseEditrici")%>">Elenco Case Editrici</a>
					</div>
				</div>
				</li>		
			<% } %>
			<li>
			<!-- 
			Gesione del login. All'inizio abbiamo ricavato le informazioni circa l'utente che sta visitando la pagina. 
			Se l'utente non ha effettuato il login, gli verrà mostrato un format a tale scopo, oppure un link che rimanda ad un format di registrazione.
			Se l'utente è registrato, a seconda del proprio ruolo, visualizzerà informazioni diverse.
			-->
				<%if(account==null){%>					
					<div id = "menuLogin">
						<a href="<%=response.encodeURL("/NYTRO/jsp/loginForm.jsp")%>" id="login">Login</a>
							<div class = "elementoLogin"> 
								<form action="<%=response.encodeURL("/NYTRO/Login")%>" method="post">
									<input type="text" name="username" placeholder="Username" required><br/>
									<input type="password" name="password" placeholder="Password" required><br/>
									<!-- Il ruolo dell'utente verrà ricavato in modo automatico dal database, per questo motivo non è necessario
									specificarlo ora. -->
									<input type="submit" value="Login">
								</form>
								<!-- 
								RegistrazioneForm rimanderà a RegistrazioneFormServlet, la quale rimandera alla jsp responsabile dell'input dei campi
								per effettuare la registrazione. Quest'ultima jsp rimanderà a RegistrazioneServlet che si occuperà della registrazione
								effettiva dell'utente all'interno del database.
								-->
								<a href="<%=response.encodeURL("/NYTRO/RegistrazioneForm")%>">
									Registrazione
								</a>
							</div>
						</div>
				<%} else { %>
					<div id = "menuUser">	
						<a href="#" id="user"><%=account.getUsername()%></a>
						<div class = "elementoUser">				
						<% if(ruolo==0){			//Admin%>
								<a href="<%=response.encodeURL("/NYTRO/ListaGiocatori")%>">Lista giocatori</a>
								<a href="<%=response.encodeURL("/NYTRO/ListaCaseEditrici")%>">Lista case editrici</a>
								<a href="<%=response.encodeURL("/NYTRO/jsp/registrazioneCasaEditrice.jsp")%>">Registra Casa Editrice</a>
								<a href="<%=response.encodeURL("/NYTRO/jsp/rimozioneAccount.jsp")%>">Rimuovi account</a>
							<hr/>
						<% } 
							if(ruolo==1){		//Giocatore %>
								<a href="<%=response.encodeURL("/NYTRO/jsp/carrello.jsp")%>">Carrello</a> 
							<hr/>	
						<%} 
							if(ruolo==2){		//Azienda %>
								<a href="<%=response.encodeURL("/NYTRO/Pubblicazioni")%>">Pubblicazioni</a>
							<hr>
						<%} %>
						<a href="<%=response.encodeURL("/NYTRO/Profilo")%>">Profilo</a>	<!-- Rimanda alla servlet per la gestione del profilo dell'utente (cambia a seconda del tipo di utente) -->
								<!-- Visualizza un pulsante attraverso cui richiamare la servlet per il Logout
							<form action="/NYTRO/Logout">
								<input type="submit" value="Logout">
							</form> Non è necessario un form -->
						<a href="<%=response.encodeURL("/NYTRO/Logout")%>">Logout</a>
					</div>
				<%} %>
			</li>
			<%if(ruolo>=0){%>
			<li>
				<form action="<%=response.encodeURL("Ricerca")%>" method="get" id="ricerca">
					<input type="text" name="testoParziale" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)"> <!-- value="<c:out value="${param.q}" />" -->
					<datalist id="ricerca-datalist"></datalist>
					<!-- 
					Il tag <datalist> viene utilizzato per fornire la funzione di "autocompletamento" per elementi di input;
					mostrerà un menù a tendina con opzioni predefinite a seconda dell'input che usando.	
					In questo modo otterremo una ricerca-ajax.
					-->
				</form>
			</li>
			<%} else {%>
			<li></li>
			<%} %>
		</ul>
	</nav>