<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/components/taglib.jsp" %>

<head>
  <title>${title}</title>
</head>

<body>
  <div class="content-wrapper">
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2 justify-content-between">
          <div class="col-sm-6">
            <h1 class="m-0">${title}</h1>
          </div>
          <div class="">
            <div class="btn-group">
              <a href="<c:url value="/admin/products/create" />" class="px-3 btn btn-primary rounded-pill">
                <i class="pr-1 fas fa-plus"></i>
                Sản Phẩm Mới
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-12">
            <c:if test="${not empty status}">
              <div class="callout callout-${status}">
                <h5><i class="icon fas fa-${status ? "check" : "exclamation"}"></i> Alert!</h5>
                <p>${msg}</p>
              </div>
            </c:if>
            <div class="card">
              <div class="card-header">
                <div class="card-tools">
                  <form action="<c:url value="/admin/products" />" method="GET">
                    <div class="input-group" style="width: 200px;">
                      <input type="text" value="${param.q}" name="q" class="form-control float-right" placeholder="Search">
                      <div class="input-group-append">
                        <button type="submit" class="btn btn-default">
                          <i class="fas fa-search"></i>
                        </button>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
              <div class="card-body table-responsive p-0">
                <table class="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>
                        <input type="checkbox" name="name">
                      </th>
                      <th>ID</th>
                      <th>Product</th>
                      <th>Price</th>
                      <th>Quantity</th>
                      <th>Category</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:if test="${products.size() > 0}">
                      <c:forEach items="${products}" var="product">
                        <tr>
                          <td>
                            <input type="checkbox" name="name">
                          </td>
                          <td>${product.id}</td>
                          <td style="overflow: hidden; white-space: nowrap; text-overflow: ellipsis; max-width: 150px;">
                            ${product.name}
                          </td>
                          <td>$${product.price}</td>
                          <td>${product.quantity}</td>
                          <td>${product.category.name}</td>
                          <td>
                            <div class="dropdown">
                              <a href="#" class="btn btn-sm btn-outline-dark rounded-pill" data-toggle="dropdown">
                                <i style="font-size: 12px;" class="fas fa-ellipsis-h"></i>
                              </a>
                              <ul class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="<c:url value="/admin/products/edit?product_id=${product.id}&page=${pageCurrent}" /><c:if test="${not empty param.q}">&q=${param.q}</c:if>">
                                  <i class="pr-1 fas fa-edit"></i>
                                  Sửa
                                </a>
                                <a class="dropdown-item" href="<c:url value="/admin/products/delete?product_id=${product.id}&file_image=${product.image}&page=${pageCurrent}" /><c:if test="${not empty param.q}">&q=${param.q}</c:if>">
                                  <i class="pr-1 far fa-trash-alt"></i>
                                  Xóa
                                </a>
                              </ul>
                            </div>
                          </td>
                        </tr>
                      </c:forEach>
                    </c:if>
                    <c:if test="${products.size() <= 0}">
                      <tr>
                        <td colspan="6" style="text-align: center;">PRODUCT LIST EMPTY</td>
                      </tr>
                    </c:if>
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->

              <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                  <c:forEach var="page" begin="1" end="${totalPage}">
                    <li class="page-item ${page == pageCurrent ? "active" : ""}">
                      <a class="page-link" href="<c:url value="/admin/products?page=${page}" /><c:if test="${not empty param.q}">&q=${param.q}</c:if>">
                        ${page}
                      </a>
                    </li>
                  </c:forEach>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</body>

<content tag="script">
  <script type="module" src="<c:url value="/assets/admin/js/pages/product/list.js" />"></script>
</content>