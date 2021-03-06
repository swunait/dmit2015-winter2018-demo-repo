package northwind.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
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
import northwind.entity.Region;
import northwind.entity.Territory;
import northwind.service.NorthwindService;

@Path("northwind")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class NorthwindRESTService {

	@Inject
	private Logger log;

	@Inject
	private Validator validator;

	@Inject
	private NorthwindService northwindService;
	
	@Path("regions")
	@GET
	public Response findAllRegion() {
		Response.ResponseBuilder builder = null;
		try {
			List<Region> regions = northwindService.findAllRegion();
			builder = Response.ok().entity(regions);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("regions/{id}")
	@GET
	public Response findOneRegionById(@PathParam("id") int regionId) {
		Response.ResponseBuilder builder = null;
		try {
			Region existingRegion = northwindService.findOneRegion(regionId);
			builder = Response.ok().entity(existingRegion);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("regions")
	@POST
	public Response createRegion(Region newRegion) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.addRegion(newRegion);
			// Send the generated regionId value to the response
			builder = Response.ok().entity(newRegion.getRegionID());
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("regions")
	@PUT
	public Response updateRegion(Region existingRegion) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.updateRegion(existingRegion);
			builder = Response.ok();
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("regions/{id}")
	@DELETE
	public Response deleteRegion(@PathParam("id") int regionId) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.deleteRegionById(regionId);
			builder = Response.ok();
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}
	
	
	@Path("territories")
	@GET
	public Response findAllTerritory() {
		Response.ResponseBuilder builder = null;
		try {
			List<Territory> territories = northwindService.findAllTerritory();
			builder = Response.ok().entity(territories);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("territories/{id}")
	@GET
	public Response findOneTerritoryById(@PathParam("id") String territoryId) {
		Response.ResponseBuilder builder = null;
		try {
			Territory existingTerritory = northwindService.findOneTerritory(territoryId);
			builder = Response.ok().entity(existingTerritory);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("territories")
	@POST
	public Response createTerritory(Territory newTerritory) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.addTerritory(newTerritory);
			builder = Response.ok();
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("territories")
	@PUT
	public Response updateTerritory(Territory existingTerritory) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.updateTerritory(existingTerritory);
			builder = Response.ok();
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("territories/{id}")
	@DELETE
	public Response deleteTerritory(@PathParam("id") String territoryId) {
		Response.ResponseBuilder builder = null;
		try {
			northwindService.deleteTerritoryById(territoryId);
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
	@GET
	public Response findAllCategory() {
		Response.ResponseBuilder builder = null;
		try {
			List<Category> categories = northwindService.findAllCategory();
			// Create an "ok" response
			builder = Response.ok(categories);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("categories/{id}")
	@GET
	public Response findOneCategoryById(@PathParam("id") int categoryId) {
		Response.ResponseBuilder builder = null;
		try {
			Category existingCategory = northwindService.findOneCategory(categoryId);
			// Create an "ok" response
			builder = Response.ok(existingCategory);
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	@Path("categories")
	@POST
	public Response createCategory(Category newCategory) {
		Response.ResponseBuilder builder = null;
		try {
			// Validates newCategory using bean validation
			validateCategory(newCategory);

			northwindService.addCategory(newCategory);
			// Create an "ok" response and send the generated categoryId
			builder = Response.ok(newCategory.getCategoryID());
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (ValidationException e) {
			// Handle the unique constrain violation
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("categoryName", "Category Name already exists");
			builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
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

	/**
	 * <p>
	 * Validates the given Category variable and throws validation exceptions based on
	 * the type of error. If the error is standard bean validation errors then it
	 * will throw a ConstraintValidationException with the set of the constraints
	 * violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing member with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 * 
	 * @param currentCategory
	 *            Category to be validated
	 * @throws ConstraintViolationException
	 *             If Bean Validation errors exist
	 * @throws ValidationException
	 *             If currentCategory with the same categoryName already exists
	 */
	private void validateCategory(Category currentCategory) throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<Category>> violations = validator.validate(currentCategory);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}

		// Check the uniqueness of the categoryName
		if (categoryNameAlreadyExists(currentCategory.getCategoryName())) {
			throw new ValidationException("Unique CategoryName Violation");
		}
	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}

	/**
	 * Checks if a member with the same email address is already registered. This is
	 * the only way to easily capture the "@UniqueConstraint(columnNames = "email")"
	 * constraint from the Member class.
	 * 
	 * @param email
	 *            The email to check
	 * @return True if the email already exists, and false otherwise
	 */
	public boolean categoryNameAlreadyExists(String categoryName) {
		Category currentCategory = null;
		try {
			currentCategory = northwindService.findOneCategoryByCategoryName(categoryName);
		} catch (NoResultException e) {
			// ignore
		}
		return currentCategory != null;
	}
}
