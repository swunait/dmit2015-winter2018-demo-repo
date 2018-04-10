package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import northwind.entity.Shipper;
import northwind.service.NorthwindService;

@Named("currentShipperController")
@ViewScoped
public class ShipperController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	// The Shipper to create or edit or delete
	private Shipper shipperDetail;		// +getter +setter
	// The primary key value of the shipper being edited
	private int id;						// +getter +setter
	// If editMode is true then shipperDetail is being used for editing, otherwise, it is being used to create a new shipper.
	private boolean editMode = false;	// +getter +setter
	
	// The selected value from the drop down menu
	private Integer selectedRegionId;	// +getter +setter
	
	@PostConstruct 
	public void init() {
		// create a new Shipper for adding a new shipper
		shipperDetail = new Shipper();
	}
	
	public String createShipper() {
		String outcome = null;
		
		try {
			northwindService.addShipper(shipperDetail);
			shipperDetail = new Shipper();
			Messages.addFlashGlobalInfo("Create was successful.");
			outcome = "viewShippers?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Create was not successful.", e.getMessage());
		}
		
		return outcome;
	}
	
	public void findShipperById() {
		if (id > 0) {
			Shipper item  = northwindService.findOneShipper(id);
			if (item == null) {
				Messages.addGlobalError("Bad request. Unknown id {0}.", id);
			} else {
				editMode = true;
				shipperDetail = item;
			}
		}
	}
	
	public String updateShipper() {
		String outcome = null;
		
		try {
			northwindService.updateShipper(shipperDetail);
			shipperDetail = new Shipper();
			Messages.addFlashGlobalInfo("Update was successful.");
			editMode = false;
			outcome = "viewShippers?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalError("Update was not successful. {0}", e.getMessage());
		}

		return outcome;
	}
	
	public String removeShipper() {
		String outcome = null;
		
		try {
			northwindService.deleteShipper(shipperDetail);
			Messages.addFlashGlobalInfo("Remove Shipper {0} was successful", shipperDetail.getShipperID());
			shipperDetail = new Shipper();
			editMode = false;
			outcome = "viewShippers?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalError("Remove was not successful. {0}", e.getMessage());
		}
		
		return outcome;
	}
	
	public String cancel() {
		editMode = false;
		return "viewShippers?faces-redirect=true";
	}
	
	public List<Shipper> retreiveAllShippers() {
		return northwindService.findAllShipper();
	}

	public Shipper getShipperDetail() {
		return shipperDetail;
	}

	public void setShipperDetail(Shipper shipperDetail) {
		this.shipperDetail = shipperDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Integer getSelectedRegionId() {
		return selectedRegionId;
	}

	public void setSelectedRegionId(Integer selectedRegionId) {
		this.selectedRegionId = selectedRegionId;
	}
	
	
}
