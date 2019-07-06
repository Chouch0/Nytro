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

@WebServlet("/ListaCaseEditrici")
public class ListaCaseEditrici extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(!(account.getRuolo()==0||account.getRuolo()==1))
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		
		Collection<AccountBean> caseEditrici = null;
		
		try {
			caseEditrici = accountDAO.doRetrieveAll(order, 2);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione utenti.");
		}
		
		request.setAttribute("caseEditrici", caseEditrici);

		request.getRequestDispatcher("jsp/listaCaseEditrici.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
