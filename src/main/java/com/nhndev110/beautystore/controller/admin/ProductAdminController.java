package com.nhndev110.beautystore.controller.admin;

import com.nhndev110.beautystore.dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.ICategoryService;
import com.nhndev110.beautystore.service.IProductService;
import jakarta.inject.Inject;

@WebServlet(name = "ProductAdminServlet", urlPatterns = {"/admin/products/*"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
public class ProductAdminController extends HttpServlet {

  @Inject
  IProductService productService;

  @Inject
  ICategoryService categoryService;

  protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String action = req.getPathInfo();

    if (action == null) {
      action = "/";
    }

    switch (action) {
      case "/" -> {
        req.setAttribute("title", "Tất Cả Sản Phẩm");
        showList(req, resp);
      }
      case "/create" -> {
        req.setAttribute("title", "Thêm Sản Phẩm");
        create(req, resp);
      }
      case "/store" ->
        store(req, resp);
      case "/show" ->
        req.setAttribute("title", "Hiển Thị Sản Phẩm");
      case "/delete" ->
        delete(req, resp);
      case "/edit" -> {
        req.setAttribute("title", "Chỉnh Sửa Sản Phẩm");
        edit(req, resp);
      }
      case "/update" ->
        update(req, resp);
      default ->
        throw new AssertionError();
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    req.setAttribute("categories", categoryService.getCategories());

    processRequest(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    processRequest(req, resp);
  }

  protected void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);

    String query = (req.getParameter("q") != null) ? req.getParameter("q") : "";
    int pageCurrent = (req.getParameter("page") != null) ? Integer.parseInt(req.getParameter("page")) : 1;

    req.setAttribute("pageCurrent", pageCurrent);

    int productQty = productService.countProductByQuery(query);
    req.setAttribute("totalPage", (int) Math.ceil(productQty / 10.0f));

    req.setAttribute("products", productService.getProducts(query, pageCurrent));

    req.getRequestDispatcher("/views/admin/product/list.jsp").forward(req, resp);

    session.removeAttribute("status");
    session.removeAttribute("msg");
  }

  protected void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/views/admin/product/create.jsp").forward(req, resp);
  }

  protected void store(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession(false);

    String name = req.getParameter("productName") != null ? req.getParameter("productName") : "";
    String description = req.getParameter("productDescription") != null ? req.getParameter("productDescription") : "";
    double price = req.getParameter("productPrice") != null ? Double.parseDouble(req.getParameter("productPrice")) : 0.0f;
    int quantity = req.getParameter("productQuantity") != null ? Integer.parseInt(req.getParameter("productQuantity")) : 0;
    int categoryId = req.getParameter("categoryID") != null ? Integer.parseInt(req.getParameter("categoryID")) : 0;
    boolean status = req.getParameter("productStatus") != null;
    String fileImageName = "";
    try {
      Part fileImage = req.getPart("productImage");

      String[] parts = fileImage.getSubmittedFileName().split("\\.");

      fileImageName = parts[0] + "_" + session.getAttribute("currentTime") + "." + parts[1];

      String folderImagePath = session.getAttribute("APP_PATH") + "\\web\\assets\\images\\products\\";
      String savePath = folderImagePath + fileImageName;

      fileImage.write(savePath);

    } catch (IOException | ServletException ex) {
      Logger.getLogger(ProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }

    ProductDTO product = productService.insertProduct(
      new ProductModel(
        name,
        fileImageName,
        description,
        price,
        quantity,
        status,
        categoryId
      )
    );
    if (product != null) {
      resp.sendRedirect(req.getContextPath() + "/admin/products");
    } else {
      resp.sendRedirect(req.getContextPath() + "/admin/products/create");
    }
  }

  protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    HttpSession session = req.getSession(false);

    int productId = req.getParameter("product_id") != null ? Integer.parseInt(req.getParameter("product_id")) : 0;

    boolean isDeleted = productService.deleteProductById(productId);

    String fileName = req.getParameter("file_image");
    if (isDeleted && !fileName.isEmpty()) {
      String folderImagePath = session.getAttribute("APP_PATH") + "\\web\\assets\\images\\products\\";
      File file = new File(folderImagePath + fileName);
      if (file.exists() && !file.isDirectory()) {
        file.delete();
      }
    }

    if (isDeleted) {
      session.setAttribute("status", "success");
      session.setAttribute("msg", "Deleted successfully");
    } else {
      session.setAttribute("status", "danger");
      session.setAttribute("msg", "An error has occurred");
    }

    String pageCurrent = req.getParameter("page");
    String query = req.getParameter("q");
    String url = req.getContextPath() + "/admin/products?page=" + pageCurrent;
    if (query != null) {
      url += "&q=" + query;
    }
    resp.sendRedirect(url);
  }

  protected void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    String productID = req.getParameter("product_id");
    ProductModel product = productService.getProductById(productID);
    req.setAttribute("product", product);
    req.getRequestDispatcher("/views/admin/product/edit.jsp").forward(req, resp);
  }

  protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    HttpSession session = req.getSession(false);

    int id = req.getParameter("productID") != null ? Integer.parseInt(req.getParameter("productID")) : 0;
    String name = req.getParameter("productName") != null ? req.getParameter("productName") : "";
    String description = req.getParameter("productDescription") != null ? req.getParameter("productDescription") : "";
    double price = req.getParameter("productPrice") != null ? Double.parseDouble(req.getParameter("productPrice")) : 0.0f;
    int quantity = req.getParameter("productQuantity") != null ? Integer.parseInt(req.getParameter("productQuantity")) : 0;
    boolean status = req.getParameter("productStatus") != null;
    int categoryId = req.getParameter("categoryID") != null ? Integer.parseInt(req.getParameter("categoryID")) : 0;

    String image = "";
    String oldImageName = req.getParameter("productOldImage");
    Part newImageFile = req.getPart("productNewImage");
    if (newImageFile.getSize() > 0) {
      String folderImagePath = session.getAttribute("APP_PATH") + "\\web\\assets\\images\\products\\";

      // Xóa file cũ
      File oldImageFile = new File(folderImagePath + oldImageName);
      if (oldImageFile.exists() && !oldImageFile.isDirectory()) {
        oldImageFile.delete();
      }

      // Thêm file mới
      String[] parts = newImageFile.getSubmittedFileName().split("\\.");
      image = parts[0] + "_" + session.getAttribute("currentTime") + "." + parts[1];
      String savePath = folderImagePath + image;

      newImageFile.write(savePath);
    } else {
      image = oldImageName;
    }

    boolean isUpdated = productService.updateProduct(
      new ProductModel(id, name, image, description, price, quantity, status, categoryId)
    );
    if (isUpdated) {
      session.setAttribute("status", "success");
      session.setAttribute("msg", "Updated successfully");
    } else {
      session.setAttribute("status", "danger");
      session.setAttribute("msg", "An error has occurred");
    }

    String pageCurrent = req.getParameter("page");
    String query = req.getParameter("q");
    String url = req.getContextPath() + "/admin/products?page=" + pageCurrent;
    if (query != null) {
      url += "&q=" + query;
    }
    resp.sendRedirect(url);
  }

}
