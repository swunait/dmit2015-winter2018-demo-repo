package northwind.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import dmit2015.util.Resources;
import northwind.entity.Category;

@RunWith(Arquillian.class)
public class NorthwindDatabaseServiceTest {
	
	@Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(Category.class, NorthwindDatabaseService.class, Resources.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
                // Deploy our test datasource
//                .addAsWebInfResource("test-ds.xml");
    }

	@Inject
	private NorthwindDatabaseService northwindDB;
	
	@Test
	public void testFindAllCategory() {
		List<Category> categories = northwindDB.findAllCategory();
		// There should be 8 categories in total
		assertEquals(10, categories.size());
	}
	
	@Test
	public void testFindCategory() {
		Category category1 = northwindDB.findOneCategory(1);
		assertNotNull(category1);
		assertEquals("Beverages", category1.getCategoryName());
	}

	@Test 
	public void testCreateUpdateDelete() {
		Category newCategory = new Category();
		newCategory.setCategoryName("Drugs");
		northwindDB.createCategory(newCategory);
		Category existingCategory = northwindDB.findOneCategory(newCategory.getCategoryID());
		assertNotNull(existingCategory);
		assertEquals("Drugs", existingCategory.getCategoryName());
		existingCategory.setCategoryName("Medical Drugs");
		northwindDB.updateCategory(existingCategory);
		Category updatedCategory = northwindDB.findOneCategory(existingCategory.getCategoryID());
		assertEquals("Medical Drugs", updatedCategory.getCategoryName());
		northwindDB.removeCategory(updatedCategory);
		assertEquals(10, northwindDB.findAllCategory().size());
	}
}
