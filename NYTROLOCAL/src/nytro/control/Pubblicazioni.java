package nytro.control;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.AccountBean;
import nytro.model.AccountDAO;
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;
import nytro.model.VideogiocoDemoBean;
import nytro.model.VideogiocoFreeToPlayBean;
import nytro.model.VideogiocoPagamentoBean;

@WebServlet("/Pubblicazioni")
@MultipartConfig(maxFileSize = 16177215)
public class Pubblicazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final AccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
//		if(account.getRuolo()!=2)
//			throw new MyException("Non disponi dei permessi necessari per visualizzare tale risorsa.");
		
		String isin=null;
		try {
			isin = accountDAO.doRetrieveIsinByUsername(account.getUsername());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String aggiungiVideogioco = request.getParameter("aggiungiVideogioco");
		if(aggiungiVideogioco!=null && !aggiungiVideogioco.equals("")) {
			String aggPegi = request.getParameter("aggPegi");
			String aggGenere = request.getParameter("aggGenere");
			String aggTitolo = request.getParameter("aggTitolo");
			String tipologia = request.getParameter("tipologia");
			
			if(tipologia!=null && aggTitolo!=null && aggPegi!=null && aggGenere!=null && 
					!aggTitolo.equals("") && !aggPegi.equals("") && !aggGenere.equals("")) {
				if(tipologia.equals("aPagamento")) {
					String aggPrezzo = request.getParameter("aggPrezzo");
					if(aggPrezzo!=null && !aggPrezzo.equals("")) {
						VideogiocoPagamentoBean bean = new VideogiocoPagamentoBean();
						bean.setISIN(isin);
						
						Date dataRilascio = new Date();
						String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(dataRilascio);
						bean.setDataRilascio(modifiedDate);
						
						bean.setTitolo(aggTitolo);
						bean.setPEGI(Integer.parseInt(aggPegi));
						bean.setImg(request.getPart("photo").getInputStream());
						bean.setPrezzo(Float.parseFloat(aggPrezzo));
						
						try {
							videogiocoDAO.doSaveVideogiocoPagamento(bean,aggGenere);
							videogiocoDAO.doRetrieveAll(null, isin);
							if(request.getPart("photo") != null && request.getPart("photo").getSize() > 0)
								videogiocoDAO.doUploadImage(bean);
							while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "")==null)
								;
						} catch (SQLException e) {
							throw new MyException("Videogioco a pagamento non salvato con successo");
						}						
					}
				}else if(tipologia.equals("demo")) {
					String aggCodiceVideogiocoPrincipale = request.getParameter("aggCodiceVideogiocoPrincipale");
					String aggDurata = request.getParameter("aggDurata");
					
					if(aggCodiceVideogiocoPrincipale!=null && !aggCodiceVideogiocoPrincipale.equals("") && 
							aggDurata!=null && !aggDurata.equals("")) {
						VideogiocoDemoBean bean = new VideogiocoDemoBean();
						bean.setISIN(isin);
						
						Date dataRilascio = new Date();
						String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(dataRilascio);
						bean.setDataRilascio(modifiedDate);
						
						bean.setTitolo(aggTitolo);
						bean.setPEGI(Integer.parseInt(aggPegi));
						bean.setImg(request.getPart("photo").getInputStream());
						bean.setCodiceVideogiocoPrincipale(Integer.parseInt(aggCodiceVideogiocoPrincipale));
						bean.setDurata(Integer.parseInt(aggDurata));
						
						try {
							videogiocoDAO.doSaveVideogiocoDemo(bean,aggGenere);
							if(request.getPart("photo") != null && request.getPart("photo").getSize() > 0)
								videogiocoDAO.doUploadImage(bean);
							while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "")==null)
								;
						} catch (SQLException e) {
							throw new MyException("Videogioco demo non salvato con successo");
						}						
					}
					
				}else if(tipologia.equals("freeToPlay")){
					String aggModalita = request.getParameter("aggModalita");
					
					if(aggModalita!=null && !aggModalita.equals("") && (aggModalita.equals("offline") || aggModalita.equals("online"))) {
						VideogiocoFreeToPlayBean bean = new VideogiocoFreeToPlayBean();
						bean.setISIN(isin);
						
						Date dataRilascio = new Date();
						String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(dataRilascio);
						bean.setDataRilascio(modifiedDate);
						
						bean.setTitolo(aggTitolo);
						bean.setPEGI(Integer.parseInt(aggPegi));
						bean.setImg(request.getPart("photo").getInputStream());
						bean.setModalitaDiGioco(aggModalita);	
						
						try {
							videogiocoDAO.doSaveVideogiocoFreeToPlay(bean,aggGenere);
							if(request.getPart("photo") != null && request.getPart("photo").getSize() > 0)
								videogiocoDAO.doUploadImage(bean);
							while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "")==null)
								;
						} catch (SQLException e) {
							throw new MyException("Videogioco free to play non salvato con successo");
						}						
					}
				}
			}
		}
		
		String cancelVideogioco = request.getParameter("cancelVideogioco");
		if(cancelVideogioco!=null && !cancelVideogioco.equals("")) {
			try {
				VideogiocoBean bean = videogiocoDAO.doRetrieveDetailedByCodice(Integer.parseInt(cancelVideogioco));
				videogiocoDAO.doDeleteVideogioco(bean);
				while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "").getDataRimozione()==null)
					;
			} catch (NumberFormatException | SQLException e) {
				throw new MyException("Errore cancellazione videogioco");
			}
		}
		
		
		String order = request.getParameter("order");
		String categoria = request.getParameter("categoria");
		
		Collection<VideogiocoBean> videogiochi = null;
		
		try {
			if(categoria==null || categoria.equals(""))
				videogiochi = videogiocoDAO.doRetrieveAll(order, isin);
			else if(categoria.equalsIgnoreCase("Demo"))
				videogiochi = videogiocoDAO.doRetrieveAllDemo(order, isin);
			else if(categoria.equalsIgnoreCase("A pagamento"))
				videogiochi = videogiocoDAO.doRetrieveAllPagamento(order, isin);
			else if(categoria.equalsIgnoreCase("Free to play"))
				videogiochi = videogiocoDAO.doRetrieveAllFreeToPlay(order, isin);
			
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.setAttribute("videogiochi", videogiochi);

		String url = response.encodeURL("jsp/pubblicazioni.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
