package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBAccessException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import northwind.entity.Region;
import northwind.service.NorthwindService;

@Named("currentRegionController")
@ViewScoped
public class RegionController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindService northwindService;
	
	// The Region to create or edit or delete
	private Region regionDetail;		// +getter +setter
	// The primary key value of the region being edited
	private int id;						// +getter +setter
	// If editMode is true then regionDetail is being used for editing, otherwise, it is being used to create a new region.
	private boolean editMode = false;	// +getter +setter
	
	@PostConstruct 
	public void init() {
		// create a new Region for adding a new region
		regionDetail = new Region();
	}
	
	public String createRegion() {
		String outcome = null;
		
		try {
			northwindService.addRegion(regionDetail);
			regionDetail = new Region();
			Messages.addFlashGlobalInfo("Create was successful.");
			outcome = "viewRegions?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalError(e.getMessage());
		} catch(Exception e) {
			Messages.addGlobalError("Create was not successful.");
		}
		
		return outcome;
	}
	
	public void findRegionById() {
		if (id > 0 ) {
			Region item  = northwindService.findOneRegion(id);
			if (item == null) {
				Messages.addGlobalError("Bad request. Unknown id {0}.", id);
			} else {
				editMode = true;
				regionDetail = item;							
			}
		}
	}
	
	public String updateRegion() {
		String outcome = null;
		
		try {
			northwindService.updateRegion(regionDetail);
			regionDetail = new Region();
			Messages.addFlashGlobalInfo("Update was successful.");
			editMode = false;
			outcome = "viewRegions?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalError(e.getMessage());
		} catch(Exception e) {
			Messages.addGlobalError("Update was not successful.");
		}

		return outcome;
	}
	
	public String removeRegion() {
		String outcome = null;
		
		try {
			northwindService.deleteRegion(regionDetail);
			Messages.addFlashGlobalInfo("Remove was successful");
			regionDetail = new Region();
			editMode = false;
			outcome = "viewRegions?faces-redirect=true";
		} catch(EJBAccessException e) {
			Messages.addGlobalError(e.getMessage());
		} catch(Exception e) {
			Messages.addGlobalError("Remove was not successful.");
		}
		
		return outcome;
	}
	
	public String cancel() {
		editMode = false;
		return "viewRegions?faces-redirect=true";
	}
	
	public List<Region> retreiveAllRegions() {
		return northwindService.findAllRegion();
	}

	public Region getRegionDetail() {
		return regionDetail;
	}

	public void setRegionDetail(Region regionDetail) {
		this.regionDetail = regionDetail;
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
}
