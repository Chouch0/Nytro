<%@page import="nytro.model.AccountBean"%>
<%@page import="nytro.model.VideogiocoPagamentoBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="nytro.model.VideogiocoBean, java.util.Collection"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Home"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->
<link href="/NYTRO/css/indexStyle.css" type="text/css" rel="stylesheet">

<%
Collection<VideogiocoPagamentoBean> videogiochiPiuAcquistati = (Collection<VideogiocoPagamentoBean>) request.getAttribute("videogiochiPiuAcquistati");
Collection<VideogiocoBean> videogiochiPiuGiocati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocati");
Collection<VideogiocoBean> videogiochiPiuVotati = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuVotati");

Collection<VideogiocoBean> videogiochiPiuGiocatiMaschi = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiMaschi");
Collection<VideogiocoBean> videogiochiPiuGiocatiFemmine = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiFemmine");
Collection<VideogiocoBean> videogiochiPiuGiocatiGenderless = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiGenderless");

Collection<VideogiocoBean> videogiochiPiuGiocatiFemmineCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiFemmineCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuGiocatiMaschiCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiMaschiCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuGiocatiGenderlessCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatiGenderlessCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuVotatiCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuVotatiCasaEditrice");
Collection<VideogiocoBean> videogiochiPiuGiocatoInGeneraleCasaEditrice = (Collection<VideogiocoBean>) request.getAttribute("videogiochiPiuGiocatoInGeneraleCasaEditrice");

String message = (String) request.getAttribute("message");
AccountBean account = (AccountBean) request.getAttribute("account");

if(videogiochiPiuAcquistati==null || videogiochiPiuGiocati==null || videogiochiPiuVotati==null){
	String url = response.encodeURL("/NYTRO/Index");
	response.sendRedirect(url);
	return ;
}
List<VideogiocoPagamentoBean> list = new ArrayList<VideogiocoPagamentoBean>(videogiochiPiuAcquistati);
List<VideogiocoBean> list2 = new ArrayList<VideogiocoBean>(videogiochiPiuGiocati);
List<VideogiocoBean> list3 = new ArrayList<VideogiocoBean>(videogiochiPiuVotati);
List<VideogiocoBean> list4 = null;
List<VideogiocoBean> list5 = null;
List<VideogiocoBean> masCasa = null;
List<VideogiocoBean> femCasa = null;
List<VideogiocoBean> genCasa = null;

if(account != null && account.getRuolo() == 2) {
	list4 = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatoInGeneraleCasaEditrice);
	list5 = new ArrayList<VideogiocoBean>(videogiochiPiuVotatiCasaEditrice);
	masCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiMaschiCasaEditrice);
	femCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiFemmineCasaEditrice);
	genCasa = new ArrayList<VideogiocoBean>(videogiochiPiuGiocatiGenderlessCasaEditrice);
}

%>	
<div id="welcome">
<%if (account == null) {%>
	<%if(message!=null && !message.equals("")) {	//Ad esempio per l'avvenuta e corretta registrazione%>
		<h1><%=message%></h1>
	<%} else {%>
	<h1>Entra a far parte della miglior community sui videogame!</h1>
	<p>Registrati o accedi per giocare ai migliori titoli del momento.</p>
	<%} %>
<%} else if(account != null && account.getRuolo() == 0) {%>
	<h1>Bentornato Amministratore!</h1>
	<p>Dai un'occhiata all'andamento della tua piattaforma.</p>
<%} else if(account != null && account.getRuolo() == 1) {%>
	<h1>Bentornato <%=account.getUsername() %>!</h1>
	<p>Dai un'occhiata ai giochi migliori selezionati per te.</p>
<%} else if(account != null && account.getRuolo() == 2) {%>
	<h1>Bentornato <%=account.getUsername() %>!</h1>
	<p>Dai un'occhiata all'andamento dei tuoi giochi sulla piattaforma.</p>
<%} %>
</div>
<div class="evidenza" id="dispari">
	<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>In evidenza</h1>
	<div id="gioco1">
		<%if (list.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(0).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(0).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(0).getTrailer()%>"></iframe>
			<%=list.get(0).toString() %><%=list.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco2">
		<%if (list.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(1).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(1).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(1).getTrailer()%>"></iframe>
			<%=list.get(1).toString() %><%=list.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco3">
		<%if (list.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(2).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(2).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list.get(2).getTrailer()%>"></iframe>
			<%=list.get(2).toString() %><%=list.get(2).getGenere().toString() %>
		</div>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
		<h1>I tuoi giochi più giocati dagli utenti</h1>
	<%if(list4 != null && !list4.isEmpty() && list4.get(0) != null) {%>
	<div id="gioco1">
		<%if (list4.get(0) != null && list4.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(0).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(0).getTrailer()%>"></iframe>
			<%=list4.get(0).toString() %><%=list4.get(0).getGenere().toString() %>
		</div>
	</div>
	<%} else if(list4 != null && !list4.isEmpty() && list4.get(1) != null) { %>
	<div id="gioco2">
		<%if (list4.get(1) != null && list4.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(1).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(1).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(1).getTrailer()%>"></iframe>
			<%=list4.get(1).toString() %><%=list4.get(1).getGenere().toString() %>
		</div>
	</div>
	<%} else if(list4 != null && !list4.isEmpty() && list4.get(2) != null) { %>
	<div id="gioco3">
		<%if (list4.get(2) != null && list4.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list4.get(2).getCodice()%>" alt="<%=list4.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list4.get(2).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list4.get(2).getTrailer()%>"></iframe>
			<%=list4.get(2).toString() %><%=list4.get(2).getGenere().toString() %>
		</div>
	</div>
	<%} else { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<div class="evidenza" id="pari">
<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>Giochi del momento</h1>
	<div id="gioco4">
		<%if (list2.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(0).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(0) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(0);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(0).getTrailer()%>"></iframe>
			<%=list2.get(0).toString() %><%=list2.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco5">
		<%if (list2.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(1).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(1) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(1);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(1).getTrailer()%>"></iframe>
			<%=list2.get(1).toString() %><%=list2.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco6">
		<%if (list2.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(2).getCodice()%>" alt="<%=list2.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(2) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(2);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list2.get(2).getTrailer()%>"></iframe>
			<%=list2.get(2).toString() %><%=list2.get(2).getGenere().toString() %>
		</div>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
			<h1>I tuoi giochi più votati dagli utenti</h1>
	<%if(list5 != null && !list5.isEmpty() && list5.get(0) != null) {%>
	<div id="gioco4">
		<%if (list5.get(0) != null && list5.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(0).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(0).getTrailer()%>"></iframe>
			<%=list5.get(0).toString() %><%=list5.get(0).getGenere().toString() %>
		</div>
	</div>
	<%} else if(list5 != null && !list5.isEmpty() && list5.get(1) != null) { %>
	<div id="gioco5">
		<%if (list5.get(1) != null && list5.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(1).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(1).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(1).getTrailer()%>"></iframe>
			<%=list5.get(1).toString() %><%=list5.get(1).getGenere().toString() %>
		</div>
	</div>
	<%} else if(list5 != null && !list5.isEmpty() && list5.get(2) != null) { %>
	<div id="gioco6">
		<%if (list5.get(2) != null && list5.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list5.get(2).getCodice()%>" alt="<%=list5.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list5.get(2).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list5.get(2).getTrailer()%>"></iframe>
			<%=list5.get(2).toString() %><%=list5.get(2).getGenere().toString() %>
		</div>
	</div>
	<%} else { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<div class="evidenza" id="dispari2">
<% if(account == null || (account!= null && account.getRuolo() == 1)|| (account!= null && account.getRuolo() == 0)) {%>
	<h1>Consigliati per te</h1>
	<div id="gioco7">
		<%if (list3.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(0).getCodice()%>" alt="<%=list3.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(0) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list3.get(0);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(0).getTrailer()%>"></iframe>
			<%=list3.get(0).toString() %><%=list3.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco8">
		<%if (list3.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(1).getCodice()%>" alt="<%=list3.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(1) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list3.get(1);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(1).getTrailer()%>"></iframe>
			<%=list3.get(1).toString() %><%=list3.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco9">
		<%if (list3.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list3.get(2).getCodice()%>" alt="<%=list3.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list3.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list3.get(2) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list3.get(2);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=list3.get(2).getTrailer()%>"></iframe>
			<%=list3.get(2).toString() %><%=list3.get(2).getGenere().toString() %>
		</div>
	</div>
	<%} else if (account != null && account.getRuolo() == 2) { %>
		<h1>Statistiche demografiche</h1>
	<%if(masCasa != null && !masCasa.isEmpty() && masCasa.get(0) != null) {%>
	<div id="gioco7">
		<%if (masCasa.get(0) != null && masCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=masCasa.get(0).getCodice()%>" alt="<%=masCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=masCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=masCasa.get(0).getTrailer()%>"></iframe>
			<%=masCasa.get(0).toString() %><%=masCasa.get(0).getGenere().toString() %>
		</div>
	</div>
	<%} else if(femCasa != null && !femCasa.isEmpty() && femCasa.get(0) != null) { %>
	<div id="gioco8">
		<%if (femCasa.get(0) != null && femCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=femCasa.get(0).getCodice()%>" alt="<%=femCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=femCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=femCasa.get(0).getTrailer()%>"></iframe>
			<%=femCasa.get(0).toString() %><%=femCasa.get(0).getGenere().toString() %>
		</div>
	</div>
	<%} else if(genCasa != null && !genCasa.isEmpty() && genCasa.get(0) != null) { %>
	<div id="gioco9">
		<%if (genCasa.get(0) != null && genCasa.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=genCasa.get(0).getCodice()%>" alt="<%=genCasa.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=genCasa.get(0).getTitolo()%>">
		<%} %>
		<div class="info">
			<iframe width="100%" height="40%" src="<%=genCasa.get(0).getTrailer()%>"></iframe>
			<%=genCasa.get(0).toString() %><%=genCasa.get(0).getGenere().toString() %>
		</div>
	</div>
	<%} else { %>
		<h1>A quanto pare non sono presenti giochi :(</h1>
	<%} %>
	<%} %>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$("document").ready(function prova(){
		$("#home").addClass("selected");
	})
</script>
<%@include file="footer.jsp"%>							 <!-- Inclusione statica di footer.html" -->