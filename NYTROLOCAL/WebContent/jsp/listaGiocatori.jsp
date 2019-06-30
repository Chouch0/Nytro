<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.AccountBean, java.util.Collection"%>
<%
	Collection<AccountBean> users = (Collection<AccountBean>) request.getAttribute("users");
%>
<html>
<head>
	<link href="/NYTRO/css/listaGiocatori.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Lista Giocatori"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="/NYTRO/ListaGiocatori" method="get">
	<label>Seleziona un criterio di ordinamento
	 <select name="order">
	  <option value="">Nessuno</option>
	  <option value="Username">Username</option>
	  <option value="Data">Data ultimo accesso</option>
	  <option value="IP">Indirizzo IP</option>
	</select> 
	</label>
	<input type="submit" value="Vai"/>
	</form>
	
	<table align="center">
		<caption>Lista giocatori</caption>
		<tr>
			<th>Username</th>
			<th>Password</th>
			<th>E-mail</th>
			<th>E-mail secondaria</th>
			<th>Cellulare</th>
			<th colspan="3">Ultimo accesso</th>
		</tr>
		<%
			for(AccountBean x : users){
		%>
		<tr>
			<td><%=x.getUsername() %></td>
			<td><%=x.getPassword() %></td>
			<td><%=x.getEmail() %></td>
			<td><%=x.getEmailRecupero() %></td>
			<td><%=x.getCellulare() %></td>
			<td><%=x.getData() %></td>
			<td><%=x.getOra() %></td>
			<td><%=x.getIp() %></td>
		</tr>
		<%
			}
		%>
	</table>
	
<%@include file="footer.html"%>		
</body>
</html>					