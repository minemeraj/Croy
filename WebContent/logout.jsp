
<%
	request.getSession().invalidate();

	request.setAttribute("successful", "You are Logged Out!");
	request.getRequestDispatcher("login.jsp")
			.forward(request, response);
%>