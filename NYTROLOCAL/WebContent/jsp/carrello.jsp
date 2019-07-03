<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, nytro.model.Cart, java.util.Collection, java.util.List"%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Carrello"/>	</jsp:include>

<%	
	Cart cart = (Cart) request.getAttribute("cart");
	String message = (String) request.getParameter("message");
	
%>

	<h2>Cart</h2>
	<%
		List<VideogiocoBean> carrelloVideogiochi = cart.getItems();
		if(carrelloVideogiochi.size()>0){
	%>
		<a href="GestoreCarrello?action=clearCart">Svuota carrello</a><br/>
		<a href="GestoreCarrello?action=buy">Effettua l'acquisto</a>
	<%
		}
	%>
	<table>
		<tr>
			<th>Titolo</th>
			<th>Action</th>
		</tr>
			<%
				if(carrelloVideogiochi.size()>0){
					for(VideogiocoBean x:carrelloVideogiochi){
			%>
		<tr>
			<td><%=x.getTitolo()%></td>
			<td><a href="GestoreCarrello?action=deleteCart&codiceVideogioco=<%=x.getCodice()%>">Rimuovi dal carrello</a></td>
		</tr>	
			<%
					}
				} else {
			%>
		<tr>
			<td colspan="2"> Nessun prodotto disponibile nel carrello </td>
		</tr>		
			<%
				}
			%>
	</table>

	<%
		if(message != null && !message.equals("")) {
	%>
		
		<p><%=message.toString()%></p>
	
	<%
		}
	%>

<%@include file="footer.html"%>	