<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
import="nytro.model.VideogiocoBean, nytro.model.Cart, java.util.Collection, java.util.List"%>

<jsp:include page="header.jsp">	<jsp:param name="pageTitle" value="Carrello"/>	</jsp:include>

<%	
	Cart cart = (Cart) session.getAttribute("carrello");
	String message = (String) request.getAttribute("message");
	
%>
<%if(cart!=null) {%>
	<h2>Cart</h2>
	<%
		List<VideogiocoBean> carrelloVideogiochi = cart.getItems();
		if(carrelloVideogiochi.size()>0){
	%>
			<a href="GestoreCarrello?action=clearCart">Svuota carrello</a><br/>
			
			<form action="/NYTRO/GestoreCarrello" method="post">
				<input type="hidden" name="action" value="buy">
				<label>Inserisci carta di pagamento<input type="text" name="cartaDiPagamento" placeholder="Carta di pagamento*" required></label>
				<input type="submit" value="Effettua l'acquisto">
			</form>
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
<%} else {%>
	<h4>Carrello vuoto</h4>
<%} %>
<%@include file="footer.html"%>	