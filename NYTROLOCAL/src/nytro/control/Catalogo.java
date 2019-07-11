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


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		ArrayList<String> generiPresenti = new ArrayList<String>();
		
		String order = request.getParameter("order");
		String categoria = request.getParameter("categoria");
		
		Collection<VideogiocoBean> catalogo = null;
		
		try {
			if(categoria==null || categoria.equals(""))
				catalogo = videogiocoDAO.doRetrieveAll(order, "");
			else if(categoria.equalsIgnoreCase("Demo"))
				catalogo = videogiocoDAO.doRetrieveAllDemo(order, "");
			else if(categoria.equalsIgnoreCase("A pagamento"))
				catalogo = videogiocoDAO.doRetrieveAllPagamento(order, "");
			else if(categoria.equalsIgnoreCase("Free to play"))
				catalogo = videogiocoDAO.doRetrieveAllFreeToPlay(order, "");
			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		Collection<VideogiocoBean> catalogoRichiesto = new LinkedList<VideogiocoBean>();
		
		String genere = request.getParameter("genere");
		if(genere!=null && !genere.equals("")) {
			for(VideogiocoBean x : catalogo) {
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
			catalogoRichiesto = catalogo;
		}
		
		List<String> nome = new ArrayList<String>();
		
		for(VideogiocoBean x : catalogoRichiesto) {
			try {
				nome.add(videogiocoDAO.doRetrieveCasaByCodice(x.getCodice()));
				List<String> tmp = x.getGenere();
				for(String y : tmp) {
					if(!generiPresenti.contains(y.toLowerCase()))
						generiPresenti.add(y.toLowerCase());
				}
			} catch (SQLException e) {
				;
			}
		}
		
		request.setAttribute("generiPresenti", generiPresenti);
		request.setAttribute("catalogo", catalogoRichiesto);
		request.setAttribute("nomeCasa", nome);

		String url = response.encodeURL("jsp/catalogo.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
