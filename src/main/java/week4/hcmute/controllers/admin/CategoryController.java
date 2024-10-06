package week4.hcmute.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import week4.hcmute.models.CatogryModel;
import week4.hcmute.services.ICategoryServices;
import week4.hcmute.services.impl.CategoryServiceImpl;
import week4.hcmute.utls.Constants;


@MultipartConfig()
@WebServlet (urlPatterns= {"/admin/categories", "/admin/category/add","/admin/category/insert","/admin/category/delete","/admin/category/update","/admin/category/edit"})
public class CategoryController  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ICategoryServices cateService = new CategoryServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url=req.getRequestURI();
		if(url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/view/admin/category-add.jsp").forward(req, resp);
			
		}else if(url.contains("/admin/categories")) {
			List<CatogryModel> list= cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/view/admin/category-list.jsp").forward(req, resp);
			
		}else if(url.contains("/admin/category/delete")) {
			int id =Integer.parseInt(req.getParameter("id"));
			cateService.delete(id);
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}else if(url.contains("/admin/category/edit")) {
			int id =Integer.parseInt(req.getParameter("id"));
			CatogryModel category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/view/admin/category-edit.jsp").forward(req, resp);
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url=req.getRequestURI();
		if(url.contains("/admin/category/insert")) {
			// lay du lieu tu view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");
			// dua vao model
			CatogryModel category = new CatogryModel();
			category.setCategoryname(categoryname);
			category.setStatus(status);
			//Xu ly upload file
			String fname="";
			String uploadPath= Constants.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists())
			{
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images1");
				if(part.getSize()>0) {
					String filename =Paths.get(part.getSubmittedFileName()).getFileName().toString();
					//doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis()+"."+ext;
					//upload file
					part.write(uploadPath+"/"+fname );
					// ghi ten file vao data
					category.setImages(fname);
					
					
				}else if(images!=null) {
					category.setImages(images);
				}else {
					category.setImages("avata.png");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			//truyen model vao insert
			cateService.insert(category);
			//chuyen  ve view
			resp.sendRedirect(req.getContextPath()+"/admin/categories");
		}else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("cateid"));
			String categoryname = req.getParameter("categoryname");
			String image = req.getParameter("image");
			int status = Integer.parseInt(req.getParameter("status"));

			CatogryModel cate = new CatogryModel();
			cate.setCategoryid(id);
			cate.setCategoryname(categoryname);
			cate.setImages(image);
			cate.setStatus(status);
			CatogryModel cateold = new CatogryModel();
			cateold = cateService.findById(id);
			String fname = "";
			String uploadPath = Constants.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					cate.setImages(fname);
				} else {
					cate.setImages(cateold.getImages());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			cateService.update(cate);
			resp.sendRedirect(req.getContextPath() + "/admin/categorys");
		}
		
	}
	
	
}
