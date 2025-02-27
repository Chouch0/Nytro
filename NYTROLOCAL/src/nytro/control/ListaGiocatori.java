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

@WebServlet("/ListaGiocatori")
public class ListaGiocatori extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean admin = (AccountBean) request.getSession().getAttribute("account");
//		if(admin.getRuolo()!=0)
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		
		Collection<AccountBean> users = null;
		
		try {
			users = accountDAO.doRetrieveAll(order, 1);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione utenti.");
		}
		
		request.setAttribute("users", users);

		String url = response.encodeURL("jsp/listaGiocatori.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
