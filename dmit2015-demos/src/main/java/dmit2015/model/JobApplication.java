package dmit2015.model;

import java.util.ArrayList;
import java.util.List;

public class JobApplication {

	private String name;
	private String phone;
	private String email;
	private List<Integer> employmentTypes = new ArrayList<Integer>();
	private boolean canadianCitizen = true;
	private String highestAcademicEducation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getEmploymentTypes() {
		return employmentTypes;
	}
	public void setEmploymentTypes(List<Integer> employmentTypes) {
		this.employmentTypes = employmentTypes;
	}
	public boolean isCanadianCitizen() {
		return canadianCitizen;
	}
	public void setCanadianCitizen(boolean canadianCitizen) {
		this.canadianCitizen = canadianCitizen;
	}
	public String getHighestAcademicEducation() {
		return highestAcademicEducation;
	}
	public void setHighestAcademicEducation(String highestAcademicEducation) {
		this.highestAcademicEducation = highestAcademicEducation;
	}
	
	public JobApplication() {
		super();
	}
	
	@Override
	public String toString() {
		return "JobApplication [name=" + name + ", phone=" + phone + ", email=" + email + ", employmentTypes="
				+ employmentTypes + ", canadianCitizen=" + canadianCitizen + ", highestAcademicEducation="
				+ highestAcademicEducation + "]";
	}
}
