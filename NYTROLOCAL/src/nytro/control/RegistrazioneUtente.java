package nytro.control;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import nytro.exceptions.MyException;
import nytro.model.AccountDAO;
import nytro.model.GiocatoreBean;

@WebServlet("/RegistrazioneUtente")
@MultipartConfig(maxFileSize = 16177215)
public class RegistrazioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final AccountDAO accountDAO = new AccountDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiocatoreBean utente = new GiocatoreBean();
		
		utente.setUsername(request.getParameter("username"));
		System.out.println("Questo è lo username : " + request.getParameter("username"));
		String phone = request.getParameter("phone");
		String date = request.getParameter("bornDate");
		String genere = request.getParameter("genere");
		if(phone.isEmpty()) {
			phone = null;
		}
		
		if(date.isEmpty()) {
			date = null;
		}
		
		if(genere == null || genere.equals("")) {
			genere = null;
		}
		
		utente.setPassword(request.getParameter("password"));
		utente.setEmail(request.getParameter("email"));
		utente.setEmailRecupero(request.getParameter("emailRec"));
		utente.setCellulare(phone);
		utente.setRuolo(1);
		utente.setGenere(genere);
		utente.setDataNascita(date); 
		
		
		
		if(utente.getUsername()==null || utente.getUsername().equals("") ||
				utente.getPassword()==null || utente.getPassword().equals("") ||
				utente.getEmail()==null || utente.getEmail().equals("") ||
				utente.getEmailRecupero()==null || utente.getEmailRecupero().equals(""))
			throw new MyException("Campi vuoti");
		
		utente.setIp(request.getRemoteAddr());
		utente.setImgProfilo(request.getPart("photo").getInputStream());
		
		try {
			accountDAO.doSaveGiocatore(utente);
			accountDAO.doUploadImage(utente);
			String message = "Registrazione effettuata con successo";
			request.setAttribute("message", message);
			String url = response.encodeURL("Index");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch(SQLException exception) {
			String url = response.encodeURL("jsp/registrazioneForm.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
