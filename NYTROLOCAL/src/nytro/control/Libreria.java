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

@WebServlet("/Libreria")
public class Libreria extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(account.getRuolo()!=1)
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		
		Collection<VideogiocoBean> libreria = null;
		
		try {
			libreria = videogiocoDAO.doRetrieveAllLibreria(account.getUsername(), order);			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.setAttribute("libreria", libreria);

		request.getRequestDispatcher("jsp/libreria.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
