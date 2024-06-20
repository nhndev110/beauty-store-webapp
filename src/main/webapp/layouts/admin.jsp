<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
		<title><sitemesh:write property="title" /> | Trang Quản Trị | BEAUTY STORE</title>
		<!-- Google Font: Source Sans Pro -->
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/fontawesome-free/css/all.min.css" />">
		<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css" />">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/icheck-bootstrap/icheck-bootstrap.min.css" />">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/overlayScrollbars/css/OverlayScrollbars.min.css" />">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/daterangepicker/daterangepicker.css" />">
		<link rel="stylesheet" href="<c:url value="/assets/admin/vendors/summernote/summernote-bs4.min.css" />">
		<!-- Theme style -->
		<link rel="stylesheet" href="<c:url value="/assets/admin/css/adminlte.min.css" />">
	</head>
	<body class="hold-transition sidebar-mini layout-fixed">
		<div class="wrapper">
			<!-- Preloader -->
			<div class="preloader flex-column justify-content-center align-items-center">
				<img class="animation__shake" src="<c:url value="/assets/admin/img/AdminLTELogo.png" />" alt="AdminLTELogo" height="60" width="60">
			</div>
			<!-- Navbar -->
			<c:import url="/components/admin/navbar.jsp" />
			<!-- Sidebar -->
			<c:import url="/components/admin/sidebar.jsp" />
			<sitemesh:write property="body" />
			<c:import url="/components/admin/footer.jsp" />
			<!-- Control Sidebar -->
			<aside class="control-sidebar control-sidebar-dark">
				<!-- Control sidebar content goes here -->
			</aside>
		</div>

		<script src="<c:url value="/assets/admin/vendors/jquery/jquery.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/jquery-ui/jquery-ui.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/bootstrap/js/bootstrap.bundle.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/chart.js/Chart.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/sparklines/sparkline.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/jquery-knob/jquery.knob.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/moment/moment.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/daterangepicker/daterangepicker.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/summernote/summernote-bs4.min.js" />"></script>
		<script src="<c:url value="/assets/admin/vendors/overlayScrollbars/js/jquery.overlayScrollbars.min.js" />"></script>
		<script src="<c:url value="/assets/admin/js/adminlte.min.js" />"></script>
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<sitemesh:write property="page.script" />
	</body>
</html>
