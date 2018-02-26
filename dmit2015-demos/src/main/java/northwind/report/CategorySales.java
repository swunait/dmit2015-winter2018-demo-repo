package northwind.report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CategorySales {
	
	private String categoryName;
	private BigDecimal salesTotal;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}	
	public BigDecimal getSalesTotal() {
		return salesTotal;
	}
	public void setSalesTotal(BigDecimal salesTotal) {
		this.salesTotal = salesTotal;
	}
	
	public CategorySales(String categoryName, BigDecimal salesTotal) {
		this.categoryName = categoryName;
		this.salesTotal = salesTotal.setScale(2, RoundingMode.HALF_UP);
	}
	
	public CategorySales(String categoryName, double salesTotal) {
		this.categoryName = categoryName;
		this.salesTotal = BigDecimal.valueOf(salesTotal).setScale(2, RoundingMode.HALF_UP);
	}
	
}
