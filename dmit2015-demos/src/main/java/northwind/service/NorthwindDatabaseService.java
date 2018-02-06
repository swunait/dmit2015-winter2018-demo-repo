package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import northwind.entity.Category;

@Stateless	// Mark this class as stateless EJB.
public class NorthwindDatabaseService {

	@Inject
	private EntityManager entityManager;
	
	public void createCategory(Category newCategory) {
		entityManager.persist(newCategory);
	}
	
	public void updateCategory(Category existingCategory) {
		entityManager.merge( existingCategory );
		entityManager.flush();
	}
	
	public void removeCategory(Category existingCategory) {
		entityManager.remove( entityManager.merge(existingCategory) );
	}
	
	public Category findOneCategory(int categoryId) {
		return entityManager.find(Category.class, categoryId);	// will throw an exception if no results returned
	}
	
	public List<Category> findAllCategory() {
		return entityManager.createQuery(
				"SELECT c FROM Category c ORDER BY c.categoryName", Category.class).getResultList();
	}
	
}
