package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountBean;
import nytro.model.AccountDAO;

/**
 * Servlet implementation class RegistrazioneUtente
 */
@WebServlet("/RegistrazioneUtente")
public class RegistrazioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean utente = new AccountBean();
		
		utente.setUsername(request.getParameter("username"));
		System.out.println("Questo è lo username : " + request.getParameter("username"));
		utente.setPassword(request.getParameter("password"));
		utente.setEmail(request.getParameter("email"));
		utente.setEmailRecupero(request.getParameter("emailRec"));
		utente.setCellulare(request.getParameter("phone"));
		utente.setRuolo(1);
		LocalDate ld = LocalDate.now();
		java.sql.Date date = java.sql.Date.valueOf(ld);
		LocalTime lt = LocalTime.now();
		Time time = Time.valueOf(lt);
		utente.setData(date.toString());
		utente.setOra(time.toString());
		utente.setIp("192.168.48.55");
		try {
			accountDAO.doSave(utente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/NYTRO/jsp/index.jsp");
			dispatcher.forward(request, response);
		} catch(SQLException exception) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/NYTRO/jsp/RegistrazioneForm.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
