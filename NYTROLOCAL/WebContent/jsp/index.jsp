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

if(videogiochiPiuAcquistati==null || videogiochiPiuGiocati==null){
	response.sendRedirect("/NYTRO/Index");
	return ;
}
List<VideogiocoPagamentoBean> list = new ArrayList<VideogiocoPagamentoBean>(videogiochiPiuAcquistati);
List<VideogiocoBean> list2 = new ArrayList<VideogiocoBean>(videogiochiPiuGiocati);

%>	
<div class="evidenza">
	<h1>In evidenza</h1>
	<div id="gioco1">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
		<section class="prezzo">Prezzo <%=list.get(0).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list.get(0).toString() %><%=list.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco2">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
		<section class="prezzo">Prezzo <%=list.get(1).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list.get(1).toString() %><%=list.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco3">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
		<section class="prezzo">Prezzo <%=list.get(2).getPrezzo() %>€</section>
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list.get(2).toString() %><%=list.get(2).getGenere().toString() %>
		</div>
	</div>
</div>
<div class="evidenza">
	<h1>I più popolari</h1>
	<div id="gioco4">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list2.get(0).toString() %><%=list2.get(0).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco5">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
		<div class="info">
			<iframe width="100%" height="40%" src="https://www.youtube.com/embed/v4AcyuWYxkA"></iframe>
			<%=list2.get(1).toString() %><%=list2.get(1).getGenere().toString() %>
		</div>
	</div>
	<div id="gioco6">
		<img src="https://www.mobygames.com/images/covers/l/214323-final-fantasy-xiv-online-windows-front-cover.jpg">
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
<%@include file="footer.html"%>							 <!-- Inclusione statica di footer.html" -->