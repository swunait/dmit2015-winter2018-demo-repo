package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import northwind.entity.Category;
import northwind.entity.Region;
import northwind.entity.Territory;

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
	
	public void deleteCategory(Category existingCategory) throws Exception {
		existingCategory = entityManager.merge(existingCategory);
		if (existingCategory.getProducts().size() > 0) {
			throw new Exception("A category with products cannot be deleted.");
		}
		entityManager.remove( existingCategory );
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
		// generate a new regionId by adding 1 to the hightest regionId value
		Query currentQuery = entityManager.createQuery("SELECT MAX(r.regionID) + 1 FROM Region r");
		int nextRegionId = (int) currentQuery.getSingleResult();
		newRegion.setRegionID(nextRegionId);
		entityManager.persist(newRegion);
	}
	
	public void updateRegion(Region existingRegion) {
		entityManager.merge( existingRegion );
	}
	
	public void deleteRegion(Region existingRegion) throws Exception {
		existingRegion = entityManager.merge(existingRegion);
		if (existingRegion.getTerritories().size() > 0) {
			throw new Exception("A region with territories cannot deleted");
		}
		entityManager.remove( existingRegion );
	}
	
	public Region findOneRegion(int RegionId) {
		return entityManager.find(Region.class, RegionId);	
	}
	
	public List<Region> findAllRegion() {
		return entityManager.createQuery(
			"FROM Region",Region.class
			).getResultList();
	}

	public void addTerritory(Territory newTerritory) {
		entityManager.persist(newTerritory);
	}
	
	public void updateTerritory(Territory existingTerritory) {
		entityManager.merge( existingTerritory );
	}
	
	public void deleteTerritory(Territory existingTerritory) {
		existingTerritory = entityManager.merge(existingTerritory);
		entityManager.remove( existingTerritory );
	}
	
	public Territory findOneTerritory(String TerritoryId) {
		return entityManager.find(Territory.class, TerritoryId);	
	}
	
	public List<Territory> findAllTerritory() {
		return entityManager.createQuery(
			"FROM Territory",Territory.class
			).getResultList();
	}
	
}
