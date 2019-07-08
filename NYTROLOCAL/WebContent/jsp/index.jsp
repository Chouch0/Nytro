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
String message = (String) request.getAttribute("message");

if(videogiochiPiuAcquistati==null || videogiochiPiuGiocati==null){
	String url = response.encodeURL("/NYTRO/Index");
	response.sendRedirect(url);
	return ;
}
List<VideogiocoPagamentoBean> list = new ArrayList<VideogiocoPagamentoBean>(videogiochiPiuAcquistati);
List<VideogiocoBean> list2 = new ArrayList<VideogiocoBean>(videogiochiPiuGiocati);

%>	
<div class="evidenza">
	<%if(message!=null && !message.equals("")) {	//Ad esempio per l'avvenuta e corretta registrazione%>
		<h1><%=message%></h1>
	<%} %>
	<h1>In evidenza</h1>
	<div id="gioco1">
		<%if (list.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list.get(0).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%=list.get(0).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
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
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
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
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list.get(2).toString() %><%=list.get(2).getGenere().toString() %>
		</div>
	</div>
</div>
<div class="evidenza">
	<h1>Giochi del momento</h1>
	<div id="gioco4">
		<%if (list2.get(0).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(0).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(0).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(0) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(0);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list2.get(0).toString() %><%=list2.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco5">
		<%if (list2.get(1).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(1).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(1).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(1) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(1);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list2.get(1).toString() %><%=list2.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco6">
		<%if (list2.get(2).getImg() != null){%>
			<img src="/NYTRO/image?codice=<%=list2.get(2).getCodice()%>" alt="<%=list.get(0).getTitolo()%>">
		<%} else {%>
			<img src="/NYTRO/img/no-cover.jpg" alt="<%=list2.get(2).getTitolo()%>">
		<%} %>
		<section class="prezzo"><%if (list2.get(2) instanceof VideogiocoPagamentoBean){
			VideogiocoPagamentoBean x = (VideogiocoPagamentoBean)list2.get(2);%><%=x.getPrezzo() %>€
			<%} else { %>FREE <%} %></section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list2.get(2).toString() %><%=list2.get(2).getGenere().toString() %>
		</div>
	</div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$("document").ready(function prova(){
		$("#home").addClass("selected");
	})
</script>
<%@include file="footer.jsp"%>							 <!-- Inclusione statica di footer.html" -->