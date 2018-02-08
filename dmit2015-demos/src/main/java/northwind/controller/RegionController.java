package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import northwind.entity.Region;
import northwind.service.NorthwindDatabaseService;

@Named("currentRegionController")
@ViewScoped
public class RegionController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindDatabaseService nortwindService;
	
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
	
	public void createRegion(ActionEvent event) {
		try {
			nortwindService.addRegion(regionDetail);
			regionDetail = new Region();
			Messages.addFlashGlobalInfo("Create region was successful.");
		} catch(Exception e) {
			Messages.addGlobalError("Create region was not successful.", e.getMessage());
		}
	}
	
	public void findRegionById() {
		if (id > 0 ) {
			Region item  = nortwindService.findOneRegion(id);
			if (item == null) {
				Messages.addGlobalError("Bad request. Unknown region id {}.", id);
			}
			editMode = true;
			regionDetail = item;			
		}
	}
	
	public String updateRegion() {
		nortwindService.updateRegion(regionDetail);
		regionDetail = new Region();
		Messages.addFlashGlobalInfo("Update Region was successful.");
		editMode = false;
		return "/northwind/viewRegions?faces-redirect=true";
	}
	
	public String removeRegion() {
		nortwindService.deleteRegion(regionDetail);
		regionDetail = new Region();
		Messages.addFlashGlobalInfo("Remove Region {0} was successful", regionDetail.getRegionID());
		editMode = false;
		return "/northwind/viewRegions?faces-redirect=true";
	}
	
	public String cancel() {
		editMode = false;
		return "/northwind/viewRegions?faces-redirect=true";
	}
	
	public List<Region> retreiveAllRegions() {
		return nortwindService.findAllRegion();
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
