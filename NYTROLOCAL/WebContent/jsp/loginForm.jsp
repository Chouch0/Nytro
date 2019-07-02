<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="nytro.model.AccountBean"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Login"/>	</jsp:include>	
<link href="/NYTRO/css/RegStyle.css" type="text/css" rel="stylesheet">
<%  AccountBean account = (AccountBean) session.getAttribute("account");
	if(account!=null)
		response.sendRedirect("jsp/index.jsp");
	
	String message = (String) request.getAttribute("message");
		
%>
<h1>Login</h1>
<form action="/NYTRO/Login" method="post">
	<input type="text" name="username" placeholder="Username" class="login" required><br/>
	<input type="password" name="password" placeholder="Password" class="login" required><br/>
	<input type="submit" value="Vai" class="sub">
	<p id="password" onclick="showFormRecuperoPassword()">Hai dimenticato la password?</p>
</form>

<br/>


<script>
	function showFormRecuperoPassword() {
		document.getElementById("recuperoPassword").style.display = "block";
	}
</script>


<form id="recuperoPassword" action="/NYTRO/RecuperaPassword" method="post">
	<input type="text" name="username" placeholder="Username" class="login" required> <br/>
	<input type="email" name="emailRecupero" placeholder="Email di recupero" class="login" required> <br/>
	<input type="submit" value="Inoltra" class="sub">
</form>

<%if(message!=null && !message.equals("")) {%>
	<%=message.toString()%>
<%} %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$("document").ready(function prova(){
		$("#login").addClass("selected");
	})
</script>



<%@include file="footer.html"%>							