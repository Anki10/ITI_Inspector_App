package com.ss.nsdc.entity;

/**
 * Created by Prashant on 5/28/2017.
 */

public class WorkshopAreaModel {

    private Integer Id;
    private Integer refId;
    private Integer available;
    private String tradeId;
    private String tradeName;
    private String workshopName;
    private String shiftsUnit;
    private String shiftsunitRemarks;
    private Integer shiftsNc;
    private String istheWorkshopRectangular;
    private String istheWorkshopRectangularRemarks;
    private Integer istheWorkshopRectangularNc;
    private String widthOftheWorkshop;
    private String widthOftheWorkshopRemarks;
    private Integer widthOftheWorkshopNc;
    private String aretheWorkshopWallsofTin;
    private String aretheWorkshopWallsofTinRemarks;
    private Integer aretheWorkshopWallsofTinNc;
    private String workShopRoof;
    private String workShopRoof_Remarks;
    private String ceilingHeightlessthan12Feet;
    private String ceilingHeightlessthan12Feet_Remarks;
    private String istheheavyMachineryLocated;
    private String istheheavyMachineryLocated_Remarks;
    private String itihaveCombinedWorkshop;
    private String itihaveCombinedWorkshopRemarks;
    private String istheDemarcated;
    private String istheDemarcatedRemarks;
    private String emergencyContactNumberDisplay;
    private String emergencyContactNumberDisplayRemarks;
    private String ceilingHeightlessthan10Feet;
    private String ceilingHeightlessthan10FeetRemarks;
    private String actualArea;
    private String actualareaRemarks;
    private String isWiresandBoardsCovered;
    private String isFireExtinguisherAvailable;
    private String isEmergencyExit;
    private String isWiresandBoardsCoveredRemarks;
    private String isFireExtinguisherAvailableRemarks;
    private String isEmergencyExitRemarks;
    private int isWiresandBoardsCoveredNc;
    private int isFireExtinguisherAvailableNc;
    private int machinaryNc;
    private int electricalNc;
    private int rubberNc;

    private String heavyMachinary;
    private String heavyMachinaryRemarks;
    private Integer heavyMachinaryNC;

    private String machinaryTools;
    private String machinaryToolsRemarks;
    private Integer machinaryToolsNC;


    private String machinesComplying;
    private String machinesComplyingRemarks;
    private Integer machinesComplyingNC;


    private String rubberMats;
    private String rubberMatsRemarks;
    private Integer rubberMatsNC;

    private String dgsetRequired;
    private String dgsetRequiredRemarks;
    private Integer dgsetRequiredNC;

    private String dgsetInstalled;
    private String dgsetInstalledRemarks;
    private Integer dgsetInstalledNC;

    private String latheRequired;
    private String latheRequiredRemarks;
    private Integer latheRequiredNC;
    private String latheInstalled;
    private String latheInstalledRemarks;
    private Integer latheInstalledNC;

    private String majorMachine;
    private String majorMachineRemarks;
    private Integer majorMachineNC;



    private String emergencyContact;
    private String emergencyContactRemarks;
    private Integer emergencyContactNC;

    private String yearWiseCollegeid;
    private int proc_tracker;
    private Integer flag;

    public String getMajorMachine(){
        return majorMachine;
    }

    public void setMajorMachine(String majorMachine){
        this.majorMachine = majorMachine;
    }

    public String getMajorMachineRemarks(){
        return majorMachineRemarks;
    }

    public void setMajorMachineRemarks(String majorMachineRemarks){
        this.majorMachineRemarks = majorMachineRemarks;
    }

    public Integer getMajorMachineNC(){
        return majorMachineNC;
    }

    public void setMajorMachineNC(Integer majorMachineNC){
        this.majorMachineNC = majorMachineNC;
    }

    public String getLatheInstalled(){
        return latheInstalled;
    }

    public void setLatheInstalled(String latheInstalled){
        this.latheInstalled = latheInstalled;
    }

    public String getLatheInstalledRemarks(){
        return latheInstalledRemarks;
    }

    public void setLatheInstalledRemarks(String latheInstalledRemarks){
        this.latheInstalledRemarks = latheInstalledRemarks;
    }

    public Integer getLatheInstalledNC(){
        return latheInstalledNC;
    }

    public void setLatheInstalledNC(Integer latheInstalledNC){
        this.latheInstalledNC = latheInstalledNC;
    }

    public String getLatheRequired(){
        return latheRequired;
    }

    public void setLatheRequired(String latheRequired){
        this.latheRequired = latheRequired;
    }

    public String getLatheRequiredRemarks(){
        return latheRequiredRemarks;
    }

    public void setLatheRequiredRemarks(String latheRequiredRemarks){
        this.latheRequiredRemarks = latheRequiredRemarks;
    }

    public Integer getLatheRequiredNC(){
        return latheRequiredNC;
    }

    public void setLatheRequiredNC(Integer latheRequiredNC){
        this.latheRequiredNC = latheRequiredNC;
    }

    public String getMachinaryTools(){
        return machinaryTools;
    }

    public void setMachinaryTools(String machinaryTools){
        this.machinaryTools = machinaryTools;
    }

    public String getMachinaryToolsRemarks(){
        return machinaryToolsRemarks;
    }

    public void setMachinaryToolsRemarks(String machinaryToolsRemarks){
        this.machinaryToolsRemarks = machinaryToolsRemarks;
    }

    public Integer getMachinaryToolsNC(){
        return machinaryToolsNC;
    }

    public void setMachinaryToolsNC(Integer machinaryToolsNC){
        this.machinaryToolsNC = machinaryToolsNC;
    }

    public String getMachinesComplying(){
        return machinesComplying;
    }

    public void setMachinesComplying(String machinesComplying){
        this.machinesComplying = machinesComplying;
    }

    public String getMachinesComplyingRemarks(){
        return machinesComplyingRemarks;
    }

    public void setMachinesComplyingRemarks(String machinesComplyingRemarks){
        this.machinesComplyingRemarks = machinesComplyingRemarks;
    }

    public Integer getMachinesComplyingNC(){
        return machinesComplyingNC;
    }

    public void setMachinesComplyingNC(Integer machinesComplyingNC){
        this.machinesComplyingNC = machinesComplyingNC;
    }

    public String getRubberMats(){
        return rubberMats;
    }

    public void setRubberMats(String rubberMats){
        this.rubberMats = rubberMats;
    }

    public String getRubberMatsRemarks(){
        return rubberMatsRemarks;
    }

    public void setRubberMatsRemarks(String rubberMatsRemarks){
        this.rubberMatsRemarks = rubberMatsRemarks;
    }

    public Integer getRubberMatsNC(){
        return rubberMatsNC;
    }

    public void setRubberMatsNC(Integer rubberMatsNC){
        this.rubberMatsNC = rubberMatsNC;
    }

    public String getDgsetRequired(){
        return dgsetRequired;
    }

    public void setDgsetRequired(String dgsetRequired){
        this.dgsetRequired = dgsetRequired;
    }

    public String getDgsetRequiredRemarks(){
        return dgsetRequiredRemarks;
    }

    public void setDgsetRequiredRemarks(String dgsetRequiredRemarks){
        this.dgsetRequiredRemarks = dgsetRequiredRemarks;
    }

    public Integer getDgsetRequiredNC(){
        return dgsetRequiredNC;
    }

    public void setDgsetRequiredNC(Integer dgsetRequiredNC){
        this.dgsetRequiredNC = dgsetRequiredNC;
    }

    public String getDgsetInstalled(){
        return dgsetInstalled;
    }

    public void setDgsetInstalled(String dgsetInstalled){
        this.dgsetInstalled = dgsetInstalled;
    }

    public String getDgsetInstalledRemarks(){
        return dgsetInstalledRemarks;
    }

