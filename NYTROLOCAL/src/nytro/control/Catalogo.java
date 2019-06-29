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


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(!(account.getRuolo()==1||account.getRuolo()==0))
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		String categoria = request.getParameter("categoria");
		
		Collection<VideogiocoBean> catalogo = null;
		
		try {
			if(categoria==null || categoria.equals(""))
				catalogo = videogiocoDAO.doRetrieveAll(order, "");
			else if(categoria.equalsIgnoreCase("Demo"))
				catalogo = videogiocoDAO.doRetrieveAllDemo(order, "");
			else if(categoria.equalsIgnoreCase("A pagamento"))
				catalogo = videogiocoDAO.doRetrieveAllPagamento(order, "");
			else if(categoria.equalsIgnoreCase("Free to play"))
				catalogo = videogiocoDAO.doRetrieveAllFreeToPlay(order, "");
			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.setAttribute("catalogo", catalogo);

		request.getRequestDispatcher("jsp/catalogo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
