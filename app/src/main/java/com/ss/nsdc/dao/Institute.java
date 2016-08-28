package com.ss.nsdc.dao;


public class Institute {
	private int  instituteId ;
	private String YearWiseCollegeId;
	private String ApplicationNo;
	private String Name_ParentOrg; 
	private String Name_Training_Center; 
	private String DistrictName;  
	private String StateName; 
	private String Stage;
	private String CreationDate;
	private String SubmissionDate;
	private String inspectionFromDate;
	private String inspectionToDate;
	private String contactno;
	private String emailId;
	private Integer proc_tracker;
	
	public int getInstituteId() {
		return instituteId;
	}


	public void setInstituteId(int instituteId) {
		this.instituteId = instituteId;
	}


	public String getYearWiseCollegeId() {
		return YearWiseCollegeId;
	}


	public void setYearWiseCollegeId(String yearWiseCollegeId) {
		YearWiseCollegeId = yearWiseCollegeId;
	}


	public String getApplicationNo() {
		return ApplicationNo;
	}


	public void setApplicationNo(String applicationNo) {
		ApplicationNo = applicationNo;
	}


	public String getName_ParentOrg() {
		return Name_ParentOrg;
	}


	public void setName_ParentOrg(String name_ParentOrg) {
		Name_ParentOrg = name_ParentOrg;
	}


	public String getName_Training_Center() {
		return Name_Training_Center;
	}


	public void setName_Training_Center(String name_Training_Center) {
		Name_Training_Center = name_Training_Center;
	}


	public String getDistrictName() {
		return DistrictName;
	}


	public void setDistrictName(String districtName) {
		DistrictName = districtName;

	}


	public String getStateName() {
		return StateName;
	}


	public void setStateName(String stateName) {
		StateName = stateName;
	}


	public String getStage() {
		return Stage;
	}


	public void setStage(String stage) {
		Stage = stage;
	}


	public String getCreationDate() {
		return CreationDate;
	}


	public void setCreationDate(String creationDate) {
		CreationDate = creationDate;
	}


	public String getSubmissionDate() {
		return SubmissionDate;
	}


	public void setSubmissionDate(String submissionDate) {
		SubmissionDate = submissionDate;
	}



	public String getInspectionToDate() {
		return inspectionToDate;
	}


	public void setInspectionToDate(String inspectionToDate) {
		this.inspectionToDate = inspectionToDate;
	}


	public String getInspectionFromDate() {
		return inspectionFromDate;
	}


	public void setInspectionFromDate(String inspectionFromDate) {
		this.inspectionFromDate = inspectionFromDate;
	}


	public String getContactno() {
		return contactno;
	}


	public void setContactno(String contactno) {
		this.contactno = contactno;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Integer getProc_tracker() {
		return proc_tracker;
	}


	public void setProc_tracker(Integer proc_tracker) {
		this.proc_tracker = proc_tracker;
	}
}