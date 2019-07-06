package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountBean;
import nytro.model.AccountDAO;

/**
 * Servlet implementation class RimozioneAccount
 */
@WebServlet("/RimozioneAccount")
public class RimozioneAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final AccountDAO accountDAO = new AccountDAO();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		try {
			AccountBean account = accountDAO.doRetrieveByUsername(username);
			accountDAO.doDelete(account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/index.jsp");
			dispatcher.forward(request, response);
		} catch(SQLException exception) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/NYTRO/jsp/rimozioneAccount.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
