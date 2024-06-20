<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
</head>

<body>
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">Edit Product</h1>
					</div>
				</div>
			</div>
		</div>

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Product information</h3>
					</div>
					<!-- form start -->
					<form method="POST" action="<c:url value="/admin/products/update?page=${param.page}" /><c:if test="${not empty param.q}">&q=${param.q}</c:if>" enctype="multipart/form-data">
							<div class="card-body">
								<input type="number" name="productID" value="${product.id}" hidden>

							<div class="form-group">
								<label>Name</label>
								<input value="${product.name}" name="productName" type="text" class="form-control" placeholder="Product name" />
							</div>

							<div class="row">
								<div class="col-12 col-sm-6">
									<div class="form-group">
										<label>Price</label>
										<div class="input-group">
											<div class="input-group-prepend">
												<span class="input-group-text">
													<i class="fas fa-dollar-sign"></i>
												</span>
											</div>
											<input value="${product.price}" name="productPrice" type="number" min="0.01" step="0.01" class="form-control" placeholder="Price" />
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-6">
									<div class="form-group">
										<label>Quantity</label>
										<input value="${product.quantity}" name="productQuantity" type="number" min="0" class="form-control" placeholder="Quantity" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-12 col-sm-7">
									<div class="form-group">
										<label>Image</label>
										<div class="input-group">
											<div class="custom-file">
												<input name="productNewImage" type="file" class="custom-file-input" id="mediaFile" />
												<label class="custom-file-label" for="mediaFile">Choose file new</label>
												<input hidden name="productOldImage" type="text" value="${product.image}" />
											</div>
										</div>
										Old image:
										<img height="100px" src="<c:url value="/assets/user/img/products/${product.image}" />" alt="alt"/>
									</div>
								</div>
								<div class="col-12 col-sm-5">
									<div class="form-group">
										<label>Category</label>
										<select name="categoryID" class="form-control">
											<c:forEach items="${categories}" var="category">
												<option
													${category.id == product.categoryId ? "selected" : ""}
													value="${category.id}"
													>
													${category.name}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label>Description</label>
								<textarea name="productDescription" class="form-control" rows="8" placeholder="Product Description">${product.description}</textarea>
							</div>

							<div class="custom-control custom-checkbox">
								<input name="productStatus" class="custom-control-input custom-control-input-primary custom-control-input-outline" type="checkbox" id="checkboxShowOrHide" ${product.status ? "checked" : ""}>
								<label for="checkboxShowOrHide" class="custom-control-label">Show product</label>
							</div>
						</div>
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Update</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</body>

<content tag="script">
	<!-- bs-custom-file-input -->
	<script src="<c:url value="/assets/admin/vendors/bs-custom-file-input/bs-custom-file-input.min.js" />"></script>
	<script>
		$(function () {
			bsCustomFileInput.init();
		});
	</script>
</content>