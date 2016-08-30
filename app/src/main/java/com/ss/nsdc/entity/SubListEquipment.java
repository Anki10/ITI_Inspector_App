package com.ss.nsdc.entity;

public class SubListEquipment {
	private String id;
	private String Job_Id;
	private String Job_Name;
	private String Equipment_Name;
	private String TotalNo;
	private String Remarks;
	private String InsTotalNo;
	private String InsRemarks;
	private String YearWiseCollegeId;
	private String ApplicationNo;
	private int proc_tracker;
	private String equipment_Id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getJob_Id() {
		return Job_Id;
	}
	public void setJob_Id(String job_Id) {
		Job_Id = job_Id;
	}
	public String getJob_Name() {
		return Job_Name;
	}
	public void setJob_Name(String job_Name) {
		Job_Name = job_Name;
	}
	public String getEquipment_Name() {
		return Equipment_Name;
	}
	public void setEquipment_Name(String equipment_Name) {
		Equipment_Name = equipment_Name;
	}
	public String getTotalNo() {
		return TotalNo;
	}
	public void setTotalNo(String totalNo) {
		TotalNo = totalNo;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	public String getInsTotalNo() {
		return InsTotalNo;
	}
	public void setInsTotalNo(String insTotalNo) {
		InsTotalNo = insTotalNo;
	}
	public String getInsRemarks() {
		return InsRemarks;
	}
	public void setInsRemarks(String insRemarks) {
		InsRemarks = insRemarks;
	}
	public int getProc_tracker() {
		return proc_tracker;
	}
	public void setProc_tracker(int proc_tracker) {
		this.proc_tracker = proc_tracker;
	}
	public String getEquipment_Id() {return equipment_Id;}
	public void setEquipment_Id(String equipment_Id) {this.equipment_Id = equipment_Id;}
}
