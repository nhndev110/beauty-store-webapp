<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
		<title><sitemesh:write property="title" /> | BEAUTY STORE</title>
		<link rel="stylesheet" href="<c:url value="/assets/user/vendors/bootstrap/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/assets/user/vendors/bootstrap-icons/font/bootstrap-icons.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/assets/user/vendors/animate/animate.min.css" />" />
		<link rel="stylesheet" href="<c:url value="/assets/user/vendors/fontawesome-free/css/all.min.css" />" />
		<link rel="stylesheet" href="<c:url value="/assets/user/css/main.css" />?${currentTime}" />
		<sitemesh:write property="head" />
	</head>
	<body>
		<c:import url="/components/user/header.jsp" />
		<sitemesh:write property="body" />
		<c:import url="/components/user/footer.jsp" />
		<script src="<c:url value="/assets/user/vendors/jquery/jquery.min.js" />"></script>
		<script src="<c:url value="/assets/user/vendors/bootstrap/js/bootstrap.bundle.min.js" />"></script>
		<sitemesh:write property="page.script" />
	</body>
</html>