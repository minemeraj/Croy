<%@page import="com.croy.tables.ImageManager"%>
<%@page import="com.croy.tables.UserManager"%>
<%@page import="com.croy.beans.User"%>
<%@page import="java.sql.Date"%>
<%@page import="com.croy.tables.AdManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.croy.beans.Ad"%>
<jsp:include page="includes/header.jsp"></jsp:include>


<%
	if (request.getParameter("ad_id") == null) {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	int ad_id;
	Ad ad;
	User user;
	ArrayList<String> imageUrls;
	ad_id = Integer.parseInt(request.getParameter("ad_id"));
	ad = AdManager.getAdById(ad_id);
	user = UserManager.get_User_by_id(ad.getUser_id());
	imageUrls = ImageManager.getImageUrlsByAdId(ad_id);
%>


<div class="col-md-3">
	<p class="lead">Posted By</p>
	<h3><%=user.getName()%></h3>

	<ul class="list-group">
		<li class="list-group-item"><%=user.getEmail()%></li>
		<li class="list-group-item"><%=user.getMobile()%></li>
	</ul>
</div>

<div class="col-md-9">

	<div class="thumbnail">
		<div id="carousel-example-generic" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<%
					for (int i = 1; i <= imageUrls.size(); i++) {
				%>
				<li class=<%if (i == 1) {
					out.print("active");
				}%>
					data-target="#carousel-example-generic" data-slide-to="<%=i%>"></li>
				<%
					}
				%>

			</ol>
			<div class="carousel-inner">
				<%
					for (int i = 0; i < imageUrls.size(); i++) {
				%>

				<div class="item<%if (i == 0) {
					out.print(" active");
				}%>">
					<img class="slide-image" src="<%=imageUrls.get(i)%>" alt="">
				</div>

				<%
					}
				%>

			</div>
			<a class="left carousel-control" href="#carousel-example-generic"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left"></span>
			</a> <a class="right carousel-control" href="#carousel-example-generic"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right"></span>
			</a>
		</div>
		<div class="caption-full">
			<h1 class="pull-right">
				<span class="label label-info">Tk <%=ad.getPrice()%></span>
			</h1>
			<h1>
				<%=ad.getTitle()%>
			</h1>
			
			<h5>
				Posted On: <%=ad.getPost_date()%>
			</h5>

			<div class="well">

				<p>
					<%=ad.getDescription()%>
				</p>

			</div>
		</div>

	</div>



</div>
<jsp:include page="includes/footer.jsp"></jsp:include>