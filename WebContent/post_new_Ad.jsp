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

			<div class="col-lg-6">
				<form method="post" role="form" autocomplete="on"
					data-toggle="validator">
					<fieldset>
						<div class="form-group">
							<label>Select Your Ad Category</label> <select
								name="sub_category" class="selectpicker form-control"
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
								placeholder="Ad Title" name="password" type="text" required
								maxlength="14" id="inputPassword"
								data-error="This fiels is required!">
							<div class="help-block with-errors">The ad title cannot
								more than 14 character long!</div>
						</div>

						<div class="form-group">
							<label>Ad Title</label> <input class="form-control" name="title"
								required data-error="This fiels is required!">
							<div class="help-block"></div>
						</div>
						<div class="form-group">
							<label>Text Input with Placeholder</label> <input
								class="form-control" placeholder="Enter text">
						</div>
						<div class="form-group">
							<label>Static Control</label>
							<p class="form-control-static">email@example.com</p>
						</div>
						<div class="form-group">
							<label>File input</label> <input type="file">
						</div>
						<div class="form-group">
							<label>Text area</label>
							<textarea class="form-control" rows="3"
								style="margin: 0px -6px 0px 0px; height: 96px; width: 453px;"></textarea>
						</div>
						<div class="form-group">
							<label>Checkboxes</label>
							<div class="checkbox">
								<label> <input type="checkbox" value="">Checkbox
									1
								</label>
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" value="">Checkbox
									2
								</label>
							</div>
							<div class="checkbox">
								<label> <input type="checkbox" value="">Checkbox
									3
								</label>
							</div>
						</div>
						<div class="form-group">
							<label>Inline Checkboxes</label> <label class="checkbox-inline">
								<input type="checkbox">1
							</label> <label class="checkbox-inline"> <input type="checkbox">2
							</label> <label class="checkbox-inline"> <input type="checkbox">3
							</label>
						</div>
						<div class="form-group">
							<label>Radio Buttons</label>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios1" value="option1" checked="">Radio 1
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios2" value="option2">Radio 2
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="optionsRadios"
									id="optionsRadios3" value="option3">Radio 3
								</label>
							</div>
						</div>
						<div class="form-group">
							<label>Inline Radio Buttons</label> <label class="radio-inline">
								<input type="radio" name="optionsRadiosInline"
								id="optionsRadiosInline1" value="option1" checked="">1
							</label> <label class="radio-inline"> <input type="radio"
								name="optionsRadiosInline" id="optionsRadiosInline2"
								value="option2">2
							</label> <label class="radio-inline"> <input type="radio"
								name="optionsRadiosInline" id="optionsRadiosInline3"
								value="option3">3
							</label>
						</div>

						<button type="submit" class="btn btn-success pull-right">Post</button>
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