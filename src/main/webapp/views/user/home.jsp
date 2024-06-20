<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
</head>

<body>
	<main class="p-5 position-relative">
		<c:import url="/components/user/carousel.jsp" />

		<section class="mt-4">
			<div class="container">
				<h3 class="fs-4 fw-normal text-uppercase">DANH MỤC</h3>
				<div class="row g-0">
					<c:forEach items="${categories}" var="category">
						<div class="col-md-2">
							<div class="card h-100 rounded-0">
								<a href="<c:url value="/home?category_id=${category.id}" />" class="link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover">
									<div class="card-body py-2 d-flex justify-content-between align-items-center flex-column">
										<img height="60px" src="<c:url value="/assets/user/img/category/${category.thumbnail}" />" alt="">
										<h6 class="card-title mt-2">${category.name}</h6>
									</div>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>

		<c:if test="${not empty productsByCategory}">
			<c:forEach items="${productsByCategory}" var="products">
				<section class="mt-4">
					<div class="container">
						<h3 class="fs-4 fw-normal text-uppercase">${products.key}</h3>
						<div class="row">
							<c:forEach items="${products.value}" var="product">
								<div class="col-lg-3 col-md-6 col-sm-6 d-flex">
									<a href="<c:url value="/product/show?product_id=${product.id}" />">
										<div class="card w-100 my-2 shadow-2-strong">
											<img
												src="<c:url value="/assets/user/img/products/${product.image}" />"
												class="card-img-top"
												style="aspect-ratio: 1 / 1"
												/>
											<div class="card-body">
												<h3
													class="card-title h6"
													style="overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;"
													>
													${product.name}
												</h3>
												<div class="d-flex">
													<p class="card-text text-danger me-4">
														<strong>$${product.price}</strong>
													</p>
												</div>
											</div>
										</div>
									</a>
								</div>
							</c:forEach>
						</div>
					</div>
				</section>
			</c:forEach>
		</c:if>

		<!--											
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
		-->

		<c:if test="${empty productsByCategory}">
			<section class="mt-4">
				<div class="container">
					<h3 class="fs-4 fw-normal text-uppercase">Danh Sách Trống</h3>
				</div>
			</section>
		</c:if>

		<c:import url="/components/user/toast.jsp" />
	</main>
</body>

<content tag="script">
	<script type="module" src="<c:url value="/assets/user/js/home.js" />?${currentTime}"></script>
</content>

