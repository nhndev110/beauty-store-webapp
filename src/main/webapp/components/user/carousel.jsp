<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>
<div id="carouselExampleCaptions" class="carousel slide container" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner rounded shadow-4-strong">
    <div class="carousel-item active" data-bs-interval="10000">
			<img src="<c:url value="/assets/user/img/banner/banner-4.jpg" />" class="d-block w-100" alt=""/>
    </div>
    <div class="carousel-item" data-bs-interval="10000">
			<img src="<c:url value="/assets/user/img/banner/banner-5.jpg" />" class="d-block w-100" alt=""/>
    </div>
    <div class="carousel-item" data-bs-interval="10000">
			<img src="<c:url value="/assets/user/img/banner/banner-6.jpg" />" class="d-block w-100" alt=""/>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
		<div class="bg-dark bg-opacity-75 rounded-pill">
			<i class="bi bi-caret-left-fill p-2 text-white" style="font-size: 30px;"></i>
		</div>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <div class="bg-dark bg-opacity-75 rounded-pill">
			<i class="bi bi-caret-right-fill p-2 text-white" style="font-size: 30px;"></i>
		</div>
    <span class="visually-hidden">Next</span>
  </button>
</div>
