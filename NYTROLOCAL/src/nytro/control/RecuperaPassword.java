package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.AccountDAO;

@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
	
	private static final long serialVersionUID = 9456335;
	private final AccountDAO accountDAO = new AccountDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String emailRecupero = request.getParameter("emailRecupero");
		
		AccountBean account = null;
		System.out.println(username);
		
		if(username==null || emailRecupero==null || username.equals("") || emailRecupero.equals(""))
			throw new MyException("Username e/o email di recupero vuota.");
		
		try {
			account = accountDAO.doRetrieveByUsername(username);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione account.");
		}
		
		if(account.getUsername()==null || account.getUsername().equals(""))
			throw new MyException("Account assente");

		if(!account.getEmailRecupero().equals(emailRecupero))
			throw new MyException("Email di recupero inserita non valida!");
		
		
		int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 9;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            
            if(i%2==0) {
            	char tmp = (char) randomLimitedInt;
            	buffer.append(Character.toUpperCase(tmp));
            } else {
            	buffer.append((char) randomLimitedInt);
            }
            
        }
        String generatedString = buffer.toString() + "" + random.nextInt(10);   
        
        account.setPassword(generatedString);
		
		try {
			accountDAO.doUpdate(account);
		} catch (SQLException e) {
			throw new MyException("Fallimento cambiamento password");
		}
		
		String message="Impostata password provvisoria: " + generatedString;
		
		request.setAttribute("message", message);

		request.getRequestDispatcher("jsp/loginForm.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
