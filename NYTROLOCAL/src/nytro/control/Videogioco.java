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
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;


@WebServlet("/Videogioco")
public class Videogioco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
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
			throw new MyException("Errore SQL");
		}
		
		request.setAttribute("videogiocoDetailed", videogiocoDetailed);

		request.getRequestDispatcher("jsp/videogioco.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
