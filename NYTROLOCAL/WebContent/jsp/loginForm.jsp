<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="nytro.model.AccountBean"%>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Login"/>	</jsp:include>	
<%  AccountBean account = (AccountBean) session.getAttribute("account");
	if(account!=null)
		response.sendRedirect("jsp/index.jsp");
	
	String message = (String) request.getAttribute("message");
		
%>
Login
<form action="/NYTRO/Login" method="post">
	<input type="text" name="username" placeholder="Username" required><br/>
	<input type="password" name="password" placeholder="Password" required><br/>
	<input type="submit" value="Vai">
</form>

<br/>


<script>
	function showFormRecuperoPassword() {
		document.getElementById("recuperoPassword").style.display = "block";
	}
</script>

<a href="#" id="haiDimenticatoLaPassword" onclick="showFormRecuperoPassword()">Ho dimenticato la password</a>
<br/>

<form id="recuperoPassword" action="/NYTRO/RecuperaPassword" method="post">
	<input type="text" name="username" placeholder="Username" required> <br/>
	<input type="email" name="emailRecupero" placeholder="Email di recupero" required> <br/>
	<input type="submit" value="Inoltra">
</form>

<%if(message!=null && !message.equals("")) {%>
	<%=message.toString()%>
<%} %>



<%@include file="footer.html"%>							