<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
	<title>${title}</title>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/popover-custom.css" />?${currentTime}"/>
	<link rel="stylesheet" href="<c:url value="/assets/user/css/tooltip-custom.css" />?${currentTime}"/>
</head>

<body>
	<main class="p-5 position-relative">
		<section>
			<div class="container h-100">
				<div class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-lg-12 col-xl-11">
						<div class="card text-black" style="border-radius: 25px;">
							<div class="card-body p-md-5">
								<div class="row justify-content-center">
									<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

										<h1 class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Form Register</h1>

										<form class="mx-1 mx-md-4 needs-validation" action="" method="post" autocomplete="off">

											<div class="d-flex flex-row align-items-center mb-4 position-relative">
												<i class="fas fa-user fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<label class="form-label fw-bold" for="form3Example1c">
														Name
														<span class="text-danger">*</span>
													</label>
													<input
														name="name"
														type="text"
														id="form3Example1c"
														class="form-control ${message.nameError != null ? "is-invalid" : ""}"
														value="${userInput.userName}"
														/>
													<div class="invalid-tooltip">${message.nameError}</div>
												</div>
											</div>

											<div class="d-flex flex-row align-items-center mb-4 position-relative">
												<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<label class="form-label fw-bold" for="form3Example3c">
														Email
														<span class="text-danger">*</span>
													</label>
													<input
														name="email"
														type="email"
														id="form3Example3c"
														class="form-control ${message.emailError != null ? "is-invalid" : ""}"
														value="${userInput.userEmail}"
														/>
													<div class="invalid-tooltip">${message.emailError}</div>
												</div>
											</div>

											<div class="d-flex flex-row align-items-center mb-4 position-relative">
												<i class="fas fa-phone fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<label class="form-label fw-bold" for="form3Example3c">
														Phone
														<span class="text-danger">*</span>
													</label>
													<input
														name="phone"
														type="phone"
														id="form3Example3c"
														class="form-control ${message.phoneError != null ? "is-invalid" : ""}"
														value="${userInput.userPhone}"
														/>
													<div class="invalid-tooltip">${message.phoneError}</div>
												</div>
											</div>

											<div class="d-flex flex-row align-items-center mb-4 position-relative">
												<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<label class="form-label fw-bold" for="form3Example4c">
														Password
														<span class="text-danger">*</span>
													</label>
													<input
														name="password"
														type="password"
														id="form3Example4c"
														class="form-control ${message.passwordError != null ? "is-invalid" : ""}"
														value="${userInput.userPassword}"
														/>
													<div class="invalid-tooltip">${message.passwordError}</div>
												</div>
											</div>

											<div class="d-flex flex-row align-items-center mb-4 position-relative">
												<i class="fas fa-key fa-lg me-3 fa-fw"></i>
												<div class="form-outline flex-fill mb-0">
													<label class="form-label fw-bold" for="form3Example4cd">
														Repeat password
														<span class="text-danger">*</span>
													</label>
													<input
														name="repassword"
														type="password"
														id="form3Example4cd"
														class="form-control ${message.repasswordError != null ? "is-invalid" : ""}"
														value="${userInput.userRePassword}"
														/>
													<div class="invalid-tooltip">${message.repasswordError}</div>
												</div>
											</div>

											<div class="form-check d-flex justify-content-center mb-5">
												<input class="form-check-input me-2" type="checkbox" value="" id="form2Example3c" />
												<label class="form-check-label" for="form2Example3">
													I agree all statements in <a href="#!">Terms of service</a>
												</label>
											</div>

											<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
												<button type="submit" class="btn btn-primary">Register</button>
											</div>

											<div class="mb-5">
												<a href="<c:url value="/login" />">Already have an account?</a>
											</div>
										</form>

									</div>
									<div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">
										<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
												 class="img-fluid" alt="Sample image">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
</body>

<content tag="script">
	<script src="<c:url value="/assets/user/js/register.js" />?${currentTime}"></script>
</content>