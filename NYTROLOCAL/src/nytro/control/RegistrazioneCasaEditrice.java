package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountDAO;
import nytro.model.CasaEditriceBean;

/**
 * Servlet implementation class RegistrazioneCasaEditrice
 */
@WebServlet("/RegistrazioneCasaEditrice")
@MultipartConfig(maxFileSize = 16177215)
public class RegistrazioneCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = -5219907705325033810L;
	private final AccountDAO accountDAO = new AccountDAO();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CasaEditriceBean utente = new CasaEditriceBean();
		
		utente.setUsername(request.getParameter("username"));
		System.out.println("Questo è lo username : " + request.getParameter("username"));
		
		String phone = request.getParameter("phone");
		if(phone.isEmpty()) {
			phone = null;
		}
		
		utente.setPassword(request.getParameter("password"));
		utente.setEmail(request.getParameter("email"));
		utente.setEmailRecupero(request.getParameter("emailRec"));
		utente.setCellulare(phone);
		utente.setRuolo(2);
		utente.setCEO(request.getParameter("nomeCEO"));
		utente.setISIN(request.getParameter("ISIN"));
		utente.setImgProfilo(request.getPart("photo").getInputStream());
		utente.setNomeCasaEditrice(request.getParameter("nomeCasaEditrice"));
		utente.setSitoWeb(request.getParameter("sitoWeb"));
		 
		
		if(utente.getUsername()==null || utente.getUsername().equals("") ||
				utente.getPassword()==null || utente.getPassword().equals("") ||
				utente.getEmail()==null || utente.getEmail().equals("") ||
				utente.getEmailRecupero()==null || utente.getEmailRecupero().equals(""))
			throw new MyException("Campi vuoti");
		
		utente.setIp(request.getRemoteAddr());
		
		
		try {
			accountDAO.doSaveCasaEditrice(utente);
			if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0)
				accountDAO.doUploadImage(utente);
			String url = response.encodeURL("jsp/index.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch(SQLException exception) {
			String url = response.encodeURL("jsp/registrazioneCasaEditrice.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
