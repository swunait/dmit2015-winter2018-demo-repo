package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.omnifaces.util.Messages;

import northwind.entity.Customer;
import northwind.entity.Order;
import northwind.service.NorthwindService;

@Named
@ViewScoped
public class OrderQueryController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private NorthwindService northwindService;
	
	@NotNull(message="Order ID value is required")
	private Integer queryOrderIdValue;					// +getter +setter
	private Order querySingleResult;					// +getter
	private List<Order> queryResultList;				// +getter
	
	@NotBlank(message="You must choose a customer")
	private String selectedCustomerId;					// +getter +setter
	
	public Integer getQueryOrderIdValue() {
		return queryOrderIdValue;
	}
	public void setQueryOrderIdValue(Integer queryOrderIdValue) {
		this.queryOrderIdValue = queryOrderIdValue;
	}
	public Order getQuerySingleResult() {
		return querySingleResult;
	}
	public List<Order> getQueryResultList() {
		return queryResultList;
	}
	public String getSelectedCustomerId() {
		return selectedCustomerId;
	}
	public void setSelectedCustomerId(String selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}
	
	
	public void findOrderByOrderId() {
		try {
			querySingleResult = northwindService.findOneOrderWithDetailsByOrderId(queryOrderIdValue);
			if( querySingleResult == null) {
				Messages.addGlobalWarn("Unknown orderId \"{0}\". We found 0 results", queryOrderIdValue);
			} else {
				Messages.addGlobalInfo("Found 1 result.");
			}			
		} catch (Exception e) {
			Messages.addGlobalError("Unable to perform search.");
		}
	}
	
	public void findOrderByOrderId(Integer orderId) {
		queryOrderIdValue = orderId;
		findOrderByOrderId();
	}
	
	public double getInvoiceTotal() {
		return querySingleResult.getOrderDetails().stream().mapToDouble( 
				od -> od.getQuantity() * od.getUnitPrice().doubleValue() * (1 - od.getDiscount()) 
				).sum()
				+ querySingleResult.getFreight().doubleValue();
	}
	
	public List<Customer> retrieveAllCustomers() {
		return northwindService.findAllCustomer();
	}
	
	public void findOrdersByCustomer() {
		try {
			queryResultList = northwindService.findAllOrderByCustomerId(selectedCustomerId);
			if( queryResultList == null || queryResultList.size() == 0) {
				Messages.addGlobalWarn("Unknown customerId \"{0}\". We found 0 results", queryOrderIdValue);
			} else {
				Messages.addGlobalInfo("Found {0} result(s).", queryResultList.size());
			}			
		} catch (Exception e) {
			Messages.addGlobalError("Unable to perform search.");
		}
	}
	
	public void cancel() {
		queryOrderIdValue = null;
		queryResultList = null;
		querySingleResult = null;
	}

}
