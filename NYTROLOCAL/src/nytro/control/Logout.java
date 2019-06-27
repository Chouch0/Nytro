package nytro.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		request.getSession().removeAttribute("account");

		String dest = request.getHeader("referer");
		if (dest == null || dest.contains("/Logout") || dest.trim().isEmpty()) {
			dest = ".";
		}
		response.sendRedirect(dest);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
