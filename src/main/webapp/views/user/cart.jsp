<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/cart.css" />?${currentTime}" />
</head>

<body>
	<main class="p-5 position-relative">
		<fmt:setLocale value="en_US" />
		<section id="cart-section">
			<div class="container">
				<c:if test="${!isEmptyCart}">
					<div id="exist-cart" class="row d-flex justify-content-center">
						<div class="col-md-9">
							<div class="card mb-4">
								<div class="card-header py-3 d-flex align-items-center justify-content-between">
									<h5 class="mb-0">Cart</h5>
									<div>
										<a href="<c:url value="/cart/clear" />" class="btn btn-secondary">Clear cart</a>
									</div>
								</div>
								<div class="card-body">
									<c:if test="${not empty cart}">
										<c:forEach items="${cart}" var="cartItem">
											<div class="row align-items-center py-2">
												<div class="col-lg-6 col-md-3 mb-4 mb-lg-0">
													<div class="d-flex align-items-center">
														<img
															src="<c:url value="/assets/user/img/products/${cartItem.product.image}" />"
															style="height: 60px;"
															class="rounded me-2"
															/>
														<p class="">
															${cartItem.product.name}
														</p>
													</div>
												</div>

												<div class="col-lg-2">
													<span class="fw-bold text-primary">
														<fmt:formatNumber value="${cartItem.product.price}" type="currency" />
													</span>
												</div>

												<div class="col-lg-2 col-md-6 mb-4 mb-lg-0">
													<!-- Quantity -->
													<div class="input-group">
														<button
															class="btn-update-cart-item btn btn-light btn-sm"
															data-update-type="desc"
															data-product-id="${cartItem.product.id}"
															>
															<i class="bi bi-dash"></i>
														</button>

														<input
															min="1"
															name="quantity"
															value="${cartItem.quantity}"
															type="number"
															class="form-control shadow-none border-light text-center"
															style="height: 32px;"
															disabled
															readonly
															/>

														<button
															class="btn-update-cart-item btn btn-light btn-sm"
															data-update-type="asc"
															data-product-id="${cartItem.product.id}"
															>
															<i class="bi bi-plus"></i>
														</button>
													</div>
													<!-- Quantity -->
												</div>

												<div class="col-lg-2 text-end">
													<button
														type="button"
														class="btn btn-light btn-sm"
														>
														<i class="bi bi-heart text-danger"></i>
													</button>
													<button
														type="button"
														class="btn-remove-cart-item btn btn-light btn-sm me-1"
														data-product-id="${cartItem.product.id}"
														>
														<i class="bi bi-x-lg text-danger"></i>
													</button>
												</div>
											</div>
										</c:forEach>
									</c:if>
									<!-- Single item -->
								</div>
							</div>
							<div class="card mb-4 mb-lg-0">
								<div class="card-body">
									<p><strong>We accept</strong></p>
									<img
										class="me-2"
										width="45px"
										src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/visa.svg"
										alt="Visa"
										/>
									<img
										class="me-2"
										width="45px"
										src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/amex.svg"
										alt="American Express"
										/>
									<img
										class="me-2"
										width="45px"
										src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce-gateway-stripe/assets/images/mastercard.svg"
										alt="Mastercard"
										/>
									<img
										class="me-2"
										width="45px"
										src="https://mdbcdn.b-cdn.net/wp-content/plugins/woocommerce/includes/gateways/paypal/assets/images/paypal.png"
										alt="PayPal acceptance mark"
										/>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<div class="card mb-4">
								<div class="card-header py-3">
									<h5 class="mb-0">Cart totals</h5>
								</div>
								<div class="card-body">
									<ul class="list-group list-group-flush">
										<li
											class="list-group-item d-flex justify-content-between align-items-center px-0"
											style="font-weight: 500;"
											>
											Subtotal
											<span id="subtotal-amount">
												<fmt:formatNumber value="${requestScope.subtotal}" type="currency" />
											</span>
										</li>
										<li
											class="list-group-item d-flex justify-content-between align-items-center px-0"
											style="font-size: 14px;"
											>
											VAT
											<div>
												+ <span id="vat-amount"><fmt:formatNumber value="${requestScope.vat}" type="currency" /></span>
											</div>
										</li>
										<li
											class="fw-bold list-group-item text-primary d-flex justify-content-between align-items-center border-0 px-0 mb-3"
											>
											<div>
												<span>Total</span>
												<p class="mb-0">(including VAT)</p>
											</div>
											<div id="total-amount" class="fw-bold">
												<fmt:formatNumber value="${requestScope.total}" type="currency" />
											</div>
										</li>
									</ul>

									<a href="<c:url value="/checkout" />" class="btn btn-primary w-100 rounded-pill">
										Check Out
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<div id="empty-cart" class="d-${isEmptyCart ? "flex" : "none"} justify-content-center align-items-center flex-column">
					<img src="<c:url value="/assets/user/img/empty-cart.png" />" width="348px" alt="" />
					<a href="<c:url value="/home" />" class="btn btn-outline-primary d-inline-block rounded-pill">
						<i class="bi bi-arrow-left"></i>
						<span class="ps-2">Tiếp Tục Mua Hàng</span>
					</a>
				</div>
			</div>
		</section>
	</main>
</body>

<content tag="script">
	<script type="module" src="<c:url value="/assets/user/js/cart.js" />?${currentTime}"></script>
</content>
