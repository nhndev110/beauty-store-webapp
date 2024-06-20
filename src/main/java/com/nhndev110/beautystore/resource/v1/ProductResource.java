package com.nhndev110.beautystore.resource.v1;

import com.nhndev110.beautystore.dto.ProductDTO;
import com.nhndev110.beautystore.model.ProductModel;
import com.nhndev110.beautystore.service.impl.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/v1/products")
public class ProductResource {

  @Inject
  private ProductService productService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/")
  public Response getAll() {
    List<ProductDTO> products = productService.getProducts();
    return Response.ok(products).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response getOne(@PathParam("id") String id) {
    ProductModel product = productService.getProductById(id);
    if (product != null) {
      return Response.ok(product).build();
    } else {
      return Response.status(Response.Status.NO_CONTENT).build();
    }
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/")
  public Response storeOne(ProductModel product) {
    ProductDTO productDTO = productService.insertProduct(product);
    if (product != null) {
      return Response.ok(productDTO).build();
    } else {
      return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
  }

}
