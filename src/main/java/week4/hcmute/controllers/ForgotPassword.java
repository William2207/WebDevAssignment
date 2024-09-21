package week4.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import week4.hcmute.services.IUserService;
import week4.hcmute.services.impl.UserServiceImpl;
import week4.hcmute.models.*;
/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet(urlPatterns= {"/fpw"} )
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("view/ForgotPassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IUserService ius = new UserServiceImpl();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserModel um = new UserModel(email,password);
		ius.update(um);
		//response.sendRedirect("/login");
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
