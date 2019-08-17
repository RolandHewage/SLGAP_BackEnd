package net.gap.app.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gap.app.entity.FinancialEntity;
import net.gap.app.entity.GapCropsEntity;
import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.OuterGrowersEntity;
import net.gap.app.entity.rb.ChemicalFertilizerEntity;
import net.gap.app.entity.rb.HarvestEntity;
import net.gap.app.entity.rb.OrganicFertilizerEntity;
import net.gap.app.entity.rb.RecordBookEntity;
import net.gap.app.models.UserModel;
import net.gap.app.models.gf.FinancialLoanModel;
import net.gap.app.models.gf.GapCropsModel;
import net.gap.app.models.gf.GapFormModel;
import net.gap.app.models.gf.OuterGrowerModel;
import net.gap.app.repositories.FinancialRepo;
import net.gap.app.repositories.GapCropsRepo;
import net.gap.app.repositories.GapFormRepo;
import net.gap.app.repositories.OuterGrowersRepo;
import net.gap.app.repositories.RecordBookRepo;
import net.gap.app.repositories.rb.ChemicalFertilizerRepo;
import net.gap.app.repositories.rb.HarvestRepo;
import net.gap.app.repositories.rb.OrganicFertilizerRepo;

@Service
public class FarmerService {

	@Autowired
	private GapFormRepo gapFormRepo;
	
	@Autowired
	private GapCropsRepo gapCropsRepo;
	
	@Autowired
	private OuterGrowersRepo outerGrowersRepo;
	
	@Autowired
	private FinancialRepo financialRepo;
	
	@Autowired
	private RecordBookRepo recordBookRepo;
	
	@Autowired
	private HarvestRepo harvestRepo;
	
	@Autowired
	private OrganicFertilizerRepo organicFertilizerRepo; 
	
	
	@Autowired
	private ChemicalFertilizerRepo chemicalFertilizerRepo; 
	
	public void saveGapForm(GapFormModel gapFormModel,UserModel userModel) {
		GapFormEntity gapFormEnity = new GapFormEntity();
		gapFormEnity.setUser_id(userModel.getUser_id());
		gapFormEnity.setProvience(gapFormModel.getProvience());
		gapFormEnity.setDistric(gapFormModel.getDistric());
		gapFormEnity.setDsDivision(gapFormModel.getDsDivision());
		gapFormEnity.setGnDivision(gapFormModel.getGnDivision());
		gapFormEnity.setArpaDivision(gapFormModel.getArpaDivision());
		gapFormEnity.setSoilType(gapFormModel.getSoilType());
		gapFormEnity.setLandOwnership(gapFormModel.getLand_ownership());
		gapFormEnity.setPlrNo(gapFormModel.getPlrNo());
		gapFormEnity.setWaterResource(gapFormModel.getWaterResource());
		gapFormEnity.setIrrigationType(gapFormModel.getIrrigationType());
		gapFormEnity.setCurrentStatus("send_to_ai");
		gapFormEnity.setAppliedDate(getCurrentDateTime());
		
		gapFormEnity = gapFormRepo.save(gapFormEnity);
		
		System.out.println(gapFormModel.getGapCropsModels());
		
		GapCropsEntity gapCropsEntity;
		for(GapCropsModel gapCropsModel : gapFormModel.getGapCropsModels()) {
			gapCropsEntity = new GapCropsEntity();
			gapCropsEntity.setGapFormId(gapFormEnity.getId());
			gapCropsEntity.setCropName(gapCropsModel.getCropName());
			gapCropsEntity.setPlaningDate(gapCropsModel.getPlanningDate());
			gapCropsEntity.setVariety(gapCropsModel.getVariety());
			gapCropsEntity.setExtent(gapCropsModel.getExtent());
			gapCropsEntity.setYield(gapCropsModel.getYield());
			
			gapCropsRepo.save(gapCropsEntity);
		}
		
		OuterGrowersEntity outerGrowersEnity;
		for(OuterGrowerModel outerGrowerModel: gapFormModel.getOuterGrowerModels()) {
			outerGrowersEnity = new OuterGrowersEntity();
			outerGrowersEnity.setGapFormId(gapFormEnity.getId());
			outerGrowersEnity.setCropName(outerGrowerModel.getCropName());
			outerGrowersEnity.setCompany(outerGrowerModel.getCompany());
			outerGrowersEnity.setExtent(outerGrowerModel.getExtent());
			outerGrowersEnity.setPeriod(outerGrowerModel.getPeriod());
			
			outerGrowersRepo.save(outerGrowersEnity);
		}
		
		FinancialEntity financialEntity;
		for(FinancialLoanModel financialLoanModel: gapFormModel.getFinancialLoanModels()) {
			financialEntity = new FinancialEntity();
			financialEntity.setGapFormId(gapFormEnity.getId());
			financialEntity.setAmount(financialLoanModel.getAmount());
			financialEntity.setLoanType(financialLoanModel.getLoanType());
			financialEntity.setBranchName(financialLoanModel.getBranchName());
			financialEntity.setInstituteName(financialLoanModel.getInstituteName());
			
			financialRepo.save(financialEntity);
			
		}
		
		
	}
	
