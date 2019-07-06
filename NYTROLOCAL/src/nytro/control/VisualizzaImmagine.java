package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountDAO;
import nytro.model.VideogiocoDAO;

/**
 * Servlet implementation class VisualizzaImmagine
 */
@WebServlet("/image")
public class VisualizzaImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizzaImmagine() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("id");
        String codice = (String) request.getParameter("codice");
        
        if (id != null) {
	        try {
	        	AccountDAO dao = new AccountDAO();
	        	byte[] image = dao.doDisplayImage(id);
	        	if (image != null) {
	                response.setContentLength(image.length);
	                response.getOutputStream().write(image);
	                response.setContentType("image/jpg");
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            } 
	        	response.getOutputStream().close();
	        }catch (SQLException e) {
	            throw new ServletException("Something failed at SQL/DB level.", e);
	        }
        } else if (codice != null) {
	        try {
	        	int code = Integer.parseInt(codice);
	        	VideogiocoDAO dao = new VideogiocoDAO();
	        	byte[] image = dao.doDisplayImage(code);
	        	if (image != null) {
	                response.setContentLength(image.length);
	                response.getOutputStream().write(image);
	                response.setContentType("image/jpg");
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            } 
	        	response.getOutputStream().close();
	        }catch (SQLException e) {
	            throw new ServletException("Something failed at SQL/DB level.", e);
	        }
        }
    }	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
