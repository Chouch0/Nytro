package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.AccountDAO;
import nytro.model.AmministratoreBean;
import nytro.model.CasaEditriceBean;
import nytro.model.GiocatoreBean;

@WebServlet("/AggiornaProfilo")
public class AggiornaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountDAO accountDAO = new AccountDAO();       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(account==null)
			throw new MyException("Account non loggato");
		
		String cambiaPassword = request.getParameter("cambiaPassword");
		if(!(cambiaPassword==null || cambiaPassword.equals(""))) {
			String cambiaPasswordConferma = request.getParameter("cambiaPasswordConferma");
			if(!cambiaPasswordConferma.equals(cambiaPassword))
				throw new MyException("Password missmatch: "+cambiaPasswordConferma+" "+cambiaPassword);
			account.setPassword(cambiaPassword);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento password");
			}
		}
		
		String cambiaEmail = request.getParameter("cambiaEmail");
		if(cambiaEmail!=null && !cambiaEmail.equals("")) {
			account.setEmail(cambiaEmail);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento email");
			}
		}
		
		String cambiaEmailRecupero = request.getParameter("cambiaEmailRecupero");
		if(cambiaEmailRecupero!=null && !cambiaEmailRecupero.equals("")) {
			account.setEmailRecupero(cambiaEmailRecupero);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento email recupero");
			}
		}
		
		String phone = request.getParameter("phone");
		if(phone!=null && !phone.equals("")) {
			account.setCellulare(phone);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento telefono");
			}
		}
		
		String cambiaNome= request.getParameter("cambiaNome");
		if(cambiaNome!=null && !cambiaNome.equals("")) {
			AmministratoreBean tmp = (AmministratoreBean) account;
			tmp.setNome(cambiaNome);
			try {
				accountDAO.doUpdateAmministratore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento nome amministratore");
			}
		}
		
		String cambiaCognome= request.getParameter("cambiaCognome");
		if(cambiaCognome!=null && !cambiaCognome.equals("")) {
			AmministratoreBean tmp = (AmministratoreBean) account;
			tmp.setCognome(cambiaCognome);
			try {
				accountDAO.doUpdateAmministratore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento cognome amministratore");
			}
		}
		
		String cambiaDataNascita = request.getParameter("cambiaDataNascita");
		if(cambiaDataNascita!=null && !cambiaDataNascita.equals("")) {
			String anno  = request.getParameter("anno");
			String mese  = request.getParameter("mese");
			String giorno  = request.getParameter("giorno");
			String data = anno+"-"+mese+"-"+giorno;
			GiocatoreBean tmp = (GiocatoreBean) account;
			tmp.setDataNascita(data);
			try {
				accountDAO.doUpdateGiocatore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento data di nascita");
			}
		}
		
		String cambiaGenere = request.getParameter("cambiaGenere");
		if(cambiaGenere!=null && !cambiaGenere.equals("")) {
			GiocatoreBean tmp = (GiocatoreBean) account;
			tmp.setGenere(cambiaGenere);
			System.out.println(tmp.getGenere()+ "  " + cambiaGenere);
			try {
				accountDAO.doUpdateGiocatore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento genere");
			}
		}
		
		String cambiaNomeCasaEditrice = request.getParameter("cambiaNomeCasaEditrice");
		if(cambiaNomeCasaEditrice!=null && !cambiaNomeCasaEditrice.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setNomeCasaEditrice(cambiaNomeCasaEditrice);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento nome casa editrice");
			}
		}
		
		String cambiaCEO = request.getParameter("cambiaCEO");
		if(cambiaCEO!=null && !cambiaCEO.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setCEO(cambiaCEO);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento CEO");
			}
		}
		
		String cambiaSitoWEB = request.getParameter("cambiaSitoWEB");
		if(cambiaSitoWEB!=null && !cambiaSitoWEB.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setSitoWeb(cambiaSitoWEB);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento sito web");
			}
		}
		
		request.getRequestDispatcher("jsp/profilo.jsp").forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
