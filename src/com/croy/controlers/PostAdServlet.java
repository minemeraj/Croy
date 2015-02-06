package com.croy.controlers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.croy.beans.Ad;
import com.croy.beans.Image;
import com.croy.tables.AdManager;
import com.croy.tables.CategoryManager;
import com.croy.tables.ImageManager;

/**
 * Servlet implementation class PostAdServlet
 */
@WebServlet("/PostAdServlet")
public class PostAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostAdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);

			Ad ad = new Ad();
			ArrayList<String> images = new ArrayList<String>();

			for (FileItem item : items) {

				if (item.isFormField()) {

					if (item.getFieldName().equals("sub_category_id")) {
						int sub_category_id = Integer
								.parseInt(item.getString());
						ad.setSub_category_id(sub_category_id);
						ad.setCategory_id(CategoryManager
								.getCategory_Id_By_Sub_Category_Id(sub_category_id));
					} else if (item.getFieldName().equals("title")) {
						ad.setTitle(item.getString());
					} else if (item.getFieldName().equals("price")) {
						ad.setPrice(item.getString());
					} else if (item.getFieldName().equals("description")) {
						ad.setDescription(item.getString());
					} else {
						System.out.println("What the hell!");
					}

					System.out.println(item.getFieldName() + " "
							+ item.getString());

				} else {

					String fileName = FilenameUtils.getName(item.getName());

					if (FilenameUtils.getBaseName(fileName).length() < 3) {
						fileName = "abcd".concat(fileName);
					}

					System.out.println("Filename -> " + fileName);

					String realPath = getServletContext().getRealPath("/");

					File file = new File(realPath + "/images");

					if (!file.exists()) {
						file.mkdir();
					}

					String prefix = FilenameUtils.getBaseName(fileName) + "_";

					String suffix = "." + FilenameUtils.getExtension(fileName);

					// Prepare filename prefix and suffix for an unique filename
					// in upload folder.

					File tempFile = File.createTempFile(prefix, suffix, file);

					System.out.println("tempFile -> " + tempFile);

					images.add("images/"
							+ FilenameUtils.getBaseName(tempFile.toString())
							+ suffix);

					item.write(tempFile);

				}
			}

			HttpSession httpSession = request.getSession();

			ad.setUser_id((int) httpSession.getAttribute("user_id"));

			ad.setAdd_id(AdManager.insertAndGetTheId(ad));

			Image image = new Image();

			image.setAd_id(ad.getAdd_id());

			for (String url : images) {

				image.setUrl(url);

				ImageManager.insert(image);
			}

			response.sendRedirect("item.jsp?ad_id=" + ad.getAdd_id());

		} catch (FileUploadException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
