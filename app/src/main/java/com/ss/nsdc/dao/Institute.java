package com.ss.nsdc.dao;


public class Institute {

	private Integer proc_tracker;
	private String yearWiseCollegeId;
	private String organisationName;
	private String headOrganisation;
	private String contactDetails;
	private String startedYear;

	public String getYearWiseCollegeId() {
		return yearWiseCollegeId;
	}

	public void setYearWiseCollegeId(String yearWiseCollegeId) {
		this.yearWiseCollegeId = yearWiseCollegeId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getHeadOrganisation() {
		return headOrganisation;
	}

	public void setHeadOrganisation(String headOrganisation) {
		this.headOrganisation = headOrganisation;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public String getStartedYear() {
		return startedYear;
	}

	public void setStartedYear(String startedYear) {
		this.startedYear = startedYear;
	}





	public Integer getProc_tracker() {
		return proc_tracker;
	}


	public void setProc_tracker(Integer proc_tracker) {
		this.proc_tracker = proc_tracker;
	}
}