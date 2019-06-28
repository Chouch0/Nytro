<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.AccountBean, java.util.Collection"%>
<%
	Collection<AccountBean> users = (Collection<AccountBean>) request.getAttribute("users");
%>

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
	
	<h1>Lista giocatori</h1>
	
	<p>
		<%
			for(AccountBean x : users){
		%>
			<%=x.toString() %><br/>		
		<%
			}
		%>
	</p>
	
<%@include file="footer.html"%>							