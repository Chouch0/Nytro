package nytro.control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import nytro.model.VideogiocoDemoBean;
import nytro.model.VideogiocoPagamentoBean;


@WebServlet("/Videogioco")
@MultipartConfig(maxFileSize = 16177215)
public class Videogioco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final RecensioneDAO recensioneDAO = new RecensioneDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(!(account.getRuolo()==1||account.getRuolo()==0||account.getRuolo()==2))
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
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
		
		String cambiaImmagineVideogioco = request.getParameter("cambiaImmagineVideogioco");
		if(cambiaImmagineVideogioco!=null && !cambiaImmagineVideogioco.equals("")) {
			InputStream img = request.getPart("photo").getInputStream();
			if(img!=null) {
				videogiocoDetailed.setImg(img);
				try {
					videogiocoDAO.doUpdate(videogiocoDetailed);
				} catch (SQLException e) {
					throw new MyException("Errore cambiamento immagine videogioco");
				}				
			}
		}
		
		String cambiaPrezzo = request.getParameter("cambiaPrezzo");
		if(cambiaPrezzo!=null && !cambiaPrezzo.equals("")) {
			String newPrezzo = request.getParameter("newPrezzo");
			if(newPrezzo!=null && !newPrezzo.equals("")) {
				VideogiocoPagamentoBean tmp = (VideogiocoPagamentoBean) videogiocoDetailed;
				tmp.setPrezzo(Float.parseFloat(newPrezzo));
				try {
					videogiocoDAO.doUpdatePagamento(tmp);
				} catch (SQLException e) {
					throw new MyException("Errore cambiamento prezzo videogioco");
				}
			}
		}
		
		String cambiaDurataDemo = request.getParameter("cambiaDurataDemo");
		if(cambiaDurataDemo!=null && !cambiaDurataDemo.equals("")) {
			String newDurataDemo = request.getParameter("newDurataDemo");
			if(newDurataDemo!=null && !newDurataDemo.equals("")) {
				VideogiocoDemoBean tmp = (VideogiocoDemoBean) videogiocoDetailed;
				tmp.setDurata(Integer.parseInt(newDurataDemo));
				try {
					videogiocoDAO.doUpdateDemo(tmp);
				} catch (SQLException e) {
					throw new MyException("Errore cambiamento durata demo videogioco");
				}
			}
		}
		
		String cambiaGenere = request.getParameter("cambiaGenere");
		if(cambiaGenere!=null && !cambiaGenere.equals("")) {
			String newGenere = request.getParameter("newGenere");
			if(newGenere!=null && !newGenere.equals("")) {
				try {
					videogiocoDAO.doInsertGenere(newGenere, videogiocoDetailed);
				} catch (SQLException e) {
					throw new MyException("Errore cambiamento genere videogioco");
				}
			}
		}
		
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
		if(orderRecensioni==null)
			orderRecensioni="";
		
		Collection<RecensioneBean> recensioni = null;
		
		try {
			recensioni = recensioneDAO.doRetrieveAllByCodice(orderRecensioni, videogiocoDetailed.getCodice());
		} catch (SQLException e) {
			throw new MyException("Errore SQL per recensioni");
		}
		
		boolean possibileAggiungereAllaLibreria=false;
		
		try {
			possibileAggiungereAllaLibreria = videogiocoDAO.doRetrieveAppartenenzaAllaLibreria(Integer.parseInt(codiceVideogioco), account.getUsername());
			System.out.println(possibileAggiungereAllaLibreria);
		} catch (NumberFormatException | SQLException e) {
			throw new MyException("Non si sa se si può aggiungere alla libreria");
		}
		
		if(possibileAggiungereAllaLibreria)
			request.setAttribute("possibileAggiungereAllaLibreria", "true");
		
		boolean possibileAggiungereAgliAcquisti=false;
		
		try {
			possibileAggiungereAgliAcquisti = videogiocoDAO.doRetrieveAppartenenzaAgliAcquisti(Integer.parseInt(codiceVideogioco), account.getUsername());
			System.out.println(possibileAggiungereAgliAcquisti);
		} catch (NumberFormatException | SQLException e) {
			throw new MyException("Non si sa se si può aggiungere agli acquisti");
		}
		
		if(possibileAggiungereAgliAcquisti)
			request.setAttribute("possibileAggiungereAgliAcquisti", "true");
		
		try {
			videogiocoDetailed = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(codiceVideogioco));
		} catch (NumberFormatException e) {
			throw new MyException("Codice non valido");
		} catch (SQLException e) {
			throw new MyException("Errore SQL per videogioco");
		}
		
		request.setAttribute("videogiocoDetailed", videogiocoDetailed);
		request.setAttribute("recensioni", recensioni);
		
		request.getRequestDispatcher("jsp/videogioco.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
