package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;

@WebServlet("/CatalogoCasaEditrice")
public class CatalogoCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(!(account.getRuolo()==1||account.getRuolo()==0))
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String order = request.getParameter("order");
		String isinCasaEditrice = request.getParameter("isinCasaEditrice");
		
		ArrayList<String> generiPresenti = new ArrayList<String>();
		
		Collection<VideogiocoBean> catalogoCasaEditrice = null;
		
		try {
			catalogoCasaEditrice = videogiocoDAO.doRetrieveAll(order, isinCasaEditrice);			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		Collection<VideogiocoBean> catalogoRichiesto = new LinkedList<VideogiocoBean>();
		
		String genere = request.getParameter("genere");
		if(genere!=null && !genere.equals("")) {
			for(VideogiocoBean x : catalogoCasaEditrice) {
				try {
					List<String> tmp = x.getGenere();
					for(String y : tmp)
						if(y.toLowerCase().equalsIgnoreCase(genere))
							catalogoRichiesto.add(x);
				} catch (SQLException e) {
					;
				}
			}
		} else {
			catalogoRichiesto = catalogoCasaEditrice;
		}
		
		String nome = isinCasaEditrice;
		
		for(VideogiocoBean x : catalogoRichiesto) {
			try {
				List<String> tmp = x.getGenere();
				nome = videogiocoDAO.doRetrieveCasaByCodice(x.getCodice());
				for(String y : tmp) {
					if(!generiPresenti.contains(y.toLowerCase()))
						generiPresenti.add(y.toLowerCase());
				}
			} catch (SQLException e) {
				;
			}
		}
		
		
		request.setAttribute("generiPresenti", generiPresenti);
		
		request.setAttribute("catalogoCasaEditrice", catalogoRichiesto);
		request.removeAttribute("isinCasaEditrice");
		request.setAttribute("isinCasaEditrice", nome);

		String url = response.encodeURL("jsp/catalogoCasaEditrice.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
