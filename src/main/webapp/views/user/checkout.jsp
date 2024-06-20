<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/checkout.css" />?${currentTime}" />
</head>

<body>
	<main class="p-5 position-relative">
		<fmt:setLocale value="en_US" />
		<section id="checkout-section">
			<div class="container">
				<form action="<c:url value="/checkout/order" />" method="POST">
					<div class="row">
						<div class="col-lg-8">
							<div class="card">
								<div class="card-body">
									<ul class="activity-checkout mb-0 px-4 mt-3">
										<li class="checkout-item active">
											<div class="avatar checkout-icon p-1">
												<div class="avatar-title rounded-circle bg-primary">
													<i class="fas fa-receipt fs-16"></i>
												</div>
											</div>
											<div class="feed-item-list">
												<h5 class="font-size-16 mb-1">Billing Info</h5>
												<p class="text-muted text-truncate mb-4">Sed ut perspiciatis unde omnis iste</p>
												<div class="mb-3">
													<div class="row">
														<div class="col-lg-4">
															<div class="mb-3">
																<label class="form-label" for="billing-name">Name</label>
																<input value="${user.name}" type="text" class="form-control" id="billing-name" placeholder="Enter name">
															</div>
														</div>
														<div class="col-lg-4">
															<div class="mb-3">
																<label class="form-label" for="billing-email-address">Email Address</label>
																<input value="${user.email}" type="email" class="form-control" id="billing-email-address" placeholder="Enter email">
															</div>
														</div>
														<div class="col-lg-4">
															<div class="mb-3">
																<label class="form-label" for="billing-phone">Phone</label>
																<input value="${user.phone}" type="text" class="form-control" id="billing-phone" placeholder="Enter Phone no.">
															</div>
														</div>
													</div>

													<div class="mb-3">
														<label class="form-label" for="billing-address">Address</label>
														<textarea class="form-control" id="billing-address" rows="3" placeholder="Enter full address">${user.address}</textarea>
													</div>

													<div class="row">
														<div class="col-lg-4">
															<div class="mb-4 mb-lg-0">
																<label class="form-label">Country</label>
																<select class="form-control form-select" title="Country">
																	<option value="0">Select Country</option>
																	<option value="AF">Afghanistan</option>
																	<option value="AL">Albania</option>
																	<option value="DZ">Algeria</option>
																	<option value="AS">American Samoa</option>
																	<option value="AD">Andorra</option>
																	<option value="AO">Angola</option>
																	<option value="AI">Anguilla</option>                                   
																</select>
															</div>
														</div>

														<div class="col-lg-4">
															<div class="mb-4 mb-lg-0">
																<label class="form-label" for="billing-city">City</label>
																<input type="text" class="form-control" id="billing-city" placeholder="Enter City">
															</div>
														</div>

														<div class="col-lg-4">
															<div class="mb-0">
																<label class="form-label" for="zip-code">Zip / Postal code</label>
																<input type="text" class="form-control" id="zip-code" placeholder="Enter Postal code">
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="checkout-item active">
											<div class="avatar checkout-icon p-1">
												<div class="avatar-title rounded-circle bg-primary">
													<i class="fas fa-truck fs-16"></i>
												</div>
											</div>
											<div class="feed-item-list">
												<div>
													<h5 class="font-size-16 mb-1">Shipping Info</h5>
													<p class="text-muted text-truncate mb-4">Neque porro quisquam est</p>
													<div class="mb-3">
														<div class="row">
															<div class="col-lg-4 col-sm-6">
																<div data-bs-toggle="collapse">
																	<label class="card-radio-label mb-0">
																		<input type="radio" name="address" id="info-address1" class="card-radio-input" checked="">
																		<div class="card-radio text-truncate p-3">
																			<span class="fs-14 mb-4 d-block">Address 1</span>
																			<span class="fs-14 mb-2 d-block">Bradley McMillian</span>
																			<span class="text-muted fw-normal text-wrap mb-1 d-block">109 Clarksburg Park Road Show Low, AZ 85901</span>
																			<span class="text-muted fw-normal d-block">Mo. 012-345-6789</span>
																		</div>
																	</label>
																</div>
															</div>

															<div class="col-lg-4 col-sm-6">
																<div>
																	<label class="card-radio-label mb-0">
																		<input type="radio" name="address" id="info-address2" class="card-radio-input">
																		<div class="card-radio text-truncate p-3">
																			<span class="fs-14 mb-4 d-block">Address 2</span>
																			<span class="fs-14 mb-2 d-block">Bradley McMillian</span>
																			<span class="text-muted fw-normal text-wrap mb-1 d-block">109 Clarksburg Park Road Show Low, AZ 85901</span>
																			<span class="text-muted fw-normal d-block">Mo. 012-345-6789</span>
																		</div>
																	</label>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="checkout-item">
											<div class="avatar checkout-icon p-1">
												<div class="avatar-title rounded-circle bg-primary">
													<i class="fas fa-wallet fs-16"></i>
												</div>
											</div>
											<div class="feed-item-list">
												<div>
													<h5 class="font-size-16 mb-1">Payment Info</h5>
													<p class="text-muted text-truncate mb-4">Duis arcu tortor, suscipit eget</p>
												</div>
												<div>
													<h5 class="font-size-14 mb-3">Payment method :</h5>
													<div class="row">
														<div class="col-lg-3 col-sm-6">
															<div data-bs-toggle="collapse">
																<label class="card-radio-label">
																	<input type="radio" name="pay-method" id="pay-methodoption1" class="card-radio-input">
																	<span class="card-radio py-3 text-center text-truncate">
																		<i class="bx bx-credit-card d-block h2 mb-3"></i>
																		Credit / Debit Card
																	</span>
																</label>
															</div>
														</div>

														<div class="col-lg-3 col-sm-6">
															<div>
																<label class="card-radio-label">
																	<input type="radio" name="pay-method" id="pay-methodoption2" class="card-radio-input">
																	<span class="card-radio py-3 text-center text-truncate">
																		<i class="bx bxl-paypal d-block h2 mb-3"></i>
																		Paypal
																	</span>
																</label>
															</div>
														</div>

														<div class="col-lg-3 col-sm-6">
															<div>
																<label class="card-radio-label">
																	<input type="radio" name="pay-method" id="pay-methodoption3" class="card-radio-input" checked="">

																	<span class="card-radio py-3 text-center text-truncate">
																		<i class="bx bx-money d-block h2 mb-3"></i>
																		<span>Cash on Delivery</span>
																	</span>
																</label>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="card checkout-order-summary">
								<div class="card-body">
									<div class="p-3 bg-light mb-3">
										<h5 class="fs-16 mb-0">
											Order Summary
											<!--<span class="float-end ms-2">#MN0124</span>-->
										</h5>
									</div>
									<div class="table-responsive">
										<table class="table table-centered mb-0 table-nowrap">
											<thead>
												<tr>
													<th class="border-top-0" scope="col">Product</th>
													<th class="border-top-0" scope="col">Subtotal</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${cart}" var="cartItem">
													<tr>
														<td>
															<div class="d-flex align-items-center">
																<h5 class="fs-16 m-0" style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 180px;">
																	<a target="_blank" href="<c:url value="/product/show?product_id=${cartItem.product.id}" />">${cartItem.product.name}</a>
																</h5>
																<span class="fw-bold ms-2">x ${cartItem.quantity}</span>
															</div>
														</td>
														<td style="text-align: end;"><fmt:formatNumber value="${cartItem.product.price * cartItem.quantity}" type="currency" /></td>
													</tr>
												</c:forEach>
												<tr>
													<td><h5 class="fs-16 m-0">Sub Total:</h5></td>
													<td style="text-align: end;"><fmt:formatNumber value="${subtotal}" type="currency" /></td>
												</tr>
												<tr>
													<td><h5 class="fs-16 m-0">VAT:</h5></td>
													<td style="text-align: end;">+ <fmt:formatNumber value="${vat}" type="currency" /></td>
												</tr>
												<tr class="text-primary fw-bold">
													<td><h5 class="fs-16 m-0">Total:</h5></td>
													<td style="text-align: end;"><fmt:formatNumber value="${total}" type="currency" /></td>
												</tr>
											</tbody>
										</table>
									</div>
									<button type="submit" class="mt-4 btn btn-primary w-100 rounded-pill fw-bold fs-16">PLACE ORDER</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</section>
	</main>
</body>
