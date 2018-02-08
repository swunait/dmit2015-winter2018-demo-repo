package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import northwind.entity.Category;
import northwind.entity.Region;

@Stateless	// Mark this class as stateless EJB.
public class NorthwindDatabaseService {

	@Inject
	private EntityManager entityManager;
	
	public void addCategory(Category newCategory) {
		entityManager.persist(newCategory);
	}
	
	public void updateCategory(Category existingCategory) {
		entityManager.merge( existingCategory );
	}
	
	public void deleteCategory(Category existingCategory) {
		entityManager.remove( entityManager.merge(existingCategory) );
	}
	
	public Category findOneCategory(int categoryId) {
		return entityManager.find(Category.class, categoryId);	
	}
	
	public List<Category> findAllCategory() {
		return entityManager.createQuery(
				"SELECT c FROM Category c ORDER BY c.categoryName", Category.class
				).getResultList();
	}
	
	
	public void addRegion(Region newRegion) {
		entityManager.persist(newRegion);
	}
	
	public void updateRegion(Region existingRegion) {
		entityManager.merge( existingRegion );
	}
	
	public void deleteRegion(Region existingRegion) {
		entityManager.remove( entityManager.merge(existingRegion) );
	}
	
	public Region findOneRegion(int RegionId) {
		return entityManager.find(Region.class, RegionId);	
	}
	
	public List<Region> findAllRegion() {
		return entityManager.createQuery(
			"SELECT r FROM Region r",Region.class
			).getResultList();
	}
}
