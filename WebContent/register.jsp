<%@page import="com.croy.utility.Utility"%>
<%@page import="com.croy.tables.AreaManager"%>
<%@page import="com.croy.beans.Area"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.croy.tables.LocationManager"%>
<%@page import="com.croy.beans.Location"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="col-md-4 col-md-offset-4">
	<div class="login-panel panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Please Register</h3>
		</div>
		<div class="panel-body">

			<%
				String msg = (String) request.getAttribute("error");
				if (msg != null) {
					out.print(Utility.display_errors(msg));
				}
			%>

			<form action="RegisterServlet" method="post" role="form"
				autocomplete="on" data-toggle="validator">
				<fieldset>
					<div class="form-group">
						<input class="form-control" placeholder="E-mail" name="email"
							type="email" required id="inputEmail"
							data-error="Sorry! email is invalid"
							value="
						<%String email = request.getParameter("email");
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
					<div class="form-group">
						<input class="form-control" placeholder="Name" name="name"
							type="text" id="inputName" required
							data-error="The name cannot be empty"
							value="<%String name = request.getParameter("name");
			if (name != null) {
				out.print(name);
			}%>">
						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="Mobile" name="mobile"
							type="tel" required data-minlength="11" id="inputMobile"
							data-error="Mobile number is either invalid or empty!"
							value="<%String mobile = request.getParameter("mobile");
			if (mobile != null) {
				out.print(mobile);
			}%>">

						<div class="help-block with-errors"></div>
					</div>
					<div class="form-group">
						<label class="control-label">Area: </label> <select name="area_id"
							class="selectpicker form-control" data-live-search="true"
							required data-error="Must select your area!" id="inputArea">
							<option disabled selected value="">--Please Select Area--</option>
							<%
								String area_id = request.getParameter("area_id");
								ArrayList<Location> locations = LocationManager.getAllLocation();
								for (Location location : locations) {
									out.print("<optgroup label=\"");
									out.print(location.getLocation());
									out.print("\">");

									ArrayList<Area> areas = AreaManager
											.getAreaByLocationId(location.getLocation_id());
									for (Area area : areas) {
										out.print("<option ");
										if (area_id != null) {
											int a_id = Integer.parseInt(area_id);
											if (a_id == area.getArea_id()) {
												out.print("selected ");
											}
										}
										out.print(" value=\"");
										out.print(area.getArea_id());
										out.print("\">");
										out.print(area.getArea());
										out.print("</option>");
									}
									out.print("</optgroup>");
								}
							%>
						</select>
						<div class="help-block with-errors">Area should be selected</div>
					</div>
					<!-- <div class="checkbox">
					<label> <input name="remember" value="Remember Me"
						type="checkbox">Remember Me
					</label>
				</div> -->
					<input class="btn btn-lg btn-success btn-block" type="submit"
						name="submit" value="Register" />
				</fieldset>
			</form>

		</div>
	</div>

</div>

<jsp:include page="includes/footer.jsp"></jsp:include>