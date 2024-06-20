<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${product.name}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/product.css" />?${currentTime}" />
</head>

<body>
	<main class="p-5 position-relative">
		<section>
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-12" style="min-height: 400px;">
						<div class="">
							<div class="d-flex justify-content-center" id="">
								<img
									style="width: max-content; object-fit: cover; object-position: center;"
									src="<c:url value="/assets/user/img/products/${product.image}" />"
									/>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<h1 class="fs-4 fw-bold">${product.name}</h1>
						<div class="d-flex mt-2">
							<div class="me-2">
								<i class="far fa-star"></i>
								<i class="far fa-star"></i>
								<i class="far fa-star"></i>
								<i class="far fa-star"></i>
								<i class="far fa-star"></i>
							</div>
							<span class="review-no">0 Đánh giá</span>
						</div>
						<p class="mt-2">${fn:replace(product.description, "\\n", "</br>")}</p>
						<h4 class="fw-bold text-primary">
							$${product.price}
						</h4>
						<div class="d-flex align-items-center mt-2">
							<h4 class="me-4 fs-5">Số lượng:</h4>
							<div class="input-group" style="width: 120px;">
								<button
									class="btn-update-cart-item btn btn-light btn-sm"
									data-update-type="desc"
									data-product-id="${product.id}"
									>
									<i class="bi bi-dash"></i>
								</button>
								<input
									min="1"
									name="quantity"
									value="1"
									type="number"
									class="input-quantity form-control shadow-none border-light text-center"
									/>
								<button
									class="btn-update-cart-item btn btn-light btn-sm"
									data-update-type="asc"
									data-product-id="${product.id}"
									>
									<i class="bi bi-plus"></i>
								</button>
							</div>
						</div>
						<div class="d-flex mt-4">
							<button class="btn btn-lg btn-primary me-2 w-75">
								<span class="fw-bold">MUA NGAY</span>
								<span class="d-block fs-6">(Giao nhanh từ 2 giờ hoặc nhận tại cửa hàng)</span>
							</button>
							<button
								class="btn btn-lg btn-outline-primary add-to-cart"
								data-product-id="${product.id}"
								>
								<i class="fas fa-cart-plus"></i>
								<span class="d-block fs-6 text-nowrap">Thêm vào giỏ</span>
							</button>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
</body>

<content tag="script">
	<script type="module" src="<c:url value="/assets/user/js/product/detail.js" />?${currentTime}"></script>
</content>