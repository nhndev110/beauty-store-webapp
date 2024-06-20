<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container d-flex flex-nowrap w-100 z-3">
		<!-- Toggle button -->
		<button
			class="navbar-toggler"
			type="button"
			data-bs-toggle="collapse"
			data-bs-target="#navbarNav"
			aria-controls="navbarNav"
			aria-expanded="false"
			aria-label="Toggle navigation"
			>
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse d-flex justify-content-between" id="navbarNav">
			<a class="navbar-brand" href="<c:url value="/home" />">
				<img
					src="<c:url value="/assets/user/img/logo/logo.png" />"
					height="40"
					alt="NHNDEV Beauty"
					loading="lazy"
					/>
				BEAUTY STORE
			</a>

			<form method="GET" action="<c:url value="/shop/search" />" class="d-flex" role="search" style="width: 40%">
        <input value="${param.q}" name="q" class="d-flex rounded-pill form-control border-2 fs-6 py-1 px-3" type="text" placeholder="Tìm kiếm ..." aria-label="Search" />
      </form>

			<ul class="navbar-nav d-flex align-items-center">
				<li class="nav-item me-3">
					<a
						class="nav-link ${servletPath.startsWith("/cart") ? "active" : ""} position-relative p-0"
						href="<c:url value="/cart" />"
						>
						<i class="bi bi-cart2" style="font-size: 22px;"></i>
						<span style="font-size: 10px;" class="position-absolute translate-middle top-0 start-100 badge rounded-pill bg-danger">
							<span id="cart-quantity-navbar">${not empty cart ? cart.size() : 0}</span>
							<span class="visually-hidden">unread messages</span>
						</span>
					</a>
				</li>

				<!-- Avatar -->
				<c:if test="${user != null}">
					<li class="nav-item dropdown">
						<a
							class="nav-link dropdown-toggle d-flex align-items-center hidden-arrow"
							href="#"
							id="navbarDropdown"
							role="button"
							data-bs-toggle="dropdown"
							aria-expanded="false"
							>
							<i class="bi bi-person-circle" style="font-size: 22px;"></i>
						</a>
						<ul
							class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown"
							style="min-width: 180px;"
							>
							<li>
								<a class="dropdown-item" href="#">
									<div class="d-flex row align-items-center">
										<div class="col-4">
											<i style="font-size: 30px;" class="bi bi-person-circle"></i>
										</div>
										<div class="col-8">
											<span>${user.name}</span>
										</div>
									</div>
								</a>
							</li>
							<div class="dropdown-divider"></div>
							<c:if test="${isAdmin}">
								<li>
									<a class="dropdown-item" target="_blank" href="<c:url value="/admin/dashboard" />">
										Tới Phần Quản Trị
									</a>
								</li>
							</c:if>
							<li>
								<a class="dropdown-item" href="#">Đơn Mua</a>
							</li>
							<li>
								<a class="dropdown-item" href="<c:url value="/logout" />">
									Đăng Xuất
								</a>
							</li>
						</ul>
					</li>
				</c:if>

				<!-- Login -->
				<c:if test="${user == null}">
					<li class="nav-item d-flex align-items-center">
						<a
							class="nav-link ${servletPath.startsWith("/login") ? "active" : ""}"
							href="<c:url value="/login" />"
							>
							Đăng Nhập
						</a>
						<span class="mx-1 text-black text-opacity-50">/</span>
						<a
							class="nav-link ${servletPath.startsWith("/register") ? "active" : ""}"
							href="<c:url value="/register" />"
							>
							Đăng Ký
						</a>
					</li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