    public void setDgsetInstalledRemarks(String dgsetInstalledRemarks){
        this.dgsetInstalledRemarks = dgsetInstalledRemarks;
    }

    public Integer getDgsetInstalledNC(){
        return dgsetInstalledNC;
    }

    public void setDgsetInstalledNC(Integer dgsetInstalledNC){
        this.dgsetInstalledNC = dgsetInstalledNC;
    }

    public String getEmergencyContact(){
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact){
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactRemarks(){
        return emergencyContactRemarks;
    }

    public void setEmergencyContactRemarks(String emergencyContactRemarks){
        this.emergencyContactRemarks = emergencyContactRemarks;
    }

    public Integer getEmergencyContactNC(){
        return emergencyContactNC;
    }

    public void setEmergencyContactNC(Integer emergencyContactNC){
        this.emergencyContactNC = emergencyContactNC;
    }


    public String getHeavyMachinary(){
        return heavyMachinary;
    }

    public void setHeavyMachinary(String heavyMachinary){
        this.heavyMachinary = heavyMachinary;
    }

    public String getHeavyMachinaryRemarks(){
        return heavyMachinaryRemarks;
    }

    public void setHeavyMachinaryRemarks(String heavyMachinaryRemarks){
        this.heavyMachinaryRemarks = heavyMachinaryRemarks;
    }

    public Integer getHeavyMachinaryNC(){
        return heavyMachinaryNC;
    }

    public void setHeavyMachinaryNC(Integer heavyMachinaryNC){
        this.heavyMachinaryNC = heavyMachinaryNC;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getShiftsUnit() {
        return shiftsUnit;
    }

    public void setShiftsUnit(String shiftsUnit) {
        this.shiftsUnit = shiftsUnit;
    }

    public String getShiftsunitRemarks() {
        return shiftsunitRemarks;
    }

    public void setShiftsunitRemarks(String shiftsunitRemarks) {
        this.shiftsunitRemarks = shiftsunitRemarks;
    }

    public Integer getShiftsNc() {
        return shiftsNc;
    }

    public void setShiftsNc(Integer shiftsNc) {
        this.shiftsNc = shiftsNc;
    }

    public String getIstheWorkshopRectangular() {
        return istheWorkshopRectangular;
    }

    public void setIstheWorkshopRectangular(String istheWorkshopRectangular) {
        this.istheWorkshopRectangular = istheWorkshopRectangular;
    }

    public String getIstheWorkshopRectangularRemarks() {
        return istheWorkshopRectangularRemarks;
    }

    public void setIstheWorkshopRectangularRemarks(String istheWorkshopRectangularRemarks) {
        this.istheWorkshopRectangularRemarks = istheWorkshopRectangularRemarks;
    }

    public Integer getIstheWorkshopRectangularNc() {
        return istheWorkshopRectangularNc;
    }

    public void setIstheWorkshopRectangularNc(Integer istheWorkshopRectangularNc) {
        this.istheWorkshopRectangularNc = istheWorkshopRectangularNc;
    }

    public String getWidthOftheWorkshop() {
        return widthOftheWorkshop;
    }

    public void setWidthOftheWorkshop(String widthOftheWorkshop) {
        this.widthOftheWorkshop = widthOftheWorkshop;
    }

    public String getWidthOftheWorkshopRemarks() {
        return widthOftheWorkshopRemarks;
    }

    public void setWidthOftheWorkshopRemarks(String widthOftheWorkshopRemarks) {
        this.widthOftheWorkshopRemarks = widthOftheWorkshopRemarks;
    }

    public Integer getWidthOftheWorkshopNc() {
        return widthOftheWorkshopNc;
    }

    public void setWidthOftheWorkshopNc(Integer widthOftheWorkshopNc) {
        this.widthOftheWorkshopNc = widthOftheWorkshopNc;
    }

    public String getAretheWorkshopWallsofTin() {
        return aretheWorkshopWallsofTin;
    }

    public void setAretheWorkshopWallsofTin(String aretheWorkshopWallsofTin) {
        this.aretheWorkshopWallsofTin = aretheWorkshopWallsofTin;
    }

    public String getAretheWorkshopWallsofTinRemarks() {
        return aretheWorkshopWallsofTinRemarks;
    }

    public void setAretheWorkshopWallsofTinRemarks(String aretheWorkshopWallsofTinRemarks) {
        this.aretheWorkshopWallsofTinRemarks = aretheWorkshopWallsofTinRemarks;
    }

    public Integer getAretheWorkshopWallsofTinNc() {
        return aretheWorkshopWallsofTinNc;
    }

    public void setAretheWorkshopWallsofTinNc(Integer aretheWorkshopWallsofTinNc) {
        this.aretheWorkshopWallsofTinNc = aretheWorkshopWallsofTinNc;
    }

    public String getWorkShopRoof() {
        return workShopRoof;
    }

    public void setWorkShopRoof(String workShopRoof) {
        this.workShopRoof = workShopRoof;
    }

    public String getWorkShopRoof_Remarks() {
        return workShopRoof_Remarks;
    }

    public void setWorkShopRoof_Remarks(String workShopRoof_Remarks) {
        this.workShopRoof_Remarks = workShopRoof_Remarks;
    }

    public String getCeilingHeightlessthan12Feet() {
        return ceilingHeightlessthan12Feet;
    }

    public void setCeilingHeightlessthan12Feet(String ceilingHeightlessthan12Feet) {
        this.ceilingHeightlessthan12Feet = ceilingHeightlessthan12Feet;
    }

    public String getCeilingHeightlessthan12Feet_Remarks() {
        return ceilingHeightlessthan12Feet_Remarks;
    }

    public void setCeilingHeightlessthan12Feet_Remarks(String ceilingHeightlessthan12Feet_Remarks) {
        this.ceilingHeightlessthan12Feet_Remarks = ceilingHeightlessthan12Feet_Remarks;
    }

    public String getIstheheavyMachineryLocated() {
        return istheheavyMachineryLocated;
    }

    public void setIstheheavyMachineryLocated(String istheheavyMachineryLocated) {
        this.istheheavyMachineryLocated = istheheavyMachineryLocated;
    }

    public String getIstheheavyMachineryLocated_Remarks() {
        return istheheavyMachineryLocated_Remarks;
    }

    public void setIstheheavyMachineryLocated_Remarks(String istheheavyMachineryLocated_Remarks) {
        this.istheheavyMachineryLocated_Remarks = istheheavyMachineryLocated_Remarks;
    }

    public String getItihaveCombinedWorkshop() {
        return itihaveCombinedWorkshop;
    }

    public void setItihaveCombinedWorkshop(String itihaveCombinedWorkshop) {
        this.itihaveCombinedWorkshop = itihaveCombinedWorkshop;
    }

    public String getItihaveCombinedWorkshopRemarks() {
        return itihaveCombinedWorkshopRemarks;
    }

    public void setItihaveCombinedWorkshopRemarks(String itihaveCombinedWorkshopRemarks) {
        this.itihaveCombinedWorkshopRemarks = itihaveCombinedWorkshopRemarks;
    }

    public String getIstheDemarcated() {
        return istheDemarcated;
    }

    public void setIstheDemarcated(String istheDemarcated) {
        this.istheDemarcated = istheDemarcated;
    }

    public String getIstheDemarcatedRemarks() {
        return istheDemarcatedRemarks;
    }

    public void setIstheDemarcatedRemarks(String istheDemarcatedRemarks) {
        this.istheDemarcatedRemarks = istheDemarcatedRemarks;
    }

    public String getEmergencyContactNumberDisplay() {
        return emergencyContactNumberDisplay;
    }

    public void setEmergencyContactNumberDisplay(String emergencyContactNumberDisplay) {
        this.emergencyContactNumberDisplay = emergencyContactNumberDisplay;
    }

    public String getEmergencyContactNumberDisplayRemarks() {
        return emergencyContactNumberDisplayRemarks;
    }

    public void setEmergencyContactNumberDisplayRemarks(String emergencyContactNumberDisplayRemarks) {
        this.emergencyContactNumberDisplayRemarks = emergencyContactNumberDisplayRemarks;
    }

    public String getCeilingHeightlessthan10Feet() {
        return ceilingHeightlessthan10Feet;
    }

    public void setCeilingHeightlessthan10Feet(String ceilingHeightlessthan10Feet) {
        this.ceilingHeightlessthan10Feet = ceilingHeightlessthan10Feet;
    }

    public String getCeilingHeightlessthan10FeetRemarks() {
        return ceilingHeightlessthan10FeetRemarks;
    }

    public void setCeilingHeightlessthan10FeetRemarks(String ceilingHeightlessthan10FeetRemarks) {
        this.ceilingHeightlessthan10FeetRemarks = ceilingHeightlessthan10FeetRemarks;
    }

    public String getYearWiseCollegeid() {
        return yearWiseCollegeid;
    }

    public void setYearWiseCollegeid(String yearWiseCollegeid) {
        this.yearWiseCollegeid = yearWiseCollegeid;
    }

    public String getActualArea() {
        return actualArea;
    }

    public void setActualArea(String actualArea) {
        this.actualArea = actualArea;
    }

    public String getActualareaRemarks() {
        return actualareaRemarks;
    }

    public void setActualareaRemarks(String actualareaRemarks) {
        this.actualareaRemarks = actualareaRemarks;
    }

    public int getProc_tracker() {
        return proc_tracker;
    }

    public void setProc_tracker(int proc_tracker) {
        this.proc_tracker = proc_tracker;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getIsWiresandBoardsCovered() {
        return isWiresandBoardsCovered;
    }

    public void setIsWiresandBoardsCovered(String isWiresandBoardsCovered) {
        this.isWiresandBoardsCovered = isWiresandBoardsCovered;
    }

    public String getIsFireExtinguisherAvailable() {
        return isFireExtinguisherAvailable;
    }

    public void setIsFireExtinguisherAvailable(String isFireExtinguisherAvailable) {
        this.isFireExtinguisherAvailable = isFireExtinguisherAvailable;
    }

    public String getIsEmergencyExit() {
        return isEmergencyExit;
    }

    public void setIsEmergencyExit(String isEmergencyExit) {
        this.isEmergencyExit = isEmergencyExit;
    }

    public String getIsWiresandBoardsCoveredRemarks() {
        return isWiresandBoardsCoveredRemarks;
    }

    public void setIsWiresandBoardsCoveredRemarks(String isWiresandBoardsCoveredRemarks) {
        this.isWiresandBoardsCoveredRemarks = isWiresandBoardsCoveredRemarks;
    }

    public String getIsFireExtinguisherAvailableRemarks() {
        return isFireExtinguisherAvailableRemarks;
    }

    public void setIsFireExtinguisherAvailableRemarks(String isFireExtinguisherAvailableRemarks) {
        this.isFireExtinguisherAvailableRemarks = isFireExtinguisherAvailableRemarks;
    }

    public String getIsEmergencyExitRemarks() {
        return isEmergencyExitRemarks;
    }

    public void setIsEmergencyExitRemarks(String isEmergencyExitRemarks) {
        this.isEmergencyExitRemarks = isEmergencyExitRemarks;
    }

    public int getIsWiresandBoardsCoveredNc() {
        return isWiresandBoardsCoveredNc;
    }

    public void setIsWiresandBoardsCoveredNc(int isWiresandBoardsCoveredNc) {
        this.isWiresandBoardsCoveredNc = isWiresandBoardsCoveredNc;
    }

    public int getIsFireExtinguisherAvailableNc() {
        return isFireExtinguisherAvailableNc;
    }

    public void setIsFireExtinguisherAvailableNc(int isFireExtinguisherAvailableNc) {
        this.isFireExtinguisherAvailableNc = isFireExtinguisherAvailableNc;
    }
}
