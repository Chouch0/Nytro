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
import nytro.model.VideogiocoBean;
import nytro.model.VideogiocoDAO;


@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final VideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final int numeroMaxVideogiochiDaMostrare = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<VideogiocoBean> videogiochiPiuAcquistati, videogiochiPiuGiocati, videogiochiPiuVotati;
		VideogiocoBean videogiocoPiuGiocatoMaschi, videogiocoPiuGiocatoFemmine, videogiocoPiuGiocatoGenderless;
		
		
		try {
			videogiochiPiuAcquistati = videogiocoDAO.doRetrievePiuAcquistati(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuGiocati = videogiocoDAO.doRetrievePiuGiocati(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuVotati = videogiocoDAO.doRetrievePiuVotati(numeroMaxVideogiochiDaMostrare);
			videogiocoPiuGiocatoMaschi = videogiocoDAO.doRetrievePiuGiocatoMaschi();
			videogiocoPiuGiocatoFemmine = videogiocoDAO.doRetrievePiuGiocatoFemmine();
			videogiocoPiuGiocatoGenderless = videogiocoDAO.doRetrievePiuGiocatoGenerless();
		} catch (SQLException e) {
			throw new MyException("Errore SQL per index");
		}
		
		request.setAttribute("videogiochiPiuAcquistati", videogiochiPiuAcquistati);
		request.setAttribute("videogiochiPiuGiocati", videogiochiPiuGiocati);
		request.setAttribute("videogiochiPiuVotati", videogiochiPiuVotati);
		request.setAttribute("videogiocoPiuGiocatoMaschi", videogiocoPiuGiocatoMaschi);
		request.setAttribute("videogiocoPiuGiocatoFemmine", videogiocoPiuGiocatoFemmine);
		request.setAttribute("videogiocoPiuGiocatoGenderless", videogiocoPiuGiocatoGenderless);

		String url = response.encodeURL("jsp/index.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
