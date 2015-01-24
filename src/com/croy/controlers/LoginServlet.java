package com.croy.controlers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.croy.beans.Location;
import com.croy.beans.User;
import com.croy.services.LoginService;
import com.croy.tables.AreaManager;
import com.croy.tables.LocationManager;
import com.croy.tables.UserManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (LoginService.authenticate(email, password)) {
			HttpSession httpSession = request.getSession();
			try {
				User user = UserManager.getUserByEmail(email);
				httpSession.setAttribute("email", user.getEmail());
				httpSession.setAttribute("name", user.getName());
				httpSession.setAttribute("mobile", user.getMobile());
				httpSession.setAttribute("location", user.getLocation());
				httpSession.setAttribute("area", user.getArea());
				httpSession.setAttribute("user",
						AreaManager.getAreaByLocationId(user.getLocation_id()));
				httpSession.setAttribute("user", user);
				response.sendRedirect("index.jsp");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("error", "Email or Password Doesn't match!");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}

	}

}
