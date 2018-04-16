package northwind.rest;

import static org.junit.Assert.*;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.junit.Before;
import org.junit.Test;

import northwind.entity.Category;
import northwind.entity.Region;
import northwind.entity.Territory;

public class NorthwindRESTServiceTest {

	final String BASE_URI = "https://localhost:8443/dmit2015-demos/rest/northwind/";

	// http://adambien.blog/roller/abien/entry/jax_rs_client_javax_net
	// https://docs.oracle.com/javase/tutorial/security/toolsign/rstep2.html
	private Client client;

	@Before
	public void init() throws KeyManagementException, NoSuchAlgorithmException {
		TrustManager[] noopTrustManager = new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		SSLContext sc = SSLContext.getInstance("ssl");
		sc.init(null, noopTrustManager, null);

		this.client = ClientBuilder.newBuilder().sslContext(sc).build();
		
		this.client = this.client.register(new BasicAuthentication("admin01", "Password2015"));
		
//		this.client = ClientBuilder.newBuilder().sslContext(sc).build().register(new Authenticator("admin01", "Password2015"));
		
	}
	
	@Test
	public void testRegionCRUD() {
		
		// create a new Region 
		Region newRegion = new Region();
		newRegion.setRegionDescription("JUnit Test Region");
		
		WebTarget resource = client.target(BASE_URI).path("regions");
		
		// call the web service method to create a new region entity
		Response response = resource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newRegion, MediaType.APPLICATION_JSON) );
		assertEquals(200, response.getStatus());
		// Read the regionId value included in the response
		Integer regionId = response.readEntity(Integer.class);
		response.close();
		
		// call the web service method to get the entity we just added 
		Region addedRegion = resource.path("{id}").resolveTemplate("id", regionId).request().get(Region.class);
		assertNotNull(addedRegion);
		assertEquals(regionId.intValue(), addedRegion.getRegionID());
		assertEquals(newRegion.getRegionDescription(), addedRegion.getRegionDescription());
		
		// change the region name and description with new values
		addedRegion.setRegionDescription("UPDATED JUnit Test Region");
		// call the web service method to update the region entity
		response = resource.request(MediaType.APPLICATION_JSON).put( Entity.json(addedRegion) );
		assertEquals(200, response.getStatus());
		response.close();
		
		// call the web service method to get the entity we just added 
		Region updatedRegion = resource.path("{id}").resolveTemplate("id", regionId).request().get(Region.class);
		assertNotNull(updatedRegion);
		assertEquals(addedRegion.getRegionID(), updatedRegion.getRegionID());
		assertEquals(addedRegion.getRegionDescription(), updatedRegion.getRegionDescription());
		
		// call the RESTful web service to delete the region entity
		response = resource.path("{id}").resolveTemplate("id", regionId).request(MediaType.APPLICATION_JSON).delete();
		assertEquals(200, response.getStatus());
		response.close();		
		
	}
	
	@Test
	public void testTerritoryCRUD() {
		
		// create a new Territory 
		Territory newTerritory = new Territory();
		String newTerritoryId = "JUNIT01";
		newTerritory.setTerritoryID(newTerritoryId);
		newTerritory.setTerritoryDescription("JUnit Test Territory");
		
		WebTarget regionResource = client.target(BASE_URI).path("regions");
		// call the web service method to get a Region with a regionId of 3
		Region northernRegion = regionResource.path("{id}").resolveTemplate("id", 3).request().get(Region.class);
		assertNotNull(northernRegion);
		newTerritory.setRegion(northernRegion);
				
		WebTarget territoryResource = client.target(BASE_URI).path("territories");
		
		// call the web service method to create a new Territory entity
		Response response = territoryResource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newTerritory, MediaType.APPLICATION_JSON) );
		assertEquals(200, response.getStatus());
		response.close();
		
		// call the web service method to get the entity we just added 
		Territory addedTerritory = territoryResource.path("{id}").resolveTemplate("id", newTerritoryId).request().get(Territory.class);
		assertNotNull(addedTerritory);
		assertEquals(newTerritoryId, addedTerritory.getTerritoryID());
		assertEquals(newTerritory.getTerritoryDescription(), addedTerritory.getTerritoryDescription());
		assertEquals(northernRegion.getRegionID(), newTerritory.getRegion().getRegionID());
		
		// change the region name and description with new values
		addedTerritory.setTerritoryDescription("UPDATED JUnit Test Territory");
		// call the web service method to update the region entity
		response = territoryResource.request(MediaType.APPLICATION_JSON).put( Entity.json(addedTerritory) );
		assertEquals(200, response.getStatus());
		response.close();
		
		// call the web service method to get the entity we just added 
		Territory updatedTerritory = territoryResource.path("{id}").resolveTemplate("id", newTerritoryId).request().get(Territory.class);
		assertNotNull(updatedTerritory);
		assertEquals(addedTerritory.getTerritoryID(), updatedTerritory.getTerritoryID());
		assertEquals(addedTerritory.getTerritoryDescription(), updatedTerritory.getTerritoryDescription());
		
		// call the RESTful web service to delete the region entity
		response = territoryResource.path("{id}").resolveTemplate("id", newTerritoryId).request(MediaType.APPLICATION_JSON).delete();
		assertEquals(200, response.getStatus());
		response.close();		
		
	}

	@Test
	public void testCategoryCRUD() {
		
		// create a new Category 
		Category newCategory = new Category();
		newCategory.setCategoryName("Deals");
		newCategory.setDescription("Discounted Items for Sale");
		
		WebTarget resource = client.target(BASE_URI).path("categories");
		
		// call the web service method to create a new category entity
		Response response = resource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newCategory, MediaType.APPLICATION_JSON) );
		assertEquals(200, response.getStatus());
		// Read the categoryId value included in the response
		Integer categoryId = response.readEntity(Integer.class);
		response.close();
		
		// call the web service method to get the entity we just added 
		Category addedCategory = resource.path("{id}").resolveTemplate("id", categoryId).request().get(Category.class);
		assertNotNull(addedCategory);
		assertEquals(categoryId.intValue(), addedCategory.getCategoryID());
		assertEquals(newCategory.getCategoryName(), addedCategory.getCategoryName());
		assertEquals(newCategory.getDescription(), addedCategory.getDescription());
		
		// change the category name and description with new values
		addedCategory.setCategoryName("Hot Deals");
		addedCategory.setDescription("Post the hot deals you find here! This forum is not for private sales or self promotion.");
		// call the web service method to update the category entity
		response = resource.request(MediaType.APPLICATION_JSON).put( Entity.json(addedCategory));
		assertEquals(200, response.getStatus());
		response.close();
		
		// call the web service method to get the entity we just added 
		Category updatedCategory = resource.path("{id}").resolveTemplate("id", categoryId).request().get(Category.class);
		assertNotNull(updatedCategory);
		assertEquals(addedCategory.getCategoryID(), updatedCategory.getCategoryID());
		assertEquals(addedCategory.getCategoryName(), updatedCategory.getCategoryName());
		assertEquals(addedCategory.getDescription(), updatedCategory.getDescription());
		
		// call the RESTful web service to delete the category entity
		response = resource.path("{id}").resolveTemplate("id", categoryId).request(MediaType.APPLICATION_JSON).delete();
		assertEquals(200, response.getStatus());
		response.close();		
		
	}


	@Test
	public void testDuplicateCategoryName() {
		Category newCategory = new Category();
		newCategory.setCategoryName("Seafood");
		newCategory.setDescription("This category name is a duplicate");
		
		WebTarget resource = client.target(BASE_URI).path("categories");
		
		// call the web service method to create a new category entity
		Response response = resource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newCategory, MediaType.APPLICATION_JSON) );
		assertEquals("Unexpected response status", 409, response.getStatus());
		Map<String, String> errorMap = response.readEntity(Map.class);
		assertNotNull("response.readEntity() should not null", errorMap);
		assertEquals("Unexpected response.readEntity(). It contains" + errorMap, 1, errorMap.size());
		errorMap.forEach(
			(errorName, errorMessage) -> System.out.println(errorName + ": " + errorMessage + " ")
		);
		
		// Read the categoryId value included in the response
		response.close();
	}
	
	@Test
	public void testInvalidCategoryName() {
		Category newCategory = new Category();
		newCategory.setCategoryName("");
		newCategory.setDescription("This category name is invalid");
		
		WebTarget resource = client.target(BASE_URI).path("categories");
		
		// call the web service method to create a new category entity
		Response response = resource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newCategory, MediaType.APPLICATION_JSON) );
		assertEquals("Unexpected response status", 400, response.getStatus());
		Map<String, String> errorMap =  response.readEntity(Map.class);
		assertNotNull("response.readEntity() should not null", errorMap);
		assertEquals("Unexpected response.readEntity(). It contains" + errorMap, 1, errorMap.size());
		errorMap.forEach(
				(errorName, errorMessage) -> System.out.println(errorName + ": " + errorMessage + " ")
			);
		
		// Read the categoryId value included in the response
		response.close();
	}
}
