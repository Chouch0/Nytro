package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.Cart;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;
import nytro.model.VideogiocoPagamentoBean;

@WebServlet("/GestoreCarrello")
public class GestoreCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static VideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(account.getRuolo()!=1)
//			throw new MyException("Non sei un giocatore, quindi non puoi gestire il tuo carrello.");
		
		Cart cart = (Cart) request.getSession().getAttribute("carrello");
		
		if(cart==null) {
			cart = new Cart();
			request.getSession().setAttribute("carrello", cart);
		}
		
		String action = request.getParameter("action");
		String message = null;
		
		try {
			if(action!=null && !action.equals("")) {
				if(action.equalsIgnoreCase("addCart")) {
					String codiceVideogioco = request.getParameter("codiceVideogioco");
					if(codiceVideogioco==null || codiceVideogioco.equals(""))
						throw new MyException("Codice videogioco assente");
					VideogiocoBean tmp = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(codiceVideogioco));
					VideogiocoPagamentoBean videogioco = (VideogiocoPagamentoBean) tmp;
					if(videogioco!=null && !cart.contains(videogioco)){
						cart.addItem(videogioco);
						message="Videogioco inserito correttamente nel carrello";
					} else {
						message="Videogioco NON inserito correttamente nel carrello";
					}
				} else if(action.equalsIgnoreCase("deleteCart")) {
					String codiceVideogioco = request.getParameter("codiceVideogioco");
					if(codiceVideogioco==null || codiceVideogioco.equals(""))
						throw new MyException("Codice videogioco assente");
					VideogiocoBean tmp = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(codiceVideogioco));
					VideogiocoPagamentoBean videogioco = (VideogiocoPagamentoBean) tmp;
					if(videogioco!=null) {
						cart.deleteItem(videogioco);
						message="Videogioco rimosso correttamente nel carrello";
					} else {
						message="Videogioco NON rimosso correttamente nel carrello";
					}						
				} else if(action.equalsIgnoreCase("clearCart")) {
					cart.deleteAll();
					message="Carrello svuotato con successo";
				} else if (action.equalsIgnoreCase("buy")){
					String cartaDiPagamento = request.getParameter("cartaDiPagamento");
					if(cartaDiPagamento==null || cartaDiPagamento.equals(""))
						throw new MyException("Carta di pagamento assente");
					
					videogiocoDAO.doAcquisto(cart.getItems(), account, cartaDiPagamento);
				
					cart.deleteAll();
					message="Acquistato effettuato con successo";
				}
			} 
			
		} catch(SQLException | NumberFormatException e) {
			throw new MyException("Errore modifica carrello");
		}
		
		request.setAttribute("message", message);
		request.getSession().setAttribute("carrello", cart);	
		
		request.getRequestDispatcher("jsp/carrello.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
