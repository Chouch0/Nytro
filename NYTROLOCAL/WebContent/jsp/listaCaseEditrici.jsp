<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.AccountBean, java.util.Collection"%>
<%
	Collection<AccountBean> caseEditrici = (Collection<AccountBean>) request.getAttribute("caseEditrici");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Lista Case Editrici"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<form action="/NYTRO/ListaCaseEditrici" method="get">
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
	
	<h1>Lista case editrici</h1>
	
	<p>
		<%
			for(AccountBean x : caseEditrici){
		%>
			<%=x.toString() %><br/>		
		<%
			}
		%>
	</p>
	
<%@include file="footer.html"%>							