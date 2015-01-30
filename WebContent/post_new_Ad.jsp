<%@page import="com.croy.tables.SubCategoryManager"%>
<%@page import="com.croy.beans.SubCategory"%>
<%@page import="com.croy.beans.Category"%>
<%@page import="com.croy.tables.CategoryManager"%>
<%@page import="java.util.ArrayList"%>
<jsp:include page="includes/header.jsp"></jsp:include>

<div class="col-md-7 col-md-offset-3">
	<div class="panel panel-default">
		<div class="panel-heading">Post Your Ad</div>
		<div class="panel-body">

			<div class=".col-md-6 .col-md-offset-3">
				<form action="PostAdServlet" method="post" role="form" autocomplete="on"
					data-toggle="validator" enctype="multipart/form-data">
					<fieldset>
						<div class="form-group">
							<label>Select Your Ad Category</label> <select
								name="sub_category_id" class="selectpicker form-control"
								data-live-search="true" required
								data-error="Must select your Ad Category" id="inputSubCategory">
								<option disabled selected value="">--Please Select
									Area--</option>
								<%
									ArrayList<Category> categories = CategoryManager.getAllCategory();
										for (Category category : categories) {
											out.print("<optgroup label=\"");
											out.print(category.getCategory());
											out.print("\">");
											ArrayList<SubCategory> subCategories = SubCategoryManager
													.getSubCategoryByCategoryId(category.getCategory_Id());
											for (SubCategory subCategory : subCategories) {
												out.print("<option ");
												out.print(" value=\"");
												out.print(subCategory.getSub_Category_id());
												out.print("\">");
												out.print(subCategory.getSub_Category());
												out.print("</option>");
											}
											out.print("</optgroup>");
										}
								%>

							</select>
						</div>
						<div class="form-group">
							<label>Ad Title</label> <input class="form-control"
								placeholder="Ad Title" name="title" type="text" required
								id="inputPassword" data-error="This fiels is required!">
							<div class="help-block with-errors"></div>
						</div>

						<div class="form-group">
							<label>Price</label> <input class="form-control"
								placeholder="Price" name="price" type="text" pattern="\d+(\.\d{2})?" required
								id="inputPassword" data-error="This fiels is required and enter valid price!">
							<div class="help-block with-errors"></div>
						</div>

						<div class="form-group">
							<label>Upload Images</label> <input id="input-20" name="file"
								type="file" multiple=true accept="image/*" required>
							<div class="help-block with-errors">Please all pictures at a time.</div>
						</div>

						<div class="form-group">
							<label>Ad Description</label>
							<textarea class="form-control" rows="3" required
								name="description"></textarea>
							<div class="help-block with-errors">Description must be
								written.</div>
						</div>

						<input type="submit" class="btn btn-success pull-right"
							value="Post">

					</fieldset>
				</form>
			</div>
			<!-- /.col-lg-6 (nested) -->


			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /.panel -->
</div>


<jsp:include page="includes/footer.jsp"></jsp:include>