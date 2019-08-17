package net.gap.app.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gap.app.entity.rb.ChemicalFertilizerEntity;
import net.gap.app.entity.rb.DiseaseEntity;
import net.gap.app.entity.rb.DiseasePreventEntity;
import net.gap.app.entity.rb.FertilizerPurchaseEntity;
import net.gap.app.entity.rb.HarvestEntity;
import net.gap.app.entity.rb.InsecticideEntity;
import net.gap.app.entity.rb.JoinPeatsControlEntity;
import net.gap.app.entity.rb.LabourEntity;
import net.gap.app.entity.rb.LandReportEntity;
import net.gap.app.entity.rb.OrganicFertilizerEntity;
import net.gap.app.entity.rb.RecordBookEntity;
import net.gap.app.entity.rb.RopanEntity;
import net.gap.app.entity.rb.SpoilDetailEntity;
import net.gap.app.entity.rb.UsedCropsEntity;
import net.gap.app.entity.rb.WaterSupplyDataEntity;
import net.gap.app.entity.rb.WaterSupplyEntity;
import net.gap.app.entity.rb.WeedicideEntity;
import net.gap.app.entity.rb.YedaumPerchaseEntity;
import net.gap.app.models.UserModel;
import net.gap.app.models.rb.ChemicalFertilizerModel;
import net.gap.app.models.rb.DiseaseModel;
import net.gap.app.models.rb.DiseasePreventModel;
import net.gap.app.models.rb.FertilizerPurchaseModel;
import net.gap.app.models.rb.HarvestModel;
import net.gap.app.models.rb.InsecticideModel;
import net.gap.app.models.rb.JoinPeatsControlModel;
import net.gap.app.models.rb.LabourModel;
import net.gap.app.models.rb.LandReportModel;
import net.gap.app.models.rb.OrganicFertilizerModel;
import net.gap.app.models.rb.RecordBookModel;
import net.gap.app.models.rb.RopanModel;
import net.gap.app.models.rb.SpoilDetailModel;
import net.gap.app.models.rb.UsedCropsModel;
import net.gap.app.models.rb.WaterSupplyDataModel;
import net.gap.app.models.rb.WaterSupplyModel;
import net.gap.app.models.rb.WeedicideModel;
import net.gap.app.models.rb.YedaumPerchaseModel;
import net.gap.app.repositories.RecordBookRepo;
import net.gap.app.repositories.rb.ChemicalFertilizerRepo;
import net.gap.app.repositories.rb.DiseasePreventRepo;
import net.gap.app.repositories.rb.DiseaseRepo;
import net.gap.app.repositories.rb.FertilizerPurchaseRepo;
import net.gap.app.repositories.rb.HarvestRepo;
import net.gap.app.repositories.rb.InsecticideRepo;
import net.gap.app.repositories.rb.JoinPeatsControlRepo;
import net.gap.app.repositories.rb.LabourRepo;
import net.gap.app.repositories.rb.LandReportRepo;
import net.gap.app.repositories.rb.OrganicFertilizerRepo;
import net.gap.app.repositories.rb.RopanRepo;
import net.gap.app.repositories.rb.SpoilDetailRepo;
import net.gap.app.repositories.rb.UsedCropsRepo;
import net.gap.app.repositories.rb.WaterSupplyDataRepo;
import net.gap.app.repositories.rb.WaterSupplyRepo;
import net.gap.app.repositories.rb.WeedicideRepo;
import net.gap.app.repositories.rb.YedaumPurchaseRepo;

@Service
public class RecordBookService {
	
	@Autowired
	RecordBookRepo recordBookRepo;
	
	@Autowired
	ChemicalFertilizerRepo chemicalFertilizerRepo;
	
	@Autowired
	DiseaseRepo diseaseRepo; 
	
	@Autowired
	DiseasePreventRepo diseasePreventRepo;
	
	@Autowired
	FertilizerPurchaseRepo fertilizerPurchaseRepo;
	
	@Autowired
	HarvestRepo harvestRepo;
	
	@Autowired
	InsecticideRepo insecticideRepo;
	
