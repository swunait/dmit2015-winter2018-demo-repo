package northwind.rest;

import static org.junit.Assert.*;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import northwind.entity.Category;

public class NorthwindRESTServiceTest {

	final String BASE_URI = "https://localhost:8443/dmit2015-demos/rest/webapi/";

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
		
//		this.client = this.client.register(new BasicAuthentication("admin01", "Password2015"));
		
//		this.client = ClientBuilder.newBuilder().sslContext(sc).build().register(new Authenticator("admin01", "Password2015"));
		
	}

	@Test
	public void testCategoryCRUD() {
		
		// create a new Category entity
		Category newCategory = new Category();
		newCategory.setCategoryName("Deals");
		newCategory.setDescription("Discounted Items for Sale");
		
		WebTarget resource = client.target(BASE_URI).path("categories");
		
		// call the RESTful web service to create a new category entity
		Response response = resource.request(MediaType.APPLICATION_JSON).post( Entity.entity(newCategory, MediaType.APPLICATION_JSON) );
		assertEquals(200, response.getStatus());
		response.close();
		
		// call the RESTful web service to retrieve all categories
		GenericType<List<Category>> responseType = new GenericType<List<Category>>() {};
		List<Category> categories = resource.request(MediaType.APPLICATION_JSON).get(responseType);
		assertNotNull(categories);
		assertTrue(categories.size() > 0);
		// assuming that the category name is unique we can use a Java 8 lamba expression to find the category we just added
		Optional<Category> optionalExistingCategory = categories.stream().filter( cat -> cat.getCategoryName().equalsIgnoreCase("Deals")).findFirst();
		assertTrue( optionalExistingCategory.isPresent() );
		// change the category name and description with new values
		Category existingCategory = optionalExistingCategory.get();
		existingCategory.setCategoryName("Hot Deals");
		existingCategory.setDescription("Post the hot deals you find here! This forum is not for private sales or self promotion.");
		// call the RESTful web service to update the category entity
		response = resource.request(MediaType.APPLICATION_JSON).put( Entity.json(existingCategory));
		assertEquals(200, response.getStatus());
		response.close();
		// call the RESTful web service to retrieve the category we added earlier
		Category updatedCategory = resource.path("{id}").resolveTemplate("id", existingCategory.getCategoryID()).request().get(Category.class);
		assertNotNull(updatedCategory);
		assertEquals(existingCategory.getCategoryID(), updatedCategory.getCategoryID());
		assertEquals(existingCategory.getCategoryName(), updatedCategory.getCategoryName());
		assertEquals(existingCategory.getDescription(), updatedCategory.getDescription());
		
		// call the RESTful web service to delete the category entity
		response = resource.path("{id}").resolveTemplate("id", updatedCategory.getCategoryID()).request(MediaType.APPLICATION_JSON).delete();
		assertEquals(200, response.getStatus());
		response.close();		
		
	}

}
