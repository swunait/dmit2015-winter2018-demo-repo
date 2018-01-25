package dmit2015.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import dmit2015.model.JobApplication;

@Named("currentJobApplicationController")
@RequestScoped
public class JobApplicationController {
	
	private JobApplication currentJobApplication = new JobApplication();	// +getter
	private Integer[] selectedEmploymentTypes;								// +getter +setter
	
	public JobApplication getCurrentJobApplication() {
		return currentJobApplication;
	}
	public void setCurrentJobApplication(JobApplication currentJobApplication) {
		this.currentJobApplication = currentJobApplication;
	}
	public Integer[] getSelectedEmploymentTypes() {
		return selectedEmploymentTypes;
	}
	public void setSelectedEmploymentTypes(Integer[] selectedEmploymentTypes) {
		this.selectedEmploymentTypes = selectedEmploymentTypes;
	}
	
	public String submit() {
		if (selectedEmploymentTypes != null) {
			currentJobApplication.getEmploymentTypes().clear();
			for(Integer type : selectedEmploymentTypes) {
				currentJobApplication.getEmploymentTypes().add(type);		
			}			
		}
		Messages.addGlobalInfo("You job appliction was successfully submited for review.");
		return "/demo/jobApplicationConfirmation?faces-redirect=true";
	}

}
