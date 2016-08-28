package com.ss.nsdc.dao;

public class SubCategory {
	private int surveyCategoryId;
	private int surveySubCategoryId;
	private String surveySubCategoryName;
	private String surveySubCategoryStatus;
	private int surveySubCategoryOrder;
	private Integer proc_tracker;
	public int getSurveyCategoryId() {
		return surveyCategoryId;
	}
	public void setSurveyCategoryId(int surveyCategoryId) {
		this.surveyCategoryId = surveyCategoryId;
	}
	public int getSurveySubCategoryId() {
		return surveySubCategoryId;
	}
	public void setSurveySubCategoryId(int surveySubCategoryId) {
		this.surveySubCategoryId = surveySubCategoryId;
	}
	public String getSurveySubCategoryName() {
		return surveySubCategoryName;
	}
	public void setSurveySubCategoryName(String surveySubCategoryName) {
		this.surveySubCategoryName = surveySubCategoryName;
	}
	public String getSurveySubCategoryStatus() {
		return surveySubCategoryStatus;
	}
	public void setSurveySubCategoryStatus(String surveySubCategoryStatus) {
		this.surveySubCategoryStatus = surveySubCategoryStatus;
	}
	public int getSurveySubCategoryOrder() {
		return surveySubCategoryOrder;
	}
	public void setSurveySubCategoryOrder(int surveySubCategoryOrder) {
		this.surveySubCategoryOrder = surveySubCategoryOrder;
	}
	public Integer getProc_tracker() {
		return proc_tracker;
	}
	public void setProc_tracker(Integer proc_tracker) {
		this.proc_tracker = proc_tracker;
	}
	
	
}
