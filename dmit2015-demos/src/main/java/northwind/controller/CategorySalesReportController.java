package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import northwind.report.CategorySales;
import northwind.service.NorthwindDatabaseService;

@Named
@ViewScoped
public class CategorySalesReportController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private NorthwindDatabaseService northwindService;
	
	private List<CategorySales> categorySales;
	private Integer selectedYear;
	private String reportTitle;
	
	public List<CategorySales> getCategorySales() {
		return categorySales;
	}
	public Integer getSelectedYear() {
		return selectedYear;
	}
	public void setSelectedYear(Integer selectedYear) {
		this.selectedYear = selectedYear;
	}	

	public String getReportTitle() {
		return reportTitle;
	}
	@PostConstruct
	void init() {
		categorySales = northwindService.findCategorySales();
		reportTitle = "Category Sales for all years";
	}

	public List<Integer> retreiveYears() {
		return northwindService.findYearsWithOrders();
	}
	
	public void createReport() {
		categorySales = northwindService.findCategorySalesForYear(selectedYear);
		reportTitle = selectedYear + " Category Sales";
	}
}