	@Autowired
	JoinPeatsControlRepo joinPeatsControlRepo;
	
	@Autowired
	LabourRepo labourRepo;
	
	@Autowired
	LandReportRepo landReportRepo;
	
	
	
	
	@Autowired
	YedaumPurchaseRepo yedaumPurchaseRepo;
	
	@Autowired
	WeedicideRepo weedicideRepo;
	
	@Autowired
	WaterSupplyRepo waterSupplyRepo;
	
	@Autowired
	WaterSupplyDataRepo waterSupplyDataRepo;
	
	@Autowired
	UsedCropsRepo usedCropsRepo;
	
	@Autowired
	SpoilDetailRepo spoilDetailRepo;
	
	@Autowired
	RopanRepo ropanRepo;
	
	@Autowired
	OrganicFertilizerRepo organicFertilizerRepo;
	
	private String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now).toString();  
	}

	
	public List<RecordBookEntity> getAllRecordBooks(UserModel userModel) {
		
		List<RecordBookEntity> rebooks = new ArrayList<RecordBookEntity>();
		
		for(RecordBookEntity reBookEntity:recordBookRepo.findAll()) {
			if(reBookEntity.getFarmerId().equals(userModel.getUser_id())) {
				rebooks.add(reBookEntity);
			}
		}
		
		return rebooks;
	}
	
	
	//=========create record book==============================================================	
	public boolean createRecordBook(RecordBookModel recordBookModel, UserModel userModel) {
		
		try {
			
			RecordBookEntity recordBookEntity=new RecordBookEntity();
			
			recordBookEntity.setFarmerId(userModel.getUser_id());
			recordBookEntity.setStartDate(recordBookModel.getStartDate());
			recordBookEntity.setLandArea(recordBookModel.getLandArea());
			recordBookEntity.setTrainedLabours(recordBookModel.getTrainedLabours());
			recordBookEntity.setUnTrainedLabours(recordBookModel.getUnTrainedLabours());
			recordBookEntity.setTargetMarket(recordBookModel.getTargetMarket());
			recordBookEntity.setSiteMapUrl(recordBookModel.getSiteMapUrl());
			
			recordBookRepo.save(recordBookEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	
	
	//========crete chemical fertilize table..................................................
	public boolean createChemicalFertilizer(ChemicalFertilizerModel chemicalFertilizerModel) {
		
		try {
		ChemicalFertilizerEntity chemicalFertilizerEntity = new ChemicalFertilizerEntity();
		
		chemicalFertilizerEntity.setRecordBookId(chemicalFertilizerModel.getRecordBookId());
		chemicalFertilizerEntity.setAddDate(chemicalFertilizerModel.getAddDate());
		chemicalFertilizerEntity.setFertilizer(chemicalFertilizerModel.getFertilizer());
		chemicalFertilizerEntity.setAddCrop(chemicalFertilizerModel.getAddCrop());
		chemicalFertilizerEntity.setAddQty(chemicalFertilizerModel.getAddQty());
		chemicalFertilizerEntity.setAddType(chemicalFertilizerModel.getAddType());
		chemicalFertilizerEntity.setPartNo(chemicalFertilizerModel.getPartNo());
		chemicalFertilizerEntity.setAddPerson(chemicalFertilizerModel.getAddPerson());
		chemicalFertilizerEntity.setCertifiedCompany(chemicalFertilizerModel.getCertifiedCompany());
		
		
		chemicalFertilizerRepo.save(chemicalFertilizerEntity);
		
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
		
	//=======create disease table=========================
	public boolean createDisease(DiseaseModel diseaseModel) {
			
			try {
			
			DiseaseEntity diseaseEntity = new DiseaseEntity();
			diseaseEntity.setRecordBookId(diseaseModel.getRecordBookId());
			diseaseEntity.setDisease(diseaseModel.getDisease());
			diseaseEntity.setChemical(diseaseModel.getChemical());
			diseaseEntity.setAddedDate(diseaseModel.getAddedDate());
			diseaseEntity.setQty(diseaseModel.getQty());
			diseaseEntity.setMethod(diseaseModel.getMethod());
			diseaseEntity.setCompany(diseaseModel.getCompany()); 
			diseaseEntity.setUsedName(diseaseModel.getUsedName());
			diseaseEntity.setPartNo(diseaseModel.getPartNo());
			diseaseEntity.setRegistered(diseaseModel.isRegistered());
			
			diseaseRepo.save(diseaseEntity);
					
			
			return true;
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		
	//=======create prevent table=========================
	public boolean createDisease(DiseasePreventModel diseasePreventModel) {
				
			try {
			
			DiseasePreventEntity diseasePreventEntity = new DiseasePreventEntity();
				
			diseasePreventEntity.setRecordBookId(diseasePreventModel.getRecordBookId());
			diseasePreventEntity.setCrop(diseasePreventModel.getCrop());
			diseasePreventEntity.setType(diseasePreventModel.getType());
			diseasePreventEntity.setAntiDiseaseName(diseasePreventModel.getAntiDiseaseName());
			diseasePreventEntity.setUsedSubstance(diseasePreventModel.getUsedSubstance());
			diseasePreventEntity.setRegistered(diseasePreventModel.isRegistered());
			diseasePreventEntity.setRegisteredCompany(diseasePreventModel.getRegisteredCompany());
			diseasePreventEntity.setQty(diseasePreventModel.getQty());
			diseasePreventEntity.setDate(diseasePreventModel.getDate());
			diseasePreventEntity.setMethod(diseasePreventModel.getMethod());
			diseasePreventEntity.setPartNo(diseasePreventModel.getPartNo());
			diseasePreventEntity.setUsedPerson(diseasePreventModel.getUsedPerson());
			diseasePreventEntity.setPurchaseCompany(diseasePreventModel.getPurchaseCompany());
					
			
			diseasePreventRepo.save(diseasePreventEntity);
							
					
				return true;
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	
	
	//=========create Fertilize purchase book==============================================================	
		public boolean createFertilizePurchase(FertilizerPurchaseModel fertilizerPurchaseModel) {
			
			try {
			
				FertilizerPurchaseEntity fertilizerPurchaseEntity = new FertilizerPurchaseEntity();
				
				fertilizerPurchaseEntity.setRecordBookId(fertilizerPurchaseModel.getRecordBookId());
				fertilizerPurchaseEntity.setPurchaseDate(fertilizerPurchaseModel.getPurchaseDate());
				fertilizerPurchaseEntity.setFertizer(fertilizerPurchaseModel.getFertizer());
				fertilizerPurchaseEntity.setQty(fertilizerPurchaseModel.getQty());
				fertilizerPurchaseEntity.setPurchasePlace(fertilizerPurchaseModel.getPurchasePlace());
				fertilizerPurchaseEntity.setCompany(fertilizerPurchaseModel.getCompany());
				fertilizerPurchaseEntity.setBatchNo(fertilizerPurchaseModel.getBatchNo());
				
				fertilizerPurchaseRepo.save(fertilizerPurchaseEntity);
								
				return true;
				
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	
		
	
	//=========create harvest table book==============================================================	
		public boolean createHarvest(HarvestModel harvestModel) {
					
			try {
					
				HarvestEntity harvestEntity = new HarvestEntity();
				harvestEntity.setRecordBookId(harvestModel.getRecordBookId());
				harvestEntity.setDate(harvestModel.getDate());
				harvestEntity.setCrop(harvestModel.getCrop());
				harvestEntity.setPartNo(harvestModel.getPartNo());
				harvestEntity.setHarvestQty(harvestModel.getHarvestQty());
				harvestEntity.setProductionQty(harvestModel.getHarvestQty());
				harvestEntity.setMarketLocalQty(harvestModel.getMarketLocalQty());
				harvestEntity.setMarketExportQty(harvestModel.getMarketExportQty());
						
				harvestRepo.save(harvestEntity);
						
				return true;
						
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
				
		
		//=========create Insectside table book==============================================================	
		public boolean createInsectiside(InsecticideModel insecticideModel) {
					
			try {
				InsecticideEntity insecticideEntity = new InsecticideEntity();
						
				insecticideEntity.setRecordBookId(insecticideModel.getRecordBookId());
				insecticideEntity.setInsect(insecticideModel.getInsect());
				insecticideEntity.setChemical(insecticideModel.getChemical());
				insecticideEntity.setDate(insecticideModel.getDate());
				insecticideEntity.setQty(insecticideModel.getQty());
				insecticideEntity.setMethod(insecticideModel.getMethod());
				insecticideEntity.setCompany(insecticideModel.getCompany());
				insecticideEntity.setUsedName(insecticideModel.getUsedName());
				insecticideEntity.setPartNo(insecticideModel.getPartNo());
				insecticideEntity.setRegistered(insecticideModel.isRegistered());
						
				insecticideRepo.save(insecticideEntity);
										
				return true;
						
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
				
		
		//=========create Join Peats table book==============================================================	
		public boolean createJoinPeatsControl(JoinPeatsControlModel joinPeatsControlModel) {
					
			try {
				JoinPeatsControlEntity  joinPeatsControlEntity = new JoinPeatsControlEntity();
				joinPeatsControlEntity.setRecordBookId(joinPeatsControlModel.getRecordBookId());
				joinPeatsControlEntity.setAntiPeats(joinPeatsControlModel.isAntiPeats());
				joinPeatsControlEntity.setSelectedSuitableCrop(joinPeatsControlModel.isSelectedSuitableCrop());
				joinPeatsControlEntity.setTrustedSource(joinPeatsControlModel.isTrustedSource());
				joinPeatsControlEntity.setStandardLand(joinPeatsControlModel.isStandardLand());
				joinPeatsControlEntity.setSpace(joinPeatsControlModel.isSpace());
				joinPeatsControlEntity.setUsedFertilizerRecommended(joinPeatsControlModel.isUsedFertilizerRecommended());
				joinPeatsControlEntity.setCutDown(joinPeatsControlModel.isCutDown());
				joinPeatsControlEntity.setCoveredLand(joinPeatsControlModel.isCoveredLand());
				joinPeatsControlEntity.setWild(joinPeatsControlModel.isWild());
				joinPeatsControlEntity.setDestroyed(joinPeatsControlModel.isDestroyed());
				joinPeatsControlEntity.setHarvested(joinPeatsControlModel.isHarvested());
						
				joinPeatsControlRepo.save(joinPeatsControlEntity);
						
										
				return true;
						
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
				
				
		//=========create Labour  table book==============================================================	
		public boolean createLabour(LabourModel labourModel) {
					
			try {
					
				LabourEntity labourEntity = new LabourEntity();
						
				labourEntity.setRecordBookId(labourModel.getRecordBookId());
				labourEntity.setPeriod(labourModel.getPeriod());
				labourEntity.setTrainingName(labourModel.getTrainingName());
				labourEntity.setTrainingPerson(labourModel.getTrainingPerson());
				labourEntity.setTrainingCompany(labourModel.getTrainingCompany());
				labourEntity.setOther(labourModel.getOther());
											
				labourRepo.save(labourEntity);
										
				return true;
						
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
				
		//=========create Land Report table book==============================================================	
		public boolean createLandReport(LandReportModel landReportModel, UserModel userModel) {
					
			try {
					
				LandReportEntity landReportEntity = new LandReportEntity();
						
				landReportEntity.setRecordBookId(landReportModel.getRecordBookId());
				landReportEntity.setLandOwn(landReportModel.getLandOwn());
				landReportEntity.setLandOwn(landReportModel.getLandOwn());
				landReportEntity.setHospitalBefore(landReportModel.isHospitalBefore());
				landReportEntity.setSoilLow(landReportModel.isSoilLow());
				landReportEntity.setSoilProtect(landReportModel.isSoilProtect());
				landReportEntity.setBeforeGrow(landReportModel.isBeforeGrow());
				landReportEntity.setCrop(landReportModel.getCrop());
				landReportEntity.setChemical(landReportModel.getChemical());
						
				landReportRepo.save(landReportEntity);
										
				return true;
					
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
	
	
	
	
	
	
	
	
	
	
	// Create yedaum purchase table
	public boolean createYedaumPurchase(YedaumPerchaseModel yedaumPerchaseModel) {
		
		try {
			
			YedaumPerchaseEntity yedaumPerchaseEntity=new YedaumPerchaseEntity();
			
			yedaumPerchaseEntity.setRecordBookId(yedaumPerchaseModel.getRecordBookId());
			yedaumPerchaseEntity.setPerchaseDate(yedaumPerchaseModel.getPerchaseDate());
			yedaumPerchaseEntity.setType(yedaumPerchaseModel.getType());
			yedaumPerchaseEntity.setQty(yedaumPerchaseModel.getQty());
			yedaumPerchaseEntity.setPurchasePlace(yedaumPerchaseModel.getPurchasePlace());
			yedaumPerchaseEntity.setBatchNo(yedaumPerchaseModel.getProductData());
			yedaumPerchaseEntity.setProductData(yedaumPerchaseModel.getProductData());
			yedaumPerchaseEntity.setExpireDate(yedaumPerchaseModel.getExpireDate());
			yedaumPerchaseEntity.setCompany(yedaumPerchaseModel.getCompany());
			
			yedaumPurchaseRepo.save(yedaumPerchaseEntity);
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	// Create weedicide table
	public boolean createWeedicide(WeedicideModel weedicideModel) {
		
		try {
			WeedicideEntity weedicideEntity=new WeedicideEntity();
			
			weedicideEntity.setRecordBookId(weedicideModel.getRecordBookId());
			weedicideEntity.setWeeds(weedicideModel.getWeeds());
			weedicideEntity.setChemical(weedicideModel.getChemical());
			weedicideEntity.setAddedDate(weedicideModel.getAddedDate());
			weedicideEntity.setQty(weedicideModel.getQty());
			weedicideEntity.setMethod(weedicideModel.getMethod());
			weedicideEntity.setCompany(weedicideModel.getCompany());
			weedicideEntity.setUsedName(weedicideModel.getUsedName());
			weedicideEntity.setPartNo(weedicideModel.getPartNo());
			weedicideEntity.setRegistered(weedicideModel.isRegistered());
			
			weedicideRepo.save(weedicideEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
	}
	
	// Create water supply table
	public boolean createWaterSupply(WaterSupplyModel waterSupplyModel) {
		
		try {
			
			WaterSupplyEntity waterSupplyEntity=new WaterSupplyEntity();
			
			waterSupplyEntity.setRecordBookId(waterSupplyModel.getRecordBookId());
			waterSupplyEntity.setDirtyWater(waterSupplyModel.isDirtyWater());
			waterSupplyEntity.setGetWaterPokuna(waterSupplyModel.isGetWaterPokuna());
			waterSupplyEntity.setThread(waterSupplyModel.isThread());
			waterSupplyEntity.setWaterAnalysis(waterSupplyModel.isWaterAnalysis());
			
			waterSupplyRepo.save(waterSupplyEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
	}
	
	// Create water supply data table
	public boolean createWaterSupplyData(WaterSupplyDataModel waterSupplyDataModel) {
		
		try {
			
			WaterSupplyDataEntity waterSupplyDataEntity=new WaterSupplyDataEntity();
			
			waterSupplyDataEntity.setRecordBookId(waterSupplyDataModel.getRecordBookId());
			waterSupplyDataEntity.setDate(waterSupplyDataModel.getDate());
			waterSupplyDataEntity.setCrop(waterSupplyDataModel.getCrop());
			waterSupplyDataEntity.setWaterSource(waterSupplyDataModel.getWaterSource());
			waterSupplyDataEntity.setMethod(waterSupplyDataModel.getMethod());
			waterSupplyDataEntity.setPartNo(waterSupplyDataModel.getPartNo());
			waterSupplyDataEntity.setUsedPerson(waterSupplyDataModel.getUsedPerson());
			
			waterSupplyDataRepo.save(waterSupplyDataEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	// Create used crops
	public boolean createUsedCrops(UsedCropsModel usedCropsModel) {
		
		try {
			
			UsedCropsEntity usedCropsEntity=new UsedCropsEntity();
			
			usedCropsEntity.setRecordBookId(usedCropsModel.getRecordBookId());
			usedCropsEntity.setGrowDate(usedCropsModel.getGrowDate());
			usedCropsEntity.setCrop(usedCropsModel.getCrop());
			usedCropsEntity.setType(usedCropsModel.getType());
			usedCropsEntity.setQty(usedCropsModel.getQty());
			usedCropsEntity.setCertifiedDetail(usedCropsModel.getCertifiedDetail());
			usedCropsEntity.setTreatDetails(usedCropsModel.getTreatDetails());
			
			usedCropsRepo.save(usedCropsEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
		
	}
	
	// Create spoil detail
	public boolean createSpoilDetail(SpoilDetailModel spoilDetailModel) {
		
		try {
			
			SpoilDetailEntity spoilDetailEntity= new SpoilDetailEntity();
			
			spoilDetailEntity.setRecordBookId(spoilDetailModel.getRecordBookId());
			spoilDetailEntity.setDate(spoilDetailModel.getDate());
			spoilDetailEntity.setCrop(spoilDetailModel.getCrop());
			spoilDetailEntity.setSpoilQty(spoilDetailModel.getSpoilQty());
			spoilDetailEntity.setReason(spoilDetailModel.getReason());
			
			spoilDetailRepo.save(spoilDetailEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
	}
	
	// Create ropan table
	public boolean createRopan(RopanModel ropanModel) {
		
		try {
			
			RopanEntity ropanEntity=new RopanEntity();
			ropanEntity.setRecordBookId(ropanModel.getRecordBookId());
			ropanEntity.setGrowDate(ropanModel.getGrowDate());
			ropanEntity.setCrop(ropanModel.getCrop());
			ropanEntity.setType(ropanModel.getType());
			ropanEntity.setQty(ropanModel.getQty());
			ropanEntity.setChemicalTreat(ropanModel.getChemicalTreat());
			ropanEntity.setPlace(ropanModel.getPlace());
			ropanEntity.setCertifiedcCompany(ropanModel.getCertifiedcCompany());
			
			ropanRepo.save(ropanEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
	}
	
	// Create organic fertilizer
	public boolean createOrganicFertilizer(OrganicFertilizerModel organicFertilizerModel) {
		
		try {
			
			OrganicFertilizerEntity organicFertilizerEntity=new OrganicFertilizerEntity();
			organicFertilizerEntity.setRecordBookId(organicFertilizerModel.getRecordBookId());
			organicFertilizerEntity.setAddDate(organicFertilizerModel.getAddDate());
			organicFertilizerEntity.setFertilizer(organicFertilizerModel.getFertilizer());
			organicFertilizerEntity.setAddCrop(organicFertilizerModel.getAddCrop());
			organicFertilizerEntity.setAddQty(organicFertilizerModel.getAddQty());
			organicFertilizerEntity.setAddType(organicFertilizerModel.getAddType());
			organicFertilizerEntity.setPartNo(organicFertilizerModel.getPartNo());
			organicFertilizerEntity.setAddPerson(organicFertilizerModel.getAddPerson());
			organicFertilizerEntity.setCertifiedCompany(organicFertilizerModel.getCertifiedCompany());
			
			organicFertilizerRepo.save(organicFertilizerEntity);
			
			return true;
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;		
		}
	}
	
	
	// Get Record Book by startDate 
	public RecordBookEntity getRecordBookDetailsMethod(String startDate) {

		return recordBookRepo.findByStartDate(startDate); 
		
	}	

	// Get record book Id by using record book date and user id
	public Long getRecordBookId(UserModel user,RecordBookModel recordBookModel) {
		
		System.out.println(recordBookModel.getStartDate());
		System.out.println(user.getUser_id());
		return recordBookRepo.getRecordFarmerId(user.getUser_id(),recordBookModel.getStartDate());
	}
	
	// Get land report details=============================================
	public LandReportEntity getRecordBookDetail(Long recordBookId) {
						
		return landReportRepo.findByRecordBookId(recordBookId);
		
	}
	
	// Get User crop details=================
	public List<UsedCropsEntity> getUsedCropDetalis(Long recordBookId) {

		List<UsedCropsEntity> crops = new ArrayList<UsedCropsEntity>();

		crops = usedCropsRepo.findByRecordBookId(recordBookId);
		return crops;
		
	}
	
	// Get ropana details=================
	public List<RopanEntity> getRopanaDetalis(Long recordBookId) {
		
		List<RopanEntity> ropana = new ArrayList<RopanEntity>();

		ropana = ropanRepo.findByRecordBookId(recordBookId);
		return ropana;

	}
	
	// Get fertilize purchase details========
	public List<FertilizerPurchaseEntity> getfertilizePurchase(Long recordBookId) {
		List<FertilizerPurchaseEntity> fer_per = new ArrayList<FertilizerPurchaseEntity>();


		fer_per = fertilizerPurchaseRepo.findByRecordBookId(recordBookId);
		return fer_per;
	}
	
	// Get organic fertilse details
	public List<OrganicFertilizerEntity> getOrganicfertilize(Long recordBookId) {

		List<OrganicFertilizerEntity> organic = new ArrayList<OrganicFertilizerEntity>();
		organic = organicFertilizerRepo.findByRecordBookId(recordBookId);
		return organic;
	}

	// Get chemical fertilize details
	public List<ChemicalFertilizerEntity> getChemicalfertilize(Long recordBookId) {
		List<ChemicalFertilizerEntity> chemical = new ArrayList<ChemicalFertilizerEntity>();


		chemical = chemicalFertilizerRepo.findByRecordBookId(recordBookId);
		return chemical;

	}
	
	// Get Join peats details
	public List<JoinPeatsControlEntity> getJoinPeats(Long recordBookId) {
		List<JoinPeatsControlEntity> join = new ArrayList<JoinPeatsControlEntity>();


		join = joinPeatsControlRepo.findByRecordBookId(recordBookId);
		return join;

	}
	
	// Get disease details
	public List<DiseaseEntity> getDisease(Long recordBookId) {

		List<DiseaseEntity> diases = new ArrayList<DiseaseEntity>();


		diases = diseaseRepo.findByRecordBookId(recordBookId);
		return diases;
	}
	
	// Get weedicide details
	public List<WeedicideEntity> getweedeside(Long recordBookId) {

		List<WeedicideEntity> diases = new ArrayList<WeedicideEntity>();


		diases = weedicideRepo.findByRecordBookId(recordBookId);
		return diases;
	}
	
	// Get insecticide details
	public List<InsecticideEntity> getInsectiside(Long recordBookId) {

		List<InsecticideEntity> diases = new ArrayList<InsecticideEntity>();


		diases = insecticideRepo.findByRecordBookId(recordBookId);
		return diases;
	}

	// Get water supply details
	public List<WaterSupplyEntity> getWaterResource(Long recordBookId) {
		List<WaterSupplyEntity> water = new ArrayList<WaterSupplyEntity>();


		water = waterSupplyRepo.findByRecordBookId(recordBookId);
		return water;
	}

	// Get water supply data details
	public List<WaterSupplyDataEntity> getWaterSupplyMethod(Long recordBookId) {
		List<WaterSupplyDataEntity> supply = new ArrayList<WaterSupplyDataEntity>();


		supply = waterSupplyDataRepo.findByRecordBookId(recordBookId);
		return supply;
	}

	// Get harvest details
	public List<HarvestEntity> getHarvestMethod(Long recordBookId) {
		List<HarvestEntity> supply = new ArrayList<HarvestEntity>();
		supply = harvestRepo.findByRecordBookId(recordBookId);
		return supply;
	}

	// Get spoil details
	public List<SpoilDetailEntity> getDamageHarvestMethod(Long recordBookId) {
		List<SpoilDetailEntity> supply = new ArrayList<SpoilDetailEntity>();
		supply = spoilDetailRepo.findByRecordBookId(recordBookId);
		return supply;
	}


	
	
	
	
	

}
