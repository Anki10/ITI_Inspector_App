package com.ss.nsdc.entity;

import java.lang.ref.SoftReference;

/**
 * Created by Radhika on 5/15/2017.
 */

public class LandandBuilding {
    private Integer proc_tracker;
    private Integer refId;
    private String yearwisecollegeid;
    private String buildingType;
    private String dateofOccupancy;
    private String totalOpenArea;
    private String totalBuildArea;
    private String totalLand;
    private String liftinInstitute;
    private String noofFloors;
    private String orgInstalledLift;
    private String capacityofLift;
    private String safetyCertificateofLift;
    private String workshopRoof;
    private String buildingPlanofInstitute;
    private String architectRegnNo;
    private String architectName;
    private String wallOfIti;
    private String floorisCemented;
    private String itiSituatedintheSameCampus;
    private String avaibilityofSeparateMaleFemaleWashroom;
    private String aretheWashroomFunctional;
    private String avaibiltyofFireextinguisher;
    private String durationlease;
    private String expiryofAgreement;
    private String avaibilityofsafeDrinkingwater;
    private String avaibilityHousekeepingstaff;
    private String avaibilityofPlacementcell;
    private String avaibilityofLibraray;
    private String avaibilityofStaffroom;
    private String durationleaseRemarks;
    private String expiryofagreementRemarks;
    private String buildingTypeRemarks;
    private String dateofOccupancyRemarks;
    private String totalOpenAreaRemarks;
    private String totalBuildAreaRemarks;
    private String totalLandRemarks;
    private String liftinInstituteRemarks;
    private String noofFloorsRemarks;
    private String orgInstalledLiftRemarks;
    private String capacityofLiftRemarks;
    private String safetyCertificateofLiftRemarks;
    private String workshopRoofRemarks;
    private String buildingPlanofInstituteRemarks;
    private String architectRegnNoRemarks;
    private String architectNameRemarks;
    private String wallOfItiRemarks;
    private String floorisCementedRemarks;
    private String itiSituatedintheSameCampusRemarks;
    private String avaibilityofSeparateMaleFemaleWashroomRemarks;
    private String aretheWashroomFunctionalRemarks;
    private String avaibiltyofFireextinguisherRemarks;
    private String avaibilityofsafedrinkingwaterRemarks;
    private String avaibilityhousekeepingstaffRemarks;
    private String avaibilityofplacementcellRemarks;
    private String avaibilityoflibrarayRemarks;
    private String avaibilityofstaffroomRemarks;
    private Integer durationleaseNc;
    private Integer expiryofagreementNC;
    private Integer buildingTypeNc;
    private Integer dateofOccupancyNc;
    private Integer totalOpenAreaNc;
    private Integer totalBuildAreaNc;
    private Integer totalLandNc;
    private Integer liftinInstituteNc;
    private Integer noofFloorsNc;
    private Integer orgInstalledLiftNc;
    private Integer capacityofLiftNc;
    private Integer safetyCertificateofLiftNc;
    private Integer workshopRoofNc;
    private Integer buildingPlanofInstituteNc;
    private Integer architectRegnNoNc;
    private Integer architectNameNc;
    private Integer wallOfItiNc;
    private Integer floorisCementedNc;
    private Integer itiSituatedintheSameCampusNc;
    private Integer avaibilityofSeparateMaleFemaleWashroomNc;
    private Integer aretheWashroomFunctionalNc;
    private Integer avaibiltyofFireextinguisherNc;
    private Integer avaibilityofsafedrinkingwaterNc;
    private Integer avaibilityhousekeepingstaffNc;
    private Integer avaibilityofplacementcellNc;
    private Integer avaibilityoflibrarayNc;
    private Integer avaibilityofstaffroomNc;
    private String sitmap;
    private Integer sitmapNc;
    private String sitmapRemarks;
    private String dimension;
    private String dimensionRemarks;
    private Integer dimensionNC;
    private String map;
    private String mapRemarks;
    private Integer mapNC;
    private String comptent;
    private String comptentRemarks;
    private Integer comptentNC;
    private String approach;
    private String approachRemarks;
    private Integer approachNC;
    private String entrance;
    private String entranceRemarks;
    private Integer entranceNC;
    private String switchBoard;
    private String switchBoardRemarks;
    private Integer switchBoardNC;
    private String ventilation;
    private String ventilationRemarks;
    private Integer ventilationNC;
    private String IsMasterPlan;
    private String IsPremisesShared;
    private String SeperateEntrance;
    private String SoundProofPartition;
    private String Staircase;
    private String IsMasterPlanRemarks;
    private String IsPremisesSharedRemarks;
    private String SeperateEntranceRemarks;
    private String SoundProofPartitionRemarks;
    private String StaircaseRemarks;
    private Integer IsMasterPlanNC;
    private Integer IsPremisesSharedNC;
    private Integer SeperateEntranceNC;
    private Integer ISoundProofPartitionNC;
    private Integer StaircaseNC;
    private String constructed;
    private String constructedRemarks;
    private Integer constructedNC;
    private String prescribed;
    private String prescribedRemarks;
    private Integer prescribedNC;


    public String getConstructed(){
        return constructed;
    }
    public String getConstructedRemarks(){
        return constructedRemarks;
    }
    public String getPrescribed(){
        return prescribed;
    }
    public String getPrescribedRemarks(){
        return prescribedRemarks;
    }

    public void setConstructed(String constructed){
        this.constructed = constructed;
    }

    public void setConstructedRemarks(String constructedRemarks){
        this.constructedRemarks = constructedRemarks;
    }

    public void setPrescribed(String prescribed){
        this.prescribed = prescribed;
    }
    public void setPrescribedRemarks(String prescribedRemarks){
        this.prescribedRemarks = prescribedRemarks;
    }

    public Integer getConstructedNC(){
        return constructedNC;
    }

    public void setConstructedNC(Integer constructedNC){
        this.constructedNC = constructedNC;
    }

    public Integer getPrescribedNC(){
        return prescribedNC;
    }

     public void  setPrescribedNC(Integer prescribedNC){
         this.prescribedNC = prescribedNC;
     }
    public Integer getStaircaseNC(){
        return StaircaseNC;
    }

    public void setStaircaseNC(Integer StaircaseNC){
        this.StaircaseNC = StaircaseNC;
    }
    public Integer getISoundProofPartitionNC(){
        return ISoundProofPartitionNC;
    }

    public void setISoundProofPartitionNC(Integer ISoundProofPartitionNC){
        this.ISoundProofPartitionNC = ISoundProofPartitionNC;
    }
    public Integer getSeperateEntranceNC(){
        return SeperateEntranceNC;
    }

    public void setSeperateEntranceNC(Integer SeperateEntranceNC){
        this.SeperateEntranceNC = SeperateEntranceNC;
    }
    public Integer getIsPremisesSharedNC(){
        return IsPremisesSharedNC;
    }

    public void setIsPremisesSharedNC(Integer IsPremisesSharedNC){
        this.IsPremisesSharedNC = IsPremisesSharedNC;
    }
    public Integer getIsMasterPlanNC(){
        return IsMasterPlanNC;
    }

    public void setIsMasterPlanNC(Integer IsMasterPlanNC){
        this.IsMasterPlanNC = IsMasterPlanNC;
    }
    public String getIsMasterPlanRemarks(){
        return IsMasterPlanRemarks;
    }

    public void setIsMasterPlanRemarks(String IsMasterPlanRemarks){
        this.IsMasterPlanRemarks = IsMasterPlanRemarks;
    }
    public String getIsPremisesSharedRemarks(){
        return IsPremisesSharedRemarks;
    }

    public void setIsPremisesSharedRemarks(String IsPremisesSharedRemarks){
        this.IsPremisesSharedRemarks = IsPremisesSharedRemarks;
    }
    public String getSeperateEntranceRemarks(){
        return SeperateEntranceRemarks;
    }

    public void setSeperateEntranceRemarks(String SeperateEntranceRemarks){
        this.SeperateEntranceRemarks = SeperateEntranceRemarks;
    }
    public String getSoundProofPartitionRemarks(){
        return SoundProofPartitionRemarks;
    }

    public void setSoundProofPartitionRemarks(String SoundProofPartitionRemarks){
        this.SoundProofPartitionRemarks = SoundProofPartitionRemarks;
    }
    public String getStaircaseRemarks(){
        return StaircaseRemarks;
    }

    public void setStaircaseRemarks(String StaircaseRemarks){
        this.StaircaseRemarks = StaircaseRemarks;
    }
    public String getStaircase(){
        return Staircase;
    }

    public void setStaircase(String Staircase){
        this.Staircase = Staircase;
    }

    public String getSoundProofPartition(){
        return SoundProofPartition;
    }

    public void setSoundProofPartition(String SoundProofPartition){
        this.SoundProofPartition = SoundProofPartition;
    }

    public String getIsMasterPlan(){
        return IsMasterPlan;
    }

    public void setIsMasterPlan(String IsMasterPlan){
        this.IsMasterPlan = IsMasterPlan;
    }

    public String getIsPremisesShared(){
        return IsPremisesShared;
    }

    public void setIsPremisesShared(String IsPremisesShared){
        this.IsPremisesShared = IsPremisesShared;
    }

    public String getSeperateEntrance(){
        return SeperateEntrance;
    }

    public void setSeperateEntrance(String SeperateEntrance){
        this.SeperateEntrance = SeperateEntrance;
    }







    public String getSwitchBoard(){
        return switchBoard;
    }

    public void setSwitchBoard(String switchBoard){
        this.switchBoard = switchBoard;
    }

    public String getSwitchBoardRemarks(){
        return switchBoardRemarks;
    }

    public void setSwitchBoardRemarks(String switchBoardRemarks){
        this.switchBoardRemarks = switchBoardRemarks;
    }

    public Integer getSwitchBoardNC(){
        return switchBoardNC;
    }

    public void setSwitchBoardNC(Integer switchBoardNC){
        this.switchBoardNC = switchBoardNC;
    }



    public String getVentilation(){
        return ventilation;
    }

    public void setVentilation(String ventilation){
        this.ventilation = ventilation;
    }

    public String getVentilationRemarks(){
        return ventilationRemarks;
    }

    public void setVentilationRemarks(String ventilationRemarks){
        this.ventilationRemarks = ventilationRemarks;
    }

    public Integer getVentilationNC(){
        return ventilationNC;
    }

    public void setVentilationNC(Integer ventilationNC){
        this.ventilationNC = ventilationNC;
    }



    public String getApproach(){
        return approach;
    }

    public void setApproach(String approach){
        this.approach = approach;
    }

    public String getApproachRemarks(){
        return approachRemarks;
    }

    public void setApproachRemarks(String approachRemarks){
        this.approachRemarks = approachRemarks;
    }

    public Integer getApproachNC(){
        return approachNC;
    }

    public void setApproachNC(Integer approachNC){
        this.approachNC = approachNC;
    }


    public String getEntrance(){
        return entrance;
    }

    public void setEntrance(String entrance){
        this.entrance = entrance;
    }

    public String getEntranceRemarks(){
        return entranceRemarks;
    }

    public void setEntranceRemarks(String entranceRemarks){
        this.entranceRemarks = entranceRemarks;
    }

    public Integer getEntranceNC(){
        return entranceNC;
    }

    public void setEntranceNC(Integer entranceNC){
        this.entranceNC = entranceNC;
    }



    public String getComptent(){
        return comptent;
    }

    public void setComptent(String comptent){
        this.comptent = comptent;
    }

    public String getComptentRemarks(){
      return comptentRemarks;
    }

    public void setComptentRemarks(String comptentRemarks){
        this.comptentRemarks = comptentRemarks;
    }

    public Integer getComptentNC(){
        return comptentNC;
    }

    public void setComptentNC(Integer comptentNC){
        this.comptentNC = comptentNC;
    }
    public String getDimension(){
        return dimension;
    }

    public String getDimensionRemarks(){
        return dimensionRemarks;
    }

    public Integer getDimensionNC(){
        return dimensionNC;
    }

    public String getMap(){
        return map;
    }

    public String getMapRemarks(){
        return mapRemarks;
    }

    public Integer getMapNC(){
        return mapNC;
    }

    public void setMap(String map){
        this.map = map;
    }

    public void setMapRemarks(String mapRemarks){
        this.mapRemarks = mapRemarks;
    }

    public void setMapNC(Integer mapNC){
        this.mapNC = mapNC;
    }

    public void setDimension(String dimension){
        this.dimension = dimension;
    }

    public void setDimensionRemarks(String dimensionRemarks){
        this.dimensionRemarks = dimensionRemarks;
    }

    public void setDimensionNC(Integer dimensionNC){
        this.dimensionNC = dimensionNC;
    }
    public Integer getSitmapNc(){
        return sitmapNc;
    }

    public String getSitmapRemarks(){
        return sitmapRemarks;
    }

    public void setSitmapNc(Integer sitmapNc){
        this.sitmapNc = sitmapNc;
    }

    public void setSitmapRemarks(String sitmapRemarks){
        this.sitmapRemarks = sitmapRemarks;
    }
    public String getSitmap(){
        return sitmap;
    }

    public void setSitmap(String sitmap){
        this.sitmap = sitmap;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getYearwisecollegeid() {
        return yearwisecollegeid;
    }

    public void setYearwisecollegeid(String yearwisecollegeid) {
        this.yearwisecollegeid = yearwisecollegeid;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getDateofOccupancy() {
        return dateofOccupancy;
    }

    public void setDateofOccupancy(String dateofOccupancy) {
        this.dateofOccupancy = dateofOccupancy;
    }

    public String getTotalOpenArea() {
        return totalOpenArea;
    }

    public void setTotalOpenArea(String totalOpenArea) {
        this.totalOpenArea = totalOpenArea;
    }

    public String getTotalBuildArea() {
        return totalBuildArea;
    }

    public void setTotalBuildArea(String totalBuildArea) {
        this.totalBuildArea = totalBuildArea;
    }

    public String getTotalLand() {
        return totalLand;
    }

    public void setTotalLand(String totalLand) {
        this.totalLand = totalLand;
    }

    public String getLiftinInstitute() {
        return liftinInstitute;
    }

    public void setLiftinInstitute(String liftinInstitute) {
        this.liftinInstitute = liftinInstitute;
    }

    public String getNoofFloors() {
        return noofFloors;
    }

    public void setNoofFloors(String noofFloors) {
        this.noofFloors = noofFloors;
    }

    public String getOrgInstalledLift() {
        return orgInstalledLift;
    }

    public void setOrgInstalledLift(String orgInstalledLift) {
        this.orgInstalledLift = orgInstalledLift;
    }

    public String getCapacityofLift() {
        return capacityofLift;
    }

    public void setCapacityofLift(String capacityofLift) {
        this.capacityofLift = capacityofLift;
    }

    public String getSafetyCertificateofLift() {
        return safetyCertificateofLift;
    }

    public void setSafetyCertificateofLift(String safetyCertificateofLift) {
        this.safetyCertificateofLift = safetyCertificateofLift;
    }

    public String getWorkshopRoof() {
        return workshopRoof;
    }

    public void setWorkshopRoof(String workshopRoof) {
        this.workshopRoof = workshopRoof;
    }

    public String getBuildingPlanofInstitute() {
        return buildingPlanofInstitute;
    }

    public void setBuildingPlanofInstitute(String buildingPlanofInstitute) {
        this.buildingPlanofInstitute = buildingPlanofInstitute;
    }

    public String getArchitectRegnNo() {
        return architectRegnNo;
    }

    public void setArchitectRegnNo(String architectRegnNo) {
        this.architectRegnNo = architectRegnNo;
    }

    public String getArchitectName() {
        return architectName;
    }

    public void setArchitectName(String architectName) {
        this.architectName = architectName;
    }

    public String getWallOfIti() {
        return wallOfIti;
    }

    public void setWallOfIti(String wallOfIti) {
        this.wallOfIti = wallOfIti;
    }

    public String getFloorisCemented() {
        return floorisCemented;
    }

    public void setFloorisCemented(String floorisCemented) {
        this.floorisCemented = floorisCemented;
    }

    public String getItiSituatedintheSameCampus() {
        return itiSituatedintheSameCampus;
    }

    public void setItiSituatedintheSameCampus(String itiSituatedintheSameCampus) {
        this.itiSituatedintheSameCampus = itiSituatedintheSameCampus;
    }

    public String getAvaibilityofSeparateMaleFemaleWashroom() {
        return avaibilityofSeparateMaleFemaleWashroom;
    }

    public void setAvaibilityofSeparateMaleFemaleWashroom(String avaibilityofSeparateMaleFemaleWashroom) {
        this.avaibilityofSeparateMaleFemaleWashroom = avaibilityofSeparateMaleFemaleWashroom;
    }

    public String getAretheWashroomFunctional() {
        return aretheWashroomFunctional;
    }

    public void setAretheWashroomFunctional(String aretheWashroomFunctional) {
        this.aretheWashroomFunctional = aretheWashroomFunctional;
    }

    public String getAvaibiltyofFireextinguisher() {
        return avaibiltyofFireextinguisher;
    }

    public void setAvaibiltyofFireextinguisher(String avaibiltyofFireextinguisher) {
        this.avaibiltyofFireextinguisher = avaibiltyofFireextinguisher;
    }

    public String getDurationlease() {
        return durationlease;
    }

    public void setDurationlease(String durationlease) {
        this.durationlease = durationlease;
    }

    public String getExpiryofAgreement() {
        return expiryofAgreement;
    }

    public void setExpiryofAgreement(String expiryofAgreement) {
        this.expiryofAgreement = expiryofAgreement;
    }

    public String getAvaibilityofsafeDrinkingwater() {
        return avaibilityofsafeDrinkingwater;
    }

    public void setAvaibilityofsafeDrinkingwater(String avaibilityofsafeDrinkingwater) {
        this.avaibilityofsafeDrinkingwater = avaibilityofsafeDrinkingwater;
    }

    public String getAvaibilityHousekeepingstaff() {
        return avaibilityHousekeepingstaff;
    }

    public void setAvaibilityHousekeepingstaff(String avaibilityHousekeepingstaff) {
        this.avaibilityHousekeepingstaff = avaibilityHousekeepingstaff;
    }

    public String getAvaibilityofPlacementcell() {
        return avaibilityofPlacementcell;
    }

    public void setAvaibilityofPlacementcell(String avaibilityofPlacementcell) {
        this.avaibilityofPlacementcell = avaibilityofPlacementcell;
    }

    public String getAvaibilityofLibraray() {
        return avaibilityofLibraray;
    }

    public void setAvaibilityofLibraray(String avaibilityofLibraray) {
        this.avaibilityofLibraray = avaibilityofLibraray;
    }

    public String getAvaibilityofStaffroom() {
        return avaibilityofStaffroom;
    }

    public void setAvaibilityofStaffroom(String avaibilityofStaffroom) {
        this.avaibilityofStaffroom = avaibilityofStaffroom;
    }

    public String getDurationleaseRemarks() {
        return durationleaseRemarks;
    }

    public void setDurationleaseRemarks(String durationleaseRemarks) {
        this.durationleaseRemarks = durationleaseRemarks;
    }

    public String getExpiryofagreementRemarks() {
        return expiryofagreementRemarks;
    }

    public void setExpiryofagreementRemarks(String expiryofagreementRemarks) {
        this.expiryofagreementRemarks = expiryofagreementRemarks;
    }

    public String getBuildingTypeRemarks() {
        return buildingTypeRemarks;
    }

    public void setBuildingTypeRemarks(String buildingTypeRemarks) {
        this.buildingTypeRemarks = buildingTypeRemarks;
    }

    public String getDateofOccupancyRemarks() {
        return dateofOccupancyRemarks;
    }

    public void setDateofOccupancyRemarks(String dateofOccupancyRemarks) {
        this.dateofOccupancyRemarks = dateofOccupancyRemarks;
    }

    public String getTotalOpenAreaRemarks() {
        return totalOpenAreaRemarks;
    }

    public void setTotalOpenAreaRemarks(String totalOpenAreaRemarks) {
        this.totalOpenAreaRemarks = totalOpenAreaRemarks;
    }

    public String getTotalBuildAreaRemarks() {
        return totalBuildAreaRemarks;
    }

    public void setTotalBuildAreaRemarks(String totalBuildAreaRemarks) {
        this.totalBuildAreaRemarks = totalBuildAreaRemarks;
    }

    public String getTotalLandRemarks() {
        return totalLandRemarks;
    }

    public void setTotalLandRemarks(String totalLandRemarks) {
        this.totalLandRemarks = totalLandRemarks;
    }

    public String getLiftinInstituteRemarks() {
        return liftinInstituteRemarks;
    }

    public void setLiftinInstituteRemarks(String liftinInstituteRemarks) {
        this.liftinInstituteRemarks = liftinInstituteRemarks;
    }

    public String getNoofFloorsRemarks() {
        return noofFloorsRemarks;
    }

    public void setNoofFloorsRemarks(String noofFloorsRemarks) {
        this.noofFloorsRemarks = noofFloorsRemarks;
    }

    public String getOrgInstalledLiftRemarks() {
        return orgInstalledLiftRemarks;
    }

    public void setOrgInstalledLiftRemarks(String orgInstalledLiftRemarks) {
        this.orgInstalledLiftRemarks = orgInstalledLiftRemarks;
    }

    public String getCapacityofLiftRemarks() {
        return capacityofLiftRemarks;
    }

    public void setCapacityofLiftRemarks(String capacityofLiftRemarks) {
        this.capacityofLiftRemarks = capacityofLiftRemarks;
    }

    public String getSafetyCertificateofLiftRemarks() {
        return safetyCertificateofLiftRemarks;
    }

    public void setSafetyCertificateofLiftRemarks(String safetyCertificateofLiftRemarks) {
        this.safetyCertificateofLiftRemarks = safetyCertificateofLiftRemarks;
    }

    public String getWorkshopRoofRemarks() {
        return workshopRoofRemarks;
    }

    public void setWorkshopRoofRemarks(String workshopRoofRemarks) {
        this.workshopRoofRemarks = workshopRoofRemarks;
    }

    public String getBuildingPlanofInstituteRemarks() {
        return buildingPlanofInstituteRemarks;
    }

    public void setBuildingPlanofInstituteRemarks(String buildingPlanofInstituteRemarks) {
        this.buildingPlanofInstituteRemarks = buildingPlanofInstituteRemarks;
    }

    public String getArchitectRegnNoRemarks() {
        return architectRegnNoRemarks;
    }

    public void setArchitectRegnNoRemarks(String architectRegnNoRemarks) {
        this.architectRegnNoRemarks = architectRegnNoRemarks;
    }

    public String getArchitectNameRemarks() {
        return architectNameRemarks;
    }

    public void setArchitectNameRemarks(String architectNameRemarks) {
        this.architectNameRemarks = architectNameRemarks;
    }

    public String getWallOfItiRemarks() {
        return wallOfItiRemarks;
    }

    public void setWallOfItiRemarks(String wallOfItiRemarks) {
        this.wallOfItiRemarks = wallOfItiRemarks;
    }

    public String getFloorisCementedRemarks() {
        return floorisCementedRemarks;
    }

    public void setFloorisCementedRemarks(String floorisCementedRemarks) {
        this.floorisCementedRemarks = floorisCementedRemarks;
    }

    public String getItiSituatedintheSameCampusRemarks() {
        return itiSituatedintheSameCampusRemarks;
    }

    public void setItiSituatedintheSameCampusRemarks(String itiSituatedintheSameCampusRemarks) {
        this.itiSituatedintheSameCampusRemarks = itiSituatedintheSameCampusRemarks;
    }

    public String getAvaibilityofSeparateMaleFemaleWashroomRemarks() {
        return avaibilityofSeparateMaleFemaleWashroomRemarks;
    }

    public void setAvaibilityofSeparateMaleFemaleWashroomRemarks(String avaibilityofSeparateMaleFemaleWashroomRemarks) {
        this.avaibilityofSeparateMaleFemaleWashroomRemarks = avaibilityofSeparateMaleFemaleWashroomRemarks;
    }

    public String getAretheWashroomFunctionalRemarks() {
        return aretheWashroomFunctionalRemarks;
    }

    public void setAretheWashroomFunctionalRemarks(String aretheWashroomFunctionalRemarks) {
        this.aretheWashroomFunctionalRemarks = aretheWashroomFunctionalRemarks;
    }

    public String getAvaibiltyofFireextinguisherRemarks() {
        return avaibiltyofFireextinguisherRemarks;
    }

    public void setAvaibiltyofFireextinguisherRemarks(String avaibiltyofFireextinguisherRemarks) {
        this.avaibiltyofFireextinguisherRemarks = avaibiltyofFireextinguisherRemarks;
    }

    public String getAvaibilityofsafedrinkingwaterRemarks() {
        return avaibilityofsafedrinkingwaterRemarks;
    }

    public void setAvaibilityofsafedrinkingwaterRemarks(String avaibilityofsafedrinkingwaterRemarks) {
        this.avaibilityofsafedrinkingwaterRemarks = avaibilityofsafedrinkingwaterRemarks;
    }

    public String getAvaibilityhousekeepingstaffRemarks() {
        return avaibilityhousekeepingstaffRemarks;
    }

    public void setAvaibilityhousekeepingstaffRemarks(String avaibilityhousekeepingstaffRemarks) {
        this.avaibilityhousekeepingstaffRemarks = avaibilityhousekeepingstaffRemarks;
    }

    public String getAvaibilityofplacementcellRemarks() {
        return avaibilityofplacementcellRemarks;
    }

    public void setAvaibilityofplacementcellRemarks(String avaibilityofplacementcellRemarks) {
        this.avaibilityofplacementcellRemarks = avaibilityofplacementcellRemarks;
    }

    public String getAvaibilityoflibrarayRemarks() {
        return avaibilityoflibrarayRemarks;
    }

    public void setAvaibilityoflibrarayRemarks(String avaibilityoflibrarayRemarks) {
        this.avaibilityoflibrarayRemarks = avaibilityoflibrarayRemarks;
    }

    public String getAvaibilityofstaffroomRemarks() {
        return avaibilityofstaffroomRemarks;
    }

    public void setAvaibilityofstaffroomRemarks(String avaibilityofstaffroomRemarks) {
        this.avaibilityofstaffroomRemarks = avaibilityofstaffroomRemarks;
    }

    public Integer getDurationleaseNc() {
        return durationleaseNc;
    }

    public void setDurationleaseNc(Integer durationleaseNc) {
        this.durationleaseNc = durationleaseNc;
    }

    public Integer getExpiryofagreementNC() {
        return expiryofagreementNC;
    }

    public void setExpiryofagreementNC(Integer expiryofagreementNC) {
        this.expiryofagreementNC = expiryofagreementNC;
    }

    public Integer getBuildingTypeNc() {
        return buildingTypeNc;
    }

    public void setBuildingTypeNc(Integer buildingTypeNc) {
        this.buildingTypeNc = buildingTypeNc;
    }

    public Integer getDateofOccupancyNc() {
        return dateofOccupancyNc;
    }

    public void setDateofOccupancyNc(Integer dateofOccupancyNc) {
        this.dateofOccupancyNc = dateofOccupancyNc;
    }

    public Integer getTotalOpenAreaNc() {
        return totalOpenAreaNc;
    }

    public void setTotalOpenAreaNc(Integer totalOpenAreaNc) {
        this.totalOpenAreaNc = totalOpenAreaNc;
    }

    public Integer getTotalBuildAreaNc() {
        return totalBuildAreaNc;
    }

    public void setTotalBuildAreaNc(Integer totalBuildAreaNc) {
        this.totalBuildAreaNc = totalBuildAreaNc;
    }

    public Integer getTotalLandNc() {
        return totalLandNc;
    }

    public void setTotalLandNc(Integer totalLandNc) {
        this.totalLandNc = totalLandNc;
    }

    public Integer getLiftinInstituteNc() {
        return liftinInstituteNc;
    }

    public void setLiftinInstituteNc(Integer liftinInstituteNc) {
        this.liftinInstituteNc = liftinInstituteNc;
    }

    public Integer getNoofFloorsNc() {
        return noofFloorsNc;
    }

    public void setNoofFloorsNc(Integer noofFloorsNc) {
        this.noofFloorsNc = noofFloorsNc;
    }

    public Integer getOrgInstalledLiftNc() {
        return orgInstalledLiftNc;
    }

    public void setOrgInstalledLiftNc(Integer orgInstalledLiftNc) {
        this.orgInstalledLiftNc = orgInstalledLiftNc;
    }

    public Integer getCapacityofLiftNc() {
        return capacityofLiftNc;
    }

    public void setCapacityofLiftNc(Integer capacityofLiftNc) {
        this.capacityofLiftNc = capacityofLiftNc;
    }

    public Integer getSafetyCertificateofLiftNc() {
        return safetyCertificateofLiftNc;
    }

    public void setSafetyCertificateofLiftNc(Integer safetyCertificateofLiftNc) {
        this.safetyCertificateofLiftNc = safetyCertificateofLiftNc;
    }

    public Integer getWorkshopRoofNc() {
        return workshopRoofNc;
    }

    public void setWorkshopRoofNc(Integer workshopRoofNc) {
        this.workshopRoofNc = workshopRoofNc;
    }

    public Integer getBuildingPlanofInstituteNc() {
        return buildingPlanofInstituteNc;
    }

    public void setBuildingPlanofInstituteNc(Integer buildingPlanofInstituteNc) {
        this.buildingPlanofInstituteNc = buildingPlanofInstituteNc;
    }

    public Integer getArchitectRegnNoNc() {
        return architectRegnNoNc;
    }

    public void setArchitectRegnNoNc(Integer architectRegnNoNc) {
        this.architectRegnNoNc = architectRegnNoNc;
    }

    public Integer getArchitectNameNc() {
        return architectNameNc;
    }

    public void setArchitectNameNc(Integer architectNameNc) {
        this.architectNameNc = architectNameNc;
    }

    public Integer getWallOfItiNc() {
        return wallOfItiNc;
    }

    public void setWallOfItiNc(Integer wallOfItiNc) {
        this.wallOfItiNc = wallOfItiNc;
    }

    public Integer getFloorisCementedNc() {
        return floorisCementedNc;
    }

    public void setFloorisCementedNc(Integer floorisCementedNc) {
        this.floorisCementedNc = floorisCementedNc;
    }

    public Integer getItiSituatedintheSameCampusNc() {
        return itiSituatedintheSameCampusNc;
    }

    public void setItiSituatedintheSameCampusNc(Integer itiSituatedintheSameCampusNc) {
        this.itiSituatedintheSameCampusNc = itiSituatedintheSameCampusNc;
    }

    public Integer getAvaibilityofSeparateMaleFemaleWashroomNc() {
        return avaibilityofSeparateMaleFemaleWashroomNc;
    }

    public void setAvaibilityofSeparateMaleFemaleWashroomNc(Integer avaibilityofSeparateMaleFemaleWashroomNc) {
        this.avaibilityofSeparateMaleFemaleWashroomNc = avaibilityofSeparateMaleFemaleWashroomNc;
    }

    public Integer getAretheWashroomFunctionalNc() {
        return aretheWashroomFunctionalNc;
    }

    public void setAretheWashroomFunctionalNc(Integer aretheWashroomFunctionalNc) {
        this.aretheWashroomFunctionalNc = aretheWashroomFunctionalNc;
    }

    public Integer getAvaibiltyofFireextinguisherNc() {
        return avaibiltyofFireextinguisherNc;
    }

    public void setAvaibiltyofFireextinguisherNc(Integer avaibiltyofFireextinguisherNc) {
        this.avaibiltyofFireextinguisherNc = avaibiltyofFireextinguisherNc;
    }

    public Integer getAvaibilityofsafedrinkingwaterNc() {
        return avaibilityofsafedrinkingwaterNc;
    }

    public void setAvaibilityofsafedrinkingwaterNc(Integer avaibilityofsafedrinkingwaterNc) {
        this.avaibilityofsafedrinkingwaterNc = avaibilityofsafedrinkingwaterNc;
    }

    public Integer getAvaibilityhousekeepingstaffNc() {
        return avaibilityhousekeepingstaffNc;
    }

    public void setAvaibilityhousekeepingstaffNc(Integer avaibilityhousekeepingstaffNc) {
        this.avaibilityhousekeepingstaffNc = avaibilityhousekeepingstaffNc;
    }

    public Integer getAvaibilityofplacementcellNc() {
        return avaibilityofplacementcellNc;
    }

    public void setAvaibilityofplacementcellNc(Integer avaibilityofplacementcellNc) {
        this.avaibilityofplacementcellNc = avaibilityofplacementcellNc;
    }

    public Integer getAvaibilityoflibrarayNc() {
        return avaibilityoflibrarayNc;
    }

    public void setAvaibilityoflibrarayNc(Integer avaibilityoflibrarayNc) {
        this.avaibilityoflibrarayNc = avaibilityoflibrarayNc;
    }

    public Integer getAvaibilityofstaffroomNc() {
        return avaibilityofstaffroomNc;
    }

    public void setAvaibilityofstaffroomNc(Integer avaibilityofstaffroomNc) {
        this.avaibilityofstaffroomNc = avaibilityofstaffroomNc;
    }
    public Integer getProc_tracker() {
        return proc_tracker;
    }


    public void setProc_tracker(Integer proc_tracker) {
        this.proc_tracker = proc_tracker;
    }
}
