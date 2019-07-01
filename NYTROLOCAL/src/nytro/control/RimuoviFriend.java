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
import nytro.model.AccountDAO;

@WebServlet("/RimuoviFriend")
public class RimuoviFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(account.getRuolo()!=1)
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String eliminatoAmico = request.getParameter("eliminatoAmico");
		try {
			accountDAO.doRimuoviAmicoFriendlist(account, eliminatoAmico);
		} catch (SQLException e) {
			;
		}
		
		request.getRequestDispatcher("Friendlist").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
