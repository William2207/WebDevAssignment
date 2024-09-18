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
public class WaitingControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WaitingControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if(session !=null && session.getAttribute("account")!=null)
				{
					UserModel u=(UserModel) session.getAttribute("account");
					request.setAttribute("username", u.getUsername());
					if(u.getRoleid()==1) {
						response.sendRedirect(request.getContextPath()+"/admin/home");
			
					}else if(u.getRoleid()==2) {
						response.sendRedirect(request.getContextPath()+"/manager/home");
						
					}else {
						response.sendRedirect(request.getContextPath()+"/home");
					}
				}else {
					response.sendRedirect(request.getContextPath()+"/login");
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
