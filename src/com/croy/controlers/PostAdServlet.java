package com.croy.controlers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.croy.beans.Ad;

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
		// TODO Auto-generated method stub

		try {
			process(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void process(HttpServletRequest request) throws Exception {

		try {

			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);

			Ad ad = new Ad();

			for (FileItem item : items) {

				if (item.isFormField()) {

					if (item.getFieldName().equals("sub_category_id")) {
						ad.setSub_category_id(Integer.parseInt(item.getString()));
//						ad.setCategory_id();
					}



					if (item.getFieldName().equals("title")) {
						ad.setTitle(item.getString());
					}

					if (item.getFieldName().equals("price")) {
						ad.setPrice(item.getString());
					}

					if (item.getFieldName().equals("description")) {
						ad.setDescription(item.getString());
					}

					System.out.println(item.getFieldName() + " "
							+ item.getString());

				} else {

					String fileName = FilenameUtils.getName(item.getName());

					System.out.println("Filename -> " + fileName);

					String realPath = getServletContext().getRealPath("/");

					File file = new File(realPath + "/uploadFolder");

					file.mkdir();

					String prefix = FilenameUtils.getBaseName(fileName) + "_";

					String suffix = "." + FilenameUtils.getExtension(fileName);

					// Prepare filename prefix and suffix for an unique filename
					// in upload folder.

					File tempFile = File.createTempFile(prefix, suffix, file);

					System.out.println("tempFile -> " + tempFile);

					item.write(tempFile); // File uploaded to "uploadFolder" in
											// Web Server(Not database)

					// Save the File path(String) to Database now.

				}
			}

		} catch (FileUploadException e) {

			e.printStackTrace();

			throw new Exception(e);

		} catch (IOException e) {

			e.printStackTrace();

			throw new Exception(e);

		} catch (Exception e) {

			e.printStackTrace();

			throw new Exception(e);
		}

	}

}
