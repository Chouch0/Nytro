<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.AccountBean, java.util.Collection, nytro.model.CasaEditriceBean"%>
<head>
	<link href="/NYTRO/css/listaGiocatori.css" type="text/css" rel="stylesheet">
</head>
<%
	Collection<AccountBean> caseEditrici = (Collection<AccountBean>) request.getAttribute("caseEditrici");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Elenco Case Editrici"/>	</jsp:include>

<link href="/NYTRO/css/friendlistStyle.css" type="text/css" rel="stylesheet">			
	<div id="lista">
	<h1>Elenco case editrici</h1>
	<div id="select">
			<form action="<%=response.encodeURL("/NYTRO/ElencoCaseEditrici")%>" method="get">
			<label>Seleziona un criterio di ordinamento
			 <select name="order">
			  <option value="">Nessuno</option>
			  <option value="azienda.Nome_Casa_Editrice">Nome casa editrice</option>
			</select> 
			</label>
			<input type="submit" value="Vai"/>
			</form>
		</div>
	<%if(caseEditrici!=null) {%>		
		<div id="tabella">
		<%
			for(AccountBean x : caseEditrici){
				CasaEditriceBean y = (CasaEditriceBean) x;
		%>
		<ul>
		<div class="amico">
			<li><div class="profilo">
			<%
			if (x.getImgProfilo() != null){%>
					<img src="/NYTRO/image?id=<%= x.getUsername()%>" alt="<%=x.getUsername()%>">
			<%} else {%>
					<img src="/NYTRO/img/default-profile.png" alt="<%=x.getUsername()%>">
			<%} %></div></li>
			<li><form action="<%=response.encodeURL("/NYTRO/CatalogoCasaEditrice?isinCasaEditrice="+y.getISIN())%>" method="post">
				<input type="submit" value="Visualizza catalogo">
			</form></li>
			<li class="username"><%=y.getNomeCasaEditrice() %></li>	
			<li>ISIN: <%=y.getISIN() %></li>		
			<li>E-mail: <%=x.getEmail() %></li>
			<li>Telefono: <%=x.getCellulare() %></li>
			<li>Sito Web: <%=y.getSitoWeb() %></li>
		</div>
		</ul>
			<%}
	} %>
	</div>

<%@include file="footer.jsp"%>	