<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, java.util.Collection"%>
<%
	VideogiocoBean videogiocoDetailed= (VideogiocoBean) request.getAttribute("videogiocoDetailed");
	
%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Videogioco"/>	</jsp:include>	<!-- Inclusione dinamica di header.jsp" -->	
	
	<h1><%=videogiocoDetailed.getTitolo() %></h1>
	
	<p>
		<%=videogiocoDetailed.toString()%>
	</p>
	
<%@include file="footer.html"%>							