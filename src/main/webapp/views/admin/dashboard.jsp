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
						<h1 class="m-0">Chào mừng trở lại ${user.name}!</h1>
					</div>
				</div>
			</div>
		</div>

		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">

			</div>
		</section>
	</div>
</body>

<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/admin/assets/js/demo.js" />?${currentTime}"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value="/admin/assets/js/pages/dashboard.js" />?${currentTime}"></script>