<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/login.css" />?${currentTime}" />
</head>

<body>
	<main class="p-5 position-relative">
		<section>
			<div class="container">
				<div class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-md-9 col-lg-6 col-xl-5">
						<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
								 class="img-fluid" alt="Sample image">
					</div>
					<div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
						<form action="<c:url value="/login" />" method="post">
							<div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
								<h1 class="my-4">Form Login</h1>
							</div>

							<div class="d-${empty msgError ? "none" : "flex"} alert-err-login alert alert-danger align-items-center alert-dismissible fade show" role="alert">
								<i class="bi bi-exclamation-triangle-fill me-2"></i>
								<div class="alert-content">${msgError}</div>
								<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
							</div>

							<!-- Email input -->
							<div class="form-floating mb-4">
								<input
									type="text"
									name="emailOrPhone"
									id="floatingInput"
									class="form-control form-control-lg"
									style="font-size: 16px;"
									placeholder="Email Or Phone"
									value="${emailOrPhone}"
									autocomplete="on"
									/>
								<label class="opacity-75" for="floatingInput">Email Or Phone</label>
							</div>

							<!-- Password input -->
							<div class="form-floating mb-4">
								<input
									type="password"
									name="password"
									id="floatingPassword"
									class="form-control form-control-lg"
									style="font-size: 16px;"
									placeholder="Password"
									${not empty emailOrPhone ? "autofocus" : ""}
									autocomplete="on"
									/>
								<label class="opacity-75" for="floatingPassword">Password</label>
							</div>

							<div class="d-flex justify-content-between align-items-center">
								<!-- Checkbox -->
								<div class="form-check mb-0">
									<input class="form-check-input me-2" type="checkbox" name="rememberMe" id="form2Example3" />
									<label class="form-check-label" for="form2Example3">
										Remember me
									</label>
								</div>
								<a href="#!" class="text-body">Forgot password?</a>
							</div>

							<div class="text-center text-lg-start mt-4 pt-2">
								<button type="submit" class="btn btn-primary btn-lg w-100 rounded-pill">Login</button>
								<p class="small fw-bold mt-2 pt-1 mb-0">
									Don't have an account?
									<a href="<c:url value="/register" />" class="link-primary">Register</a>
								</p>
							</div>

						</form>
					</div>
				</div>
			</div>
		</section>
	</main>
</body>
<content tag="script">
	<script type="module" src="<c:url value="/assets/user/js/login.js" />?${currentTime}"></script>
</content>
