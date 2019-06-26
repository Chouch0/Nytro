package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountDAO;
import nytro.exceptions.CustomException;
import nytro.model.AccountBean;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO utenteDAO = new AccountDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountBean account = null;
		
		if (username != null && password != null) {
			try {
				account = utenteDAO.doRetrieveByUsernamePassword(username, password);
			} catch (SQLException e) {
				throw new CustomException("Username e/o password vuoti.");
			}
		}

		if (account == null) {
			throw new CustomException("Username e/o password non validi.");
		}
		
		request.getSession().setAttribute("account", account);

		String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/Login") || dest.trim().isEmpty()) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
