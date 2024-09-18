package week4.hcmute.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.eclipse.tags.shaded.org.apache.bcel.classfile.Constant;

import week4.hcmute.models.UserModel;
import week4.hcmute.services.IUserService;
import week4.hcmute.services.impl.UserServiceImpl;
/**
 * Servlet implementation class HelloControllers
 */
public class HelloControllers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//req.getRequestDispatcher("views/login.jsp").forward(req, resp);
		request.getRequestDispatcher("view/Login.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		boolean isRememberMe = false;
		String remember = request.getParameter("remember"); 
		
		if("on".equals(remember))
		{
			isRememberMe = true;
		}
		String alertMsg="";
		
		if (username.isEmpty() || password.isEmpty())
		{
			alertMsg = "Tai khoan hoac mat khau khong duoc rong";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("view/Login.jps").forward(request,response);
			return;
		}
		IUserService service = new UserServiceImpl();
		UserModel user = service.login(username, password);
		if(user!=null)
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("account", user);
			
			if(isRememberMe)
			{
				saveRemeberMe(response,username);
			}
			response.sendRedirect(request.getContextPath()+"/waiting");
		
		} else {
			alertMsg = "Tai Khoan va mat khau khogn dung";
			request.setAttribute("alert", alertMsg);
			request.getRequestDispatcher("/view/Login.jsp").forward(request,response);
		}
		
			
	}
	public static final String SESSION_USERNAME ="username";
	public static final String COOKIE_REMEMBER="username";
	
	private void saveRemeberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(COOKIE_REMEMBER, username);
		cookie.setMaxAge(30*60);
		response.addCookie(cookie);
	}
	

}
