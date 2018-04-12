package northwind.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import northwind.entity.Category;
import northwind.entity.Customer;
import northwind.entity.Order;
import northwind.entity.Region;
import northwind.entity.Shipper;
import northwind.entity.Territory;
import northwind.report.CategorySales;

@Stateless	// Mark this class as stateless EJB.
//@Interceptors({NorthwindSecurityInterceptor.class})
public class NorthwindService {

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
	
	public void deleteCategory(int categoryId) throws Exception {
		Category existingCategory = findOneCategory(categoryId);
		deleteCategory(existingCategory);
	}
	
	public Category findOneCategory(int categoryId) {
		return entityManager.find(Category.class, categoryId);	
	}
	
	public Category findOneCategoryByCategoryName(String categoryName) {
		Category querySingleResult = null;
		try {
			querySingleResult =  (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryNameValue")
					.setParameter("categoryNameValue", categoryName)
					.getSingleResult();
		} catch(NoResultException e) {
			querySingleResult = null;
		}
		return querySingleResult;
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
	
	public void addShipper(Shipper newShipper) {
		entityManager.persist(newShipper);
	}
	
	public void updateShipper(Shipper existingShipper) {
		entityManager.merge( existingShipper );
	}
	
	public void deleteShipper(Shipper existingShipper) {
		existingShipper = entityManager.merge(existingShipper);
		entityManager.remove( existingShipper );
	}
	
	public Shipper findOneShipper(int ShipperId) {
		return entityManager.find(Shipper.class, ShipperId);	
	}
	
	public List<Shipper> findAllShipper() {
		return entityManager.createQuery(
			"FROM Shipper",Shipper.class
			).getResultList();
	}
	
	
	

	public List<Integer> findYearsWithOrders() {
		return entityManager.createQuery(
			"SELECT YEAR(o.shippedDate) "
			+ "FROM Order o "
			+ "WHERE YEAR(o.shippedDate) IS NOT NULL "
			+ "GROUP BY YEAR(o.shippedDate) "
			+ "ORDER BY YEAR(o.shippedDate) "
			, Integer.class)
			.getResultList();
	}
	
	public List<CategorySales> findCategorySales() {
		return entityManager.createQuery(
			"SELECT new northwind.report.CategorySales(c.categoryName, SUM(od.unitPrice * od.quantity * (1 - od.discount)) ) "
			+ " FROM OrderDetail od, IN (od.product) p, IN (p.category) c, IN (od.order) o "
			+ " GROUP BY c.categoryID",
			CategorySales.class)
			.getResultList();
	}
	
	public List<CategorySales> findCategorySalesForYear(Integer year) {
		if (year == null) {
			return findCategorySales();
		}
		return entityManager.createQuery(
			"SELECT new northwind.report.CategorySales(c.categoryName, SUM(od.unitPrice * od.quantity * (1 - od.discount)) ) "
			+ " FROM OrderDetail od, IN (od.product) p, IN (p.category) c, IN (od.order) o "
			+ " WHERE YEAR(o.shippedDate) = :yearValue "
			+ " GROUP BY c.categoryID",
			CategorySales.class)
			.setParameter("yearValue", year)
			.getResultList();
	}

	

	public Order findOneOrderWithDetailsByOrderId(int orderId) {
		Order querySingleResult = null;
		try {
			querySingleResult = entityManager.createQuery(
				"SELECT o FROM Order o JOIN FETCH o.orderDetails WHERE o.orderID = :orderIdValue", 
				Order.class)
				.setParameter("orderIdValue", orderId)
				.getSingleResult();
		} catch(NoResultException e) {
			querySingleResult = null;
		}
		return querySingleResult;
	}
	
	public List<Customer> findAllCustomer() {
		return entityManager.createQuery("SELECT c FROM Customer c ORDER BY c.companyName", Customer.class).getResultList();
	}
	
	public List<Order> findAllOrderByCustomerId(String customerId) {
		return entityManager.createQuery(
			"SELECT o FROM Order o WHERE o.customer.customerID = :customerIdValue", 
			Order.class)
			.setParameter("customerIdValue", customerId)
			.getResultList();
	}
}
