package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import northwind.entity.Category;
import northwind.service.NorthwindDatabaseService;

@Named
@ViewScoped
public class ViewAllCategoryController implements Serializable {

	@Inject
	private NorthwindDatabaseService northwindDB;
	
	public List<Category> retrieveAllCategory() {
		return northwindDB.findAllCategory();
	}
}