	private String getCurrentDateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now).toString();  
	}
	
	//get all gap forms dates;
	public List<String> getAllGapFormDates(UserModel userModel){
		
		List<String> gap = new ArrayList<String>();
		gap = gapFormRepo.getAllGapForms(userModel.getUser_id());
		return gap;
		
	}
	
	//get all record book dates;
	public List<String> getAllRecordBookDates(UserModel userModel){
			
		List<String> rb = new ArrayList<String>();
		rb = recordBookRepo.getAllRecordBookDate(userModel.getUser_id());
		return rb;
			
	}
	
	//get gap from record..............
	public GapFormEntity getGapCropGapFormDetailByDate(String startDate) {
			
		return gapFormRepo.findByappliedDate(startDate);
			
	}
		
	//get  crop  record..............
	public List<GapCropsEntity> getGapCropRecords(Long gap_form_id) {
			
		return gapCropsRepo.findByGapFormId(gap_form_id);
			
	}
	
	//get outer crop  record..............
	public List<OuterGrowersEntity> getOuterGapFormDetailById(Long gap_form_id) {
					
		return outerGrowersRepo.findByGapFormId(gap_form_id);
					
	}
				
				
	//get financial  record..............
	public List<FinancialEntity> getFinancialGapFormDetailById(Long gap_form_id) {
				
		return financialRepo.findByGapFormId(gap_form_id);
					
	}	
	
	
	// Get harvest analysis.......... 2018/09/08
	public int[] getHarvestQtyForCrops(String farmerId) {
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
		System.out.print(rb_Id+"\n");
		
		List<String> crops=new ArrayList<String>();
		
		for(HarvestEntity dif_crop: harvestRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getCrop());
			System.out.print(dif_crop+"\n");
		}
		
		System.out.print(crops+"\n");
		
		int crops_size = crops.size();
		int[] qty=new int[crops.size()];
		int x=0;
		int y=0;
		
		for(HarvestEntity harvestEntity: harvestRepo.findByRecordBookIdIn(rb_Id)) {			
			if(harvestEntity.getCrop().equals(crops.get(y++))) {
				qty[x++]=(int)harvestEntity.getHarvestQty();
			}			
		}
		return qty;
	}
	
	public List<String> getAllCropNames(String farmerId){
		//List<Long> rb_Id=recordBookRepo.getRecordBookIds(farmerId);
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
	
		List<String> crops=new ArrayList<String>();
		
		for(HarvestEntity dif_crop: harvestRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getCrop());
		}
		
		return crops;
	}
	
	// Get organic fertilizer analysis.......... 2018/09/08
	public int[] getOrganicFertilizerQtyForCrops(String farmerId) {
		//List<Long> rb_Id=recordBookRepo.getRecordBookIds(farmerId);
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
		
		List<String> crops=new ArrayList<String>();
		
		for(OrganicFertilizerEntity dif_crop: organicFertilizerRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getAddCrop());
		}
		
		int crops_size = crops.size();
		int[] qty=new int[crops.size()];
		int x=0;
		
		for(OrganicFertilizerEntity organicFertilizerEntity: organicFertilizerRepo.findByRecordBookIdIn(rb_Id)) {			
			if(organicFertilizerEntity.getAddCrop().equals(crops.get(x))) {
				qty[x++]=(int)organicFertilizerEntity.getAddQty();
			}			
		}
		return qty;
	}
	
	public List<String> getAllCropNamesOrganic(String farmerId){
		//List<Long> rb_Id=recordBookRepo.getRecordBookIds(farmerId);
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
	
		List<String> crops=new ArrayList<String>();
		
		for(OrganicFertilizerEntity dif_crop: organicFertilizerRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getAddCrop());
		}
		
		return crops;
	}	
	
	
	// Get chemical fertilizer analysis.......... 2018/09/08
	public int[] getChemicalFertilizerQtyForCrops(String farmerId) {
		//List<Long> rb_Id=recordBookRepo.getRecordBookIds(farmerId);
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
		
		List<String> crops=new ArrayList<String>();
		
		for(ChemicalFertilizerEntity dif_crop: chemicalFertilizerRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getAddCrop());
		}
		
		int crops_size = crops.size();
		int[] qty=new int[crops.size()];
		int x=0;
		
		for(ChemicalFertilizerEntity chemicalFertilizerEntity: chemicalFertilizerRepo.findByRecordBookIdIn(rb_Id)) {			
			if(chemicalFertilizerEntity.getAddCrop().equals(crops.get(x))) {
				qty[x++]=(int)chemicalFertilizerEntity.getAddQty();
			}			
		}
		return qty;
	}
	
	public List<String> getAllCropNamesChemical(String farmerId){
		//List<Long> rb_Id=recordBookRepo.getRecordBookIds(farmerId);
		List<Long> rb_Id=new ArrayList<Long>();
		for(RecordBookEntity sm:recordBookRepo.getRecordBookIds(farmerId)) {
			rb_Id.add(sm.getId());
		}
	
		List<String> crops=new ArrayList<String>();
		
		for(ChemicalFertilizerEntity dif_crop: chemicalFertilizerRepo.findByRecordBookIdIn(rb_Id)) {
			crops.add(dif_crop.getAddCrop());
		}
		
		return crops;
	}		
}
