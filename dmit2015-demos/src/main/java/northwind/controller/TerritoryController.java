package northwind.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import northwind.entity.Region;
import northwind.entity.Territory;
import northwind.service.NorthwindDatabaseService;

@Named("currentTerritoryController")
@ViewScoped
public class TerritoryController implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NorthwindDatabaseService northwindService;
	
	// The Territory to create or edit or delete
	private Territory territoryDetail;		// +getter +setter
	// The primary key value of the territory being edited
	private String id;						// +getter +setter
	// If editMode is true then territoryDetail is being used for editing, otherwise, it is being used to create a new territory.
	private boolean editMode = false;	// +getter +setter
	
	// The selected value from the drop down menu
	private Integer selectedRegionId;	// +getter +setter
	
	@PostConstruct 
	public void init() {
		// create a new Territory for adding a new territory
		territoryDetail = new Territory();
	}
	
	public String createTerritory() {
		String outcome = null;
		
		try {
			if (selectedRegionId != null && selectedRegionId > 0) {
				Region selectedRegion = northwindService.findOneRegion(selectedRegionId);
				territoryDetail.setRegion(selectedRegion);
			}
			
			northwindService.addTerritory(territoryDetail);
			territoryDetail = new Territory();
			Messages.addFlashGlobalInfo("Create was successful.");
			outcome = "viewTerritories?faces-redirect=true";
		} catch(Exception e) {
			Messages.addGlobalError("Create was not successful.", e.getMessage());
		}
		
		return outcome;
	}
	
	public void findTerritoryById() {
		if (id != null && !id.isEmpty() ) {
			Territory item  = northwindService.findOneTerritory(id);
			if (item == null) {
				Messages.addGlobalError("Bad request. Unknown id {0}.", id);
			} else {
				editMode = true;
				territoryDetail = item;
				// selected the selected regionId for the drop down menu
				selectedRegionId = territoryDetail.getRegion().getRegionID();
			}
		}
	}
	
	public String updateTerritory() {
		String outcome = null;
		
		try {
			if (selectedRegionId != null && selectedRegionId > 0) {
				Region selectedRegion = northwindService.findOneRegion(selectedRegionId);
				territoryDetail.setRegion(selectedRegion);
			}

			northwindService.updateTerritory(territoryDetail);
			territoryDetail = new Territory();
			Messages.addFlashGlobalInfo("Update was successful.");
			editMode = false;
			outcome = "viewTerritories?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalError("Update was not successful. {0}", e.getMessage());
		}

		return outcome;
	}
	
	public String removeTerritory() {
		String outcome = null;
		
		try {
			northwindService.deleteTerritory(territoryDetail);
			territoryDetail = new Territory();
			Messages.addFlashGlobalInfo("Remove Territory {0} was successful", territoryDetail.getTerritoryID());
			editMode = false;
			outcome = "viewTerritories?faces-redirect=true";
		} catch (Exception e) {
			Messages.addFlashGlobalError("Remove was not successful. {0}", e.getMessage());
		}
		
		return outcome;
	}
	
	public String cancel() {
		editMode = false;
		return "viewTerritories?faces-redirect=true";
	}
	
	public List<Territory> retreiveAllTerritories() {
		return northwindService.findAllTerritory();
	}

	public List<Region> retreiveAllRegions() {
		return northwindService.findAllRegion();
	}

	public Territory getTerritoryDetail() {
		return territoryDetail;
	}

	public void setTerritoryDetail(Territory territoryDetail) {
		this.territoryDetail = territoryDetail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
