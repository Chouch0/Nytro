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
import nytro.model.AccountDAO;
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
	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
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
			if(request.getPart("photo") != null  && request.getPart("photo").getSize() > 0) {
				videogiocoDetailed.setImg(request.getPart("photo").getInputStream());
				try {
					videogiocoDAO.doUploadImageByCodice(videogiocoDetailed);
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
		
		String cambiaTrailer = request.getParameter("cambiaTrailer");
		if(cambiaTrailer!=null && !cambiaTrailer.equals("")) {
			try {
				videogiocoDetailed.setTrailer(cambiaTrailer);
				videogiocoDAO.doUpdate(videogiocoDetailed);
			} catch (SQLException e) {
				throw new MyException("Errore cambiamento trailer videogioco");
			}			
		}
		
		String rimuovereRecensione = request.getParameter("rimuovereRecensione");
		if(rimuovereRecensione!=null && !rimuovereRecensione.equals("")) {
			RecensioneBean recensione = new RecensioneBean();
			try {
				recensione = recensioneDAO.doRetrieveByUsername(account.getUsername(), Integer.parseInt(codiceVideogioco));
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
				if(recensioneDAO.doCheck(recensione))
					recensioneDAO.doSave(recensione);
				else
					throw new MyException("Recensione gi� rilasciata per questo gioco");
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
		
		String rangeRecensione = request.getParameter("rangeRecensione");
		if(rangeRecensione!=null && !rangeRecensione.equals("")) {
			String min = request.getParameter("min");
			String max = request.getParameter("max");
			if(min!=null && max!=null && !min.equals("") && !max.equals("")) {
				try {
					recensioni = recensioneDAO.doRetrieveAllByRange(videogiocoDetailed.getCodice(), orderRecensioni, Integer.parseInt(min), Integer.parseInt(max));
				} catch (NumberFormatException | SQLException e) {
					throw new MyException("Impossibile ricavare recensioni"); 
				}
			}			
		}
		
		boolean possibileAggiungereAllaLibreria=false;
		
		try {
			possibileAggiungereAllaLibreria = videogiocoDAO.doRetrieveAppartenenzaAllaLibreria(Integer.parseInt(codiceVideogioco), account.getUsername());
			System.out.println(possibileAggiungereAllaLibreria);
		} catch (NumberFormatException | SQLException e) {
			throw new MyException("Non si sa se si pu� aggiungere alla libreria");
		}
		
		if(possibileAggiungereAllaLibreria)
			request.setAttribute("possibileAggiungereAllaLibreria", "true");
		
		boolean possibileAggiungereAgliAcquisti=false;
		
		try {
			possibileAggiungereAgliAcquisti = videogiocoDAO.doRetrieveAppartenenzaAgliAcquisti(Integer.parseInt(codiceVideogioco), account.getUsername());
			System.out.println(possibileAggiungereAgliAcquisti);
		} catch (NumberFormatException | SQLException e) {
			throw new MyException("Non si sa se si pu� aggiungere agli acquisti");
		}
		
		if(possibileAggiungereAgliAcquisti)
			request.setAttribute("possibileAggiungereAgliAcquisti", "true");
		
		Collection<AccountBean> amici = null;
		
		try {
			amici = accountDAO.doRetrieveAllFriendsByVideogioco(account, Integer.parseInt(codiceVideogioco));
		} catch (SQLException e) {
			;
		}
		
		try {
			videogiocoDetailed = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(codiceVideogioco));
		} catch (NumberFormatException e) {
			throw new MyException("Codice non valido");
		} catch (SQLException e) {
			throw new MyException("Errore SQL per videogioco");
		}
		
		String casa = videogiocoDetailed.getISIN();
		try {
			casa = videogiocoDAO.doRetrieveCasaByCodice(videogiocoDetailed.getCodice());
		} catch (SQLException e) {
			throw new MyException("Errore SQL per casa videogioco");
		}
		
		request.setAttribute("videogiocoDetailed", videogiocoDetailed);
		request.setAttribute("recensioni", recensioni);
		request.setAttribute("amici", amici);
		request.setAttribute("nomeCasaEd", casa);
		
		String url = response.encodeURL("jsp/videogioco.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
