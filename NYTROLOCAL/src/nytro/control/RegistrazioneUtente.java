package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountDAO;
import nytro.model.GiocatoreBean;

@WebServlet("/RegistrazioneUtente")
public class RegistrazioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiocatoreBean utente = new GiocatoreBean();
		
		utente.setUsername(request.getParameter("username"));
		System.out.println("Questo è lo username : " + request.getParameter("username"));
		
		utente.setPassword(request.getParameter("password"));
		utente.setEmail(request.getParameter("email"));
		utente.setEmailRecupero(request.getParameter("emailRec"));
		utente.setCellulare(request.getParameter("phone"));
		utente.setRuolo(1);
		utente.setGenere(request.getParameter("genere")); 
		utente.setDataNascita(request.getParameter("bornDate")); 
		
		if(utente.getUsername()==null || utente.getUsername().equals("") ||
				utente.getPassword()==null || utente.getPassword().equals("") ||
				utente.getEmail()==null || utente.getEmail().equals("") ||
				utente.getEmailRecupero()==null || utente.getEmailRecupero().equals(""))
			throw new MyException("Campi vuoti");
		
		utente.setIp(request.getRemoteAddr());
		
		try {
			accountDAO.doSaveGiocatore(utente);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/index.jsp");
			dispatcher.forward(request, response);
		} catch(SQLException exception) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/registrazioneForm.jsp");
			dispatcher.forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
