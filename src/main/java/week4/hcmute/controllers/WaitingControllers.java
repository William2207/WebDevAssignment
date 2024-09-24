package week4.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import week4.hcmute.models.UserModel;

import java.io.IOException;

/**
 * Servlet implementation class WaitingControllers
 */
@WebServlet(urlPatterns = "/waiting")
public class WaitingControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null && session.getAttribute("account") != null) {
			UserModel u = (UserModel) session.getAttribute("account");
			request.setAttribute("username", u.getUsername());
			if (u.getRoleid() == 2) {
				response.sendRedirect(request.getContextPath() + "/admin/home");

			} else if (u.getRoleid() == 1) {
				response.sendRedirect(request.getContextPath() + "/home");

			} else {
				response.sendRedirect(request.getContextPath() + "/home");
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
