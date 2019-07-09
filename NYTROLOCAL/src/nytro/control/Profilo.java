package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.AccountDAO;
import nytro.model.CasaEditriceBean;

@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		AccountBean detailedAccount=null;
		try {
			detailedAccount = accountDAO.doRetrieveDetailed(account);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione detailedAccount partendo dall'account: "+account);
		}
		
		request.getSession().removeAttribute("account");
		
		if(detailedAccount.getUsername()!=null)
			request.getSession().setAttribute("account", detailedAccount);
		else
			request.getSession().setAttribute("account", account);
		
		
		ArrayList<String> contributo = null;
		String contributoAnnuale = request.getParameter("contributoAnnuale");
		if(contributoAnnuale!=null && !contributoAnnuale.equals("")) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(endDate!=null && startDate!=null && !startDate.equals("") && !endDate.equals("")) {
				try {
					contributo=accountDAO.doRetrieveContributoAdmin(startDate, endDate);
				} catch (SQLException e) {
					;
				}
			}
			request.setAttribute("contributo", contributo);
		}
		
		String contributoAnnualeCasaEditrice = request.getParameter("contributoAnnualeCasaEditrice");
		if(contributoAnnualeCasaEditrice!=null && !contributoAnnualeCasaEditrice.equals("")) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(endDate!=null && startDate!=null && !startDate.equals("") && !endDate.equals("")) {
				try {					
					contributo=accountDAO.doRetrieveContributoCasaEditrice(accountDAO.doRetrieveIsinByUsername(account.getUsername()), startDate, endDate);
				} catch (SQLException e) {
					;
				}
			}
			request.setAttribute("contributo", contributo);
		}
		
		String url = response.encodeURL("jsp/profilo.jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
