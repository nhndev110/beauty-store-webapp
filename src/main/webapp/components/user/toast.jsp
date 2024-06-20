<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<c:if test="${statusRegister}">
	<div class="toast-container position-fixed bottom-0 start-0 p-3">
		<div id="toastRegisterSuccess" class="toast bg-primary" role="alert" aria-live="assertive" aria-atomic="true">
			<div class="toast-header text-primary">
				<div class="me-2">
					<i class="bi bi-check-circle-fill"></i>
				</div>
				<strong class="me-auto">Success</strong>
				<small>few seconds ago</small>
				<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
			</div>
			<div class="toast-body text-white">
				Bạn đã đăng ký thành công tài khoản
			</div>
		</div>
	</div>
</c:if>