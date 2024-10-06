package week4.hcmute.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import week4.hcmute.utls.Constants;

@WebServlet(urlPatterns = "/image") 
public class DownloadFileController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = req.getParameter("fname");
		 File file = new File(Constants.DIR + "/" + fileName);
		 resp.setContentType("image/jpeg");
		 if (file.exists()) {
		 IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		 }
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	

}
