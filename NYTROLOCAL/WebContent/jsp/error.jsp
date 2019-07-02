<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" import="java.util.Enumeration"%>
<jsp:include page="header.jsp"> <jsp:param name="pageTitle" value="Errore"/> </jsp:include>
	<section>
		<h1>Errore <%=response.getStatus() %></h1>
		
		<pre>
			<%
				if (exception != null) {
					out.flush();
					exception.getMessage();
					exception.printStackTrace(response.getWriter());
				}
			%>
		</pre>
	</section>
<%@include file="footer.html"%>