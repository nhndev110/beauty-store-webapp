<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<a href="<c:url value="/admin/dashboard" />" class="brand-link">
		<img src="<c:url value="/assets/admin/img/AdminLTELogo.png" />" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
		<span class="brand-text font-weight-light">AdminLTE 3</span>
	</a>

	<div class="sidebar">
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-item">
					<a href="<c:url value="/admin/dashboard" />" class="nav-link">
						<i class="nav-icon fas fa-th"></i>
						<p>Trang Chủ</p>
					</a>
				</li>
				<li class="nav-item ${servletPath.startsWith("/admin/products") ? "menu-is-opening menu-open" : ""}">
					<a href="#" class="nav-link">
						<i class="nav-icon fas fa-th"></i>
						<p>
							Sản Phẩm
							<i class="fas fa-angle-left right"></i>
						</p>
					</a>
					<ul class="nav nav-treeview" style="display: ${servletPath.startsWith("/admin/products") ? "block" : "none"};">
						<li class="nav-item">
							<a href="<c:url value="/admin/products" />" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Danh Sách Sản Phẩm</p>
							</a>
						</li>
						<li class="nav-item">
							<a href="<c:url value="/admin/products/create" />" class="nav-link">
								<i class="far fa-circle nav-icon"></i>
								<p>Thêm Sản Phẩm</p>
							</a>
						</li>
					</ul>
				</li>
        <li class="nav-item">
					<a href="<c:url value="/admin/banner" />" class="nav-link">
						<i class="nav-icon fas fa-th"></i>
						<p>Banner</p>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</aside>