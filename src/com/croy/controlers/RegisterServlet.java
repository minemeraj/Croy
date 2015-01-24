package com.croy.controlers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.croy.beans.User;
import com.croy.tables.AreaManager;
import com.croy.tables.UserManager;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
			String email = request.getParameter("email");
			if (UserManager.isEmailExists(email)) {
				request.setAttribute("error", "Email Address Already Exists!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}

			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String mobile = request.getParameter("mobile");
			int area_id = Integer.parseInt(request.getParameter("area_id"));
			int location_id = AreaManager.getLocationIdByAreaId(area_id);
			User user = new User();
			user.setAllInfoByStrings(email, password, name, mobile,
					location_id, area_id);
			if (UserManager.insert(user)) {
				request.setAttribute("successful", "Registration Complete, Please Login!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("error", "Something is wrong, Please Try Again!");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("register.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
