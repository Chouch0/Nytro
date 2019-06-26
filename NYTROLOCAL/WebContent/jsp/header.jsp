<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="nytro.model.UtenteBean"%>
<%
	UtenteBean utente = (UtenteBean) request.getAttribute("utente");
	int ruolo=-1;
	if(utente!=null)
		ruolo = utente.getRuolo();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Nytro - ${param.pageTitle}</title>
		<meta charset="UTF-8">
		<meta name="description" content="NYTRO">
		<meta name="author" content="Montano Michele, Nocera Sabato, Volpicelli Veronica">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" >
		<script src="../js/ricerca.js"></script>
		<link href="../css/style.css" type="text/css" rel="stylesheet">
	</head>
<body>
	<nav>
		<div class = "menu">
			<header>
			<!-- Logo del sito + Nome del sito-->
				<a href="."><img id="logo" src="../img/logo.png" alt="logo"/>NYTRO</a>
			</header>
			<ul>
				<li>
					<form action="Ricerca" method="get">
						<input type="text" name="q" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)"> <!-- value="<c:out value="${param.q}" />" -->
						<datalist id="ricerca-datalist"></datalist>
						<!-- 
						Il tag <datalist> viene utilizzato per fornire la funzione di "autocompletamento" per elementi di input;
						mostrerà un menù a tendina con opzioni predefinite a seconda dell'input che usando.	
						In questo modo otterremo una ricerca-ajax.
						-->
					</form>
				</li>
				<li>
					<a href=".">Home</a>
				</li>
				<li>
					<a href="jsp/contatti.jsp">Contatti</a>
				</li>
				<% if(utente!=null && (ruolo==1)){ %>
					<li>
						<a href="Libreria">Libreria</a>
					</li>
					<li>
						<a href="Friendlist">Friendlist</a>
					</li>
				<% } %>
				<% if(utente!=null && (ruolo==0 || ruolo==1)){ %>
					<li><a>Esplora</a>
					<div class = "menuTendina">
					<!-- 
					Il tag <menu> definisce una lista di comandi 
					Il funzionamento è il seguente: cliccando su "Catalogo" o su "Case Editrici" verrà richiamata la servlet corrispondente, la quale 
					si preoccuperà di caricare in modo adeguato la jsp corrispondente.
					-->
						<div class = "elementoTendina">
							<a href="Catalogo">Catalogo</a>
						</div>
						<div class = "elementoTendina">
							<a href="CaseEditrici">Case Editrici</a>
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
					<%if(utente==null){%>
						Login
							<div class = "menuTendina">
								<div class = "elementoTendina">
									<div class ="schedaTendina">
										<form action="Login" method="post">
											<input type="text" name="username" placeholder="Username"><br/>
											<input type="password" name="password" placeholder="Password"><br/>
											<!-- Il ruolo dell'utente verrà ricavato in modo automatico dal database, per questo motivo non è necessario
											specificarlo ora. -->
											<input type="submit" value="Login">
										</form>
									</div>
								</div>
								<div class = "elementoTendina">
									<!-- 
									RegistrazioneForm rimanderà a RegistrazioneFormServlet, la quale rimandera alla jsp responsabile dell'input dei campi
									per effettuare la registrazione. Quest'ultima jsp rimanderà a RegistrazioneServlet che si occuperà della registrazione
									effettiva dell'utente all'interno del database.
									-->
									<a href="RegistrazioneForm">
										Registrazione
									</a>
								</div>
							</div>
					<%} else { %>
						<div class = "menuTendina">	
							<%=utente.getUsername()%>						
							<% if(ruolo==0){			//Admin%>
								<div class = "elementoTendina"><a href="ListaGiocatori">Lista giocatori</a></div>
								<div class = "elementoTendina"><a href="ListaCaseEditrici">Lista case editrici</a></div>
								<hr/>
							<% } 
								if(ruolo==1){		//Giocatore %>
								<div class = "elementoTendina"><a href="Carrello">Carrello</a></div>	<!-- Richiama la servlet responsabile di gestire il carrello -->
								<hr/>	
							<%} 
								if(ruolo==2){		//Azienda %>
								<div class = "elementoTendina"><a href="Pubblicazioni">Pubblicazioni</a></div>
								<hr>
							<%} %>
							<div class = "elementoTendina"><a href="Profilo">Profilo</a></div>	<!-- Rimanda alla servlet per la gestione del profilo dell'utente (cambia a seconda del tipo di utente) -->
							<div class = "elementoTendina">		<!-- Visualizza un pulsante attraverso cui richiamare la servlet per il Logout -->
								<div class = "schedaTendina">
									<form action="Logout">
										<input type="submit" value="Logout">
									</form>
								</div>
							</div>
						</div>
					<%} %>
				</li>	
			</ul>
		</div>
	</nav> 