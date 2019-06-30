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
	</head>
<body>
	<nav>
		<div class = "menu">
			<header>
			<!-- Logo del sito + Nome del sito-->
				<a href="."><img id="logo" src="/NYTRO/img/logo.png" alt="logo"/>NYTRO</a>
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
					<a href="contatti.jsp">Contatti</a>
				</li>
				<% if(account!=null && (ruolo==1)){ %>
					<li>
						<a href="/NYTRO/Libreria">Libreria</a>
					</li>
					<li>
						<a href="/NYTRO/Friendlist">Friendlist</a>
					</li>
				<% } %>
				<% if(account!=null && (ruolo==0 || ruolo==1)){ %>
					<li>Esplora
					<div class = "menuTendina">
					<!-- 
					Il tag <menu> definisce una lista di comandi  
					Il funzionamento è il seguente: cliccando su "Catalogo" o su "Case Editrici" verrà richiamata la servlet corrispondente, la quale 
					si preoccuperà di caricare in modo adeguato la jsp corrispondente.
					-->
						<div class = "elementoTendina">
							<a href="/NYTRO/Catalogo">Catalogo</a>
						</div>
						<div class = "elementoTendina">
							<a href="/NYTRO/CaseEditrici">Case Editrici</a>
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
						<div class = "menuTendina">
							<a href="#">Login</a>
								<div class = "elementoTendina">
									<form action="/NYTRO/Login" method="post">
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
									<a href="/NYTRO/RegistrazioneForm">
										Registrazione
									</a>
								</div>
							</div>
					<%} else { %>
						<div class = "menuTendina">	
							<b><%=account.getUsername()%></b>				
							<% if(ruolo==0){			//Admin%>
								<div class = "elementoTendina"><a href="/NYTRO/ListaGiocatori">Lista giocatori</a></div>
								<div class = "elementoTendina"><a href="/NYTRO/ListaCaseEditrici">Lista case editrici</a></div>
								<hr/>
							<% } 
								if(ruolo==1){		//Giocatore %>
								<div class = "elementoTendina"><a href="/NYTRO/Carrello">Carrello</a></div>	<!-- Richiama la servlet responsabile di gestire il carrello -->
								<hr/>	
							<%} 
								if(ruolo==2){		//Azienda %>
								<div class = "elementoTendina"><a href="/NYTRO/Pubblicazioni">Pubblicazioni</a></div>
								<hr>
							<%} %>
							<div class = "elementoTendina"><a href="Profilo">Profilo</a></div>	<!-- Rimanda alla servlet per la gestione del profilo dell'utente (cambia a seconda del tipo di utente) -->
							<div class = "elementoTendina">		<!-- Visualizza un pulsante attraverso cui richiamare la servlet per il Logout -->
								<div class = "schedaTendina">
									<form action="/NYTRO/Logout">
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
</body>
</html>