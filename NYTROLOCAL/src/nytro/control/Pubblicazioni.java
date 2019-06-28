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
import nytro.model.AccountDAO;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;

@WebServlet("/Pubblicazioni")
public class Pubblicazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(account.getRuolo()!=2)
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String isin=null;
		try {
			isin = accountDAO.doRetrieveIsinByUsername(account.getUsername());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String order = request.getParameter("order");
		String categoria = request.getParameter("categoria");
		
		Collection<VideogiocoBean> videogiochi = null;
		
		try {
			if(categoria==null || categoria.equals(""))
				videogiochi = videogiocoDAO.doRetrieveAll(order, isin);
			else if(categoria.equalsIgnoreCase("Demo"))
				videogiochi = videogiocoDAO.doRetrieveAllDemo(order, isin);
			else if(categoria.equalsIgnoreCase("A pagamento"))
				videogiochi = videogiocoDAO.doRetrieveAllPagamento(order, isin);
			else if(categoria.equalsIgnoreCase("Free to play"))
				videogiochi = videogiocoDAO.doRetrieveAllFreeToPlay(order, isin);
			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.removeAttribute("videogiochi");
		request.setAttribute("videogiochi", videogiochi);

		request.getRequestDispatcher("jsp/pubblicazioni.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
