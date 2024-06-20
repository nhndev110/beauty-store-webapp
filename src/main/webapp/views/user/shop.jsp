<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/main.css" />?${currentTime}" />
</head>

<body>
	<main class="p-5 position-relative">
		<section>
			<div class="container">
				<h3 style="text-align: center;">Shop</h3>
				<div class="row">
					<c:forEach items="${productList}" var="product">
						<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
							<div class="card w-100 my-2 shadow-2-strong">
								<img
									src="<c:url value="/assets/user/img/products/${product.image}" />"
									class="card-img-top"
									style="aspect-ratio: 1 / 1"
									/>
								<div class="card-body">
									<h3 class="card-title h6" style="overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;">
										<a href="<c:url value="/product/show" />?product_id=${product.id}">${product.name}</a>
									</h3>
									<div class="d-flex">
										<p class="card-text text-danger me-4">
											<strong>$${product.price}</strong>
										</p>
									</div>
								</div>
								<div class="card-footer d-flex align-items-center justify-content-between p-3 mt-auto">
									<div class="btn-group rounded-pill shadow-0 d-flex align-items-center">
										<div class="btn btn-primary cart-icon">
											<i class="bi bi-cart2" style="font-size: 16px;"></i>
										</div>
										<button
											class="add-to-cart btn btn-primary"
											data-product-id="${product.id}"
											>
											Add to cart
										</button>
									</div>
									<a href="#!" class="btn btn-light border icon-hover">
										<i class="bi bi-heart text-danger" style="font-size: 16px;"></i>
									</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
	</main>
</body>