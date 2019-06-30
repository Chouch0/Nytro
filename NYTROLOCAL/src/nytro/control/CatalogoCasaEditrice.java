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

@WebServlet("/CatalogoCasaEditrice")
public class CatalogoCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(!(account.getRuolo()==1||account.getRuolo()==0))
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		String isinCasaEditrice = request.getParameter("isinCasaEditrice");
		
		Collection<VideogiocoBean> catalogoCasaEditrice = null;
		
		try {
			catalogoCasaEditrice = videogiocoDAO.doRetrieveAll(order, isinCasaEditrice);
			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.setAttribute("catalogoCasaEditrice", catalogoCasaEditrice);
		request.setAttribute("isinCasaEditrice", isinCasaEditrice);

		request.getRequestDispatcher("jsp/catalogoCasaEditrice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
