<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
</head>

<body>
	<div class="content-wrapper">
		<div class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1 class="m-0">${title}</h1>
					</div>
				</div>
			</div>
		</div>

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="card">
					<!-- form start -->
					<form method="POST" action="<c:url value="/admin/products/store" />" enctype="multipart/form-data">
						<div class="card-body">
							<div class="form-group">
								<label>Name</label>
								<input name="productName" type="text" class="form-control" placeholder="Product name" />
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
											<input name="productPrice" type="number" min="0.01" step="0.01" class="form-control" placeholder="Price" />
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-6">
									<div class="form-group">
										<label>Quantity</label>
										<input name="productQuantity" type="number" min="0" class="form-control" placeholder="Quantity" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-12 col-sm-7">
									<div class="form-group">
										<label>Media</label>
										<div class="input-group">
											<div class="custom-file">
												<input name="productImage" type="file" class="custom-file-input" id="mediaFile" />
												<label class="custom-file-label" for="mediaFile">Choose file</label>
											</div>
											<div class="input-group-append">
												<span class="input-group-text">Upload</span>
											</div>
										</div>
									</div>
								</div>
								<div class="col-12 col-sm-5">
									<div class="form-group">
										<label>Category</label>
										<select name="categoryID" class="form-control">
											<c:forEach items="${categories}" var="category">
												<option value="${category.id}">${category.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label>Description</label>
								<textarea name="productDescription" class="form-control" rows="4" placeholder="Product Description"></textarea>
							</div>

							<div class="custom-control custom-checkbox">
								<input name="productStatus" class="custom-control-input custom-control-input-primary custom-control-input-outline" type="checkbox" id="checkboxShowOrHide" checked>
								<label for="checkboxShowOrHide" class="custom-control-label">Show product</label>
							</div>
						</div>
						<div class="card-footer">
							<button type="submit" class="btn btn-primary">Create</button>
							<button type="reset" class="btn btn-secondary">Reset</button>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</body>

<content tag="script">
	<script src="<c:url value="/admin/assets/plugins/bs-custom-file-input/bs-custom-file-input.min.js" />"></script>
	<script>
		$(function () {
			bsCustomFileInput.init();
		});
	</script>
</content>
