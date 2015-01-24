<%@page import="com.croy.utility.Utility"%>
<jsp:include page="includes/header.jsp"></jsp:include>


<div class="col-md-4 col-md-offset-4">
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Please Login</h3>
		</div>
		<div class="panel-body">
			<%
				String msg = (String) request.getAttribute("error");
				if (msg != null) {
					out.print(Utility.display_errors(msg));
				}

				String success = (String) request.getAttribute("successful");
				if (success != null) {
					out.print(Utility.display_success(success));
				}
			%>
			<form action="LoginServlet" method="post" role="form"
				autocomplete="on" data-toggle="validator">
				<fieldset>
					<div class="form-group">
						<input class="form-control" placeholder="E-mail" name="email"
							type="email" required id="inputEmail"
							data-error="Sorry! email is invalid"
							value="<%String email = request.getParameter("email");
			if (email != null) {
				out.print(email);
			}%>">
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="Password" name="password"
							type="password" required data-minlength="6" id="inputPassword"
							data-error="The password should be at least 6 lharacter long!">
						<div class="help-block with-errors"></div>
					</div>

					<!-- <div class="checkbox">
					<label> <input name="remember" value="Remember Me"
						type="checkbox">Remember Me
					</label>
				</div> -->
					<input class="btn btn-lg btn-success btn-block" type="submit"
						name="submit" value="Login" />
				</fieldset>
			</form>

		</div>
	</div>

</div>

<jsp:include page="includes/footer.jsp"></jsp:include>