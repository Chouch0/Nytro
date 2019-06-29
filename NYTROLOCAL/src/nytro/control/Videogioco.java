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
import nytro.model.RecensioneBean;
import nytro.model.RecensioneDAO;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;


@WebServlet("/Videogioco")
public class Videogioco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final RecensioneDAO recensioneDAO = new RecensioneDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(!(account.getRuolo()==1||account.getRuolo()==0||account.getRuolo()==2))
			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String codiceVideogioco = request.getParameter("codiceVideogioco");
		System.out.println(codiceVideogioco);
		if(codiceVideogioco==null || codiceVideogioco.equals(""))
			throw new MyException("Parametro codice videogioco vuoto");
		
		VideogiocoBean videogiocoDetailed;
		
		try {
			videogiocoDetailed = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(codiceVideogioco));
		} catch (NumberFormatException e) {
			throw new MyException("Codice non valido");
		} catch (SQLException e) {
			throw new MyException("Errore SQL per videogioco");
		}
		
		request.setAttribute("videogiocoDetailed", videogiocoDetailed);
		
		
		String rimuovereRecensione = request.getParameter("rimuovereRecensione");
		if(rimuovereRecensione!=null && !rimuovereRecensione.equals("")) {
			try {
				recensioneDAO.doDelete(account.getUsername(), Integer.parseInt(codiceVideogioco));
			} catch (NumberFormatException | SQLException e) {
				throw new MyException("Impossibile cancellare recensione"); 
			}
		}
		
		String commentoRecensione = request.getParameter("commentoRecensione");
		String votoRecensione= request.getParameter("votoRecensione");
		
		if(commentoRecensione!=null && votoRecensione!=null && !commentoRecensione.equals("") && !votoRecensione.equals("")) {
			RecensioneBean recensione = new RecensioneBean();
			recensione.setCodVideogioco(Integer.parseInt(codiceVideogioco));
			recensione.setCommento(commentoRecensione);
			recensione.setVoto(Double.parseDouble(votoRecensione));
			recensione.setUsername(account.getUsername());
			try {
				recensioneDAO.doSave(recensione);
			} catch (SQLException e) {
				throw new MyException("Errore inserimento nuova recensione");
			}
		}
		
		String orderRecensioni = request.getParameter("orderRecensioni");
		
		Collection<RecensioneBean> recensioni = null;
		
		try {
			recensioni = recensioneDAO.doRetrieveAllByCodice(orderRecensioni, videogiocoDetailed.getCodice());
		} catch (SQLException e) {
			throw new MyException("Errore SQL per recensioni");
		}
		
		request.setAttribute("recensioni", recensioni);

		request.getRequestDispatcher("jsp/videogioco.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
