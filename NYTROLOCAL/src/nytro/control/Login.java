package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountDAO;
import nytro.exceptions.MyException;
import nytro.model.AccountBean;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 8573498926977710169L;
	private final AccountDAO accountDAO = new AccountDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		AccountBean account = null;
		System.out.println(username + " " + password);
		if (username != null && password != null) {
			try {
				account = accountDAO.doRetrieveByUsernamePassword(username, password);
			} catch (SQLException e) {
				throw new MyException("Username e/o password vuoti.");
			}
		}

		if (account.getUsername()==null) {						//Controlla che il campo username sia null per indicare che l'utente � assente
			throw new MyException("Username e/o password non validi.");
		}

		try {
			account.setIp(request.getRemoteAddr());
			accountDAO.doUpdate(account);
		} catch (SQLException e) {
			throw new MyException("Fallimento aggiornamento informazioni ultimo accesso");
		}		
		
		try {
			account = accountDAO.doRetrieveByUsernamePassword(username, password);	//per avere le informazioni circa data e ora aggiornate
		} catch (SQLException e) {
			throw new MyException("Username e/o password vuoti.");
		}
		
		request.getSession().setAttribute("account", account);
	
		response.sendRedirect("jsp/index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
