package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.AccountDAO;

/**
 * Servlet implementation class VisualizzaImmagine
 */
@WebServlet("/image/*")
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
        String imageName = (String) request.getAttribute("id");
        System.out.println(imageName);

        try {
        	AccountDAO dao = new AccountDAO();
        	byte[] image = dao.doDisplayImage(imageName);
        	if (image != null) {
                response.setContentType(getServletContext().getMimeType(imageName));
                response.setContentLength(image.length);
                response.getOutputStream().write(image);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            } 
        }catch (SQLException e) {
            throw new ServletException("Something failed at SQL/DB level.", e);
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
