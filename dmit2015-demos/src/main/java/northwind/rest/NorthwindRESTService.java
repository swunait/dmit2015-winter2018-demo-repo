package northwind.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import northwind.entity.Category;
import northwind.service.NorthwindService;

@Path("webapi")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class NorthwindRESTService {
	
	@Inject
	private NorthwindService northwindService;
	
	@Path("categories")
	@GET
	public List<Category> findAllCategory() {
		return northwindService.findAllCategory();
	}
	
	@Path("categories/{id}")
	@GET
	public Category findOneCategoryById(@PathParam("id") int categoryId) {
		return northwindService.findOneCategory(categoryId);
	}
	
	@Path("categories")
	@POST
	public Response createCategory(Category newCategory) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.addCategory(newCategory);
            // Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }		
		return builder.build();
	}
	
	@Path("categories")
	@PUT
	public Response updateCategory(Category existingCategory) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.updateCategory(existingCategory);
            // Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }		
		return builder.build();
	}
	
	@Path("categories/{id}")
	@DELETE
	public Response deleteCategory(@PathParam("id") int categoryId) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.deleteCategory(categoryId);
            // Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }		
		return builder.build();
	}
}
