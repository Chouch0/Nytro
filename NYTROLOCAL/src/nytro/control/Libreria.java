package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;

@WebServlet("/Libreria")
public class Libreria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(account.getRuolo()!=1)
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String cancellaVideogioco=request.getParameter("cancellaVideogioco");
		if(cancellaVideogioco!=null && !cancellaVideogioco.equals("")) {
			int codiceVideogiocoDaCancellare = Integer.parseInt(cancellaVideogioco);
			try {
				videogiocoDAO.doDeleteFromLibreria(account.getUsername(), codiceVideogiocoDaCancellare);
				while(videogiocoDAO.doRetrieveFromLibreria(codiceVideogiocoDaCancellare, account.getUsername()).getUsername()!=null)
					;
				String url = response.encodeURL("/NYTRO/Libreria");
				response.sendRedirect(url);
				return ;
			} catch (SQLException e) {
				throw new MyException("Errore cancellazione videogioco.");
			}
		} 
		
		String aggiungiVideogioco=request.getParameter("aggiungiVideogioco");
		if(aggiungiVideogioco!=null && !aggiungiVideogioco.equals("")) {
			int codiceVideogiocoDaAggiungere = Integer.parseInt(aggiungiVideogioco);
			try {
				videogiocoDAO.doSaveToLibreria(account.getUsername(), codiceVideogiocoDaAggiungere);
				while(videogiocoDAO.doRetrieveFromLibreria(codiceVideogiocoDaAggiungere, account.getUsername()).getUsername()==null)
					;
				String url = response.encodeURL("/NYTRO/Libreria");
				response.sendRedirect(url);
				return ;
			} catch (SQLException e) {
				throw new MyException("Errore inserimento videogioco.");
			}
		}
		
		String order = request.getParameter("order");
		
		Collection<VideogiocoBean> libreria = null;
		
		try {
			libreria = videogiocoDAO.doRetrieveAllLibreria(account.getUsername(), order);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
	
		request.getSession().removeAttribute("libreria");
		request.getSession().setAttribute("libreria", libreria);

		String url = response.encodeURL("jsp/libreria.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
