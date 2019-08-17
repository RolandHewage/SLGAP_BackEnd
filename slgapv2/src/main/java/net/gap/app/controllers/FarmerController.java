package net.gap.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.gap.app.entity.FinancialEntity;
import net.gap.app.entity.GapCropsEntity;
import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.OuterGrowersEntity;
import net.gap.app.entity.rb.ChemicalFertilizerEntity;
import net.gap.app.entity.rb.DiseaseEntity;
import net.gap.app.entity.rb.FertilizerPurchaseEntity;
import net.gap.app.entity.rb.HarvestEntity;
import net.gap.app.entity.rb.InsecticideEntity;
import net.gap.app.entity.rb.JoinPeatsControlEntity;
import net.gap.app.entity.rb.LandReportEntity;
import net.gap.app.entity.rb.OrganicFertilizerEntity;
import net.gap.app.entity.rb.RecordBookEntity;
import net.gap.app.entity.rb.RopanEntity;
import net.gap.app.entity.rb.SpoilDetailEntity;
import net.gap.app.entity.rb.UsedCropsEntity;
import net.gap.app.entity.rb.WaterSupplyDataEntity;
import net.gap.app.entity.rb.WaterSupplyEntity;
import net.gap.app.entity.rb.WeedicideEntity;
import net.gap.app.models.ResponseModel;
import net.gap.app.models.UserModel;
import net.gap.app.models.gf.GapFormModel;
import net.gap.app.models.rb.ChemicalFertilizerModel;
import net.gap.app.models.rb.DiseaseModel;
import net.gap.app.models.rb.FertilizerPurchaseModel;
import net.gap.app.models.rb.HarvestModel;
import net.gap.app.models.rb.InsecticideModel;
import net.gap.app.models.rb.JoinPeatsControlModel;
import net.gap.app.models.rb.LandReportModel;
import net.gap.app.models.rb.OrganicFertilizerModel;
import net.gap.app.models.rb.RecordBookModel;
import net.gap.app.models.rb.RopanModel;
import net.gap.app.models.rb.SpoilDetailModel;
import net.gap.app.models.rb.UsedCropsModel;
import net.gap.app.models.rb.WaterSupplyDataModel;
import net.gap.app.models.rb.WaterSupplyModel;
import net.gap.app.models.rb.WeedicideModel;
import net.gap.app.services.FarmerService;
import net.gap.app.services.RecordBookService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/farmer")
public class FarmerController extends SecureRoute {
	
	
	@Autowired
	private FarmerService farmerService;
	
	@Autowired
	private RecordBookService recordBookService;

	@RequestMapping("/hello")
	public ResponseEntity<?> helloUser(){
		return new ResponseEntity<Object>(new ResponseModel(200,"You are autherized user",null),HttpStatus.OK);
	}
	
	@RequestMapping("/userinfo")
	public ResponseEntity<?> userInfo(HttpServletRequest request){
		UserModel user = super.getUserModel(request);
		return new ResponseEntity<Object>(new ResponseModel(200,"User Info",user),HttpStatus.OK);
	}
	
	@RequestMapping("/submit-gap-form")
	public ResponseEntity<?> submitGapForm(@RequestBody GapFormModel gapFormModel,HttpServletRequest request){
		farmerService.saveGapForm(gapFormModel, super.getUserModel(request));
		return null;
	}
	
	//get gap form dates===================================
		@RequestMapping("/getFormDates")
		public List<String> getAllGapFormDates(HttpServletRequest request){
			UserModel user = super.getUserModel(request);
			
			return farmerService.getAllGapFormDates(user);
		}
		
		//get gap from detail according to gap form date
		@RequestMapping("/getFormDetails")
		public GapFormEntity getGapFormRecordsByDate(@RequestParam String startDate,HttpServletRequest request){
			UserModel user = super.getUserModel(request);
			
			 return farmerService.getGapCropGapFormDetailByDate(startDate);
			
			
		}
		
		//get gap crop  details=======================
		@RequestMapping("/getGapCropDetails")
		public List<GapCropsEntity> getGapCropGapFormRecordsByDate(@RequestParam Long gap_form_id ,HttpServletRequest request){
			UserModel user = super.getUserModel(request);
			
			 return farmerService.getGapCropRecords(gap_form_id);
			
			
		}
		
		//get outer gap crop  details=======================
			@RequestMapping("/getOuterCropDetails")
			public List<OuterGrowersEntity> getOuterGapFormRecordsByDate(@RequestParam Long gap_form_id,HttpServletRequest request){
				UserModel user = super.getUserModel(request);
				
				 return farmerService.getOuterGapFormDetailById(gap_form_id);
				
				
			}
		

			//get financial gap crop  details=======================
				@RequestMapping("/getFinancialDetails")
				public List<FinancialEntity> getFinancialGapFormRecordsByDate(@RequestParam Long gap_form_id,HttpServletRequest request){
					UserModel user = super.getUserModel(request);
					
					 return farmerService.getFinancialGapFormDetailById(gap_form_id);
					
					
				}	
			
		//get rocord book  date===========================
		@RequestMapping("/getRerordBookDates")
		public List<String> getAllGapRecordBookDates(HttpServletRequest request){
			UserModel user = super.getUserModel(request);
			
			return farmerService.getAllRecordBookDates(user);
		}
		
		//create new record book====================
		@RequestMapping("/recordBook")
		public ResponseEntity<?> createRecordBook(@RequestBody RecordBookModel recordBookModel,HttpServletRequest request){
			
			UserModel user = super.getUserModel(request);
			
			if(recordBookService.createRecordBook(recordBookModel, user)) {
				return new ResponseEntity<Object>(new ResponseModel(200,"Create Record Book successfull",null),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(new ResponseModel(400,"Record Book Create Fail",null),HttpStatus.BAD_REQUEST);
			}
			
		}
		
		
		
		//==========get record book Id by usering date and user id=========================
		@RequestMapping("/recordBook-Id")
		public Long getRecordBookIdByDateAndUserId(@RequestBody RecordBookModel recordBookModel,HttpServletRequest request) {
			
			try {
			
				UserModel user = super.getUserModel(request);
				
				
			     Long rb = recordBookService.getRecordBookId( user,recordBookModel);
				
				return rb;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		//land report detail=========================================
		@RequestMapping("/land-report")
		public ResponseEntity<?> createLandReportTable(@RequestBody LandReportModel landReportModel ,HttpServletRequest request){
			
			UserModel user = super.getUserModel(request);
			
			if(recordBookService.createLandReport(landReportModel, user)) {
				return new ResponseEntity<Object>(new ResponseModel(200,"Land Report Create successfull",null),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Object>(new ResponseModel(400,"Land Report Create Create Fail",null),HttpStatus.BAD_REQUEST);
			}
			
		}
		
		//==========get record book Id by using date and user id=========================
			@RequestMapping("/landReportdetail")
			public LandReportEntity getRecordBookIdByDate(@RequestParam Long recordBookId,UserModel userModel) {
				
				try {
				
					
					LandReportEntity landReportModelreturn = new LandReportEntity();
					
					landReportModelreturn = recordBookService.getRecordBookDetail(recordBookId);
					
					return landReportModelreturn;
				}
				catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
				
			}
			
		//save chemical & fertilize perchase detailss==========================
			@RequestMapping("/fertilize-purchase")
			public ResponseEntity<?> createFertilizePurchaseTable(@RequestBody FertilizerPurchaseModel landReportModel ,HttpServletRequest request){
				
				UserModel user = super.getUserModel(request);
				
				if(recordBookService.createFertilizePurchase(landReportModel)) {
					return new ResponseEntity<Object>(new ResponseModel(200,"Fertilize purchase add successfull",null),HttpStatus.OK);
				}
				else {
					return new ResponseEntity<Object>(new ResponseModel(400,"Fertilize purchase add Fail",null),HttpStatus.BAD_REQUEST);
				}
				
			}
			
		//save organic fertilize detailss==========================
					@RequestMapping("/organic-fertilize")
					public ResponseEntity<?> createOrganicFertilizer(@RequestBody OrganicFertilizerModel organicFertilizerModel ,HttpServletRequest request){
						
						UserModel user = super.getUserModel(request);
						
						if(recordBookService.createOrganicFertilizer(organicFertilizerModel)) {
							return new ResponseEntity<Object>(new ResponseModel(200,"Organic Fertilize add successfull",null),HttpStatus.OK);
						}
						else {
							return new ResponseEntity<Object>(new ResponseModel(400,"Organic Fertilize add Fail",null),HttpStatus.BAD_REQUEST);
						}
						
					}
					

		//save chemical fertilize detailss==========================
						@RequestMapping("/chemical-fertilize")
							public ResponseEntity<?> createChemicalFertilizer(@RequestBody ChemicalFertilizerModel chemicalFertilizerModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createChemicalFertilizer(chemicalFertilizerModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"chemical Fertilize add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"chemical Fertilize add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
			
		//save harvest detailss==========================
				@RequestMapping("/harvest")
				public ResponseEntity<?> createHarvestTable(@RequestBody HarvestModel harvestModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createHarvest(harvestModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"harvest detail add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"harvest detail add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
		//save  damage harvest detailss==========================
							@RequestMapping("/harvest-damage")
							public ResponseEntity<?> createSpoilDetailTable(@RequestBody SpoilDetailModel spoilDetailModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createSpoilDetail(spoilDetailModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"chemical Fertilize add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"chemical Fertilize add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
		//save  water supply details ==========================
							@RequestMapping("/water-supply-detail")
							public ResponseEntity<?> createWaterSupplyDetails(@RequestBody WaterSupplyModel waterSupplyModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createWaterSupply(waterSupplyModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"water supply record add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"water supply record add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
		//save water supply methods details==========================
							@RequestMapping("/water-supply")
							public ResponseEntity<?> createWaterSupplyMethods(@RequestBody WaterSupplyDataModel waterSupplyDataModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createWaterSupplyData(waterSupplyDataModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"water supply methods add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"water supply methods add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
		//create used crops details		
							@RequestMapping("/used-crop")
							public ResponseEntity<?> createUsedCrops(@RequestBody UsedCropsModel usedCropsModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createUsedCrops(usedCropsModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"Used crop record add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"Used crop record add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}		
							
		//create ropan details		
					@RequestMapping("/ropana")
					public ResponseEntity<?> createUsedCrops(@RequestBody RopanModel ropanModel ,HttpServletRequest request){
							
						UserModel user = super.getUserModel(request);
						
						if(recordBookService.createRopan(ropanModel)) {
								return new ResponseEntity<Object>(new ResponseModel(200,"Ropana record add successfull",null),HttpStatus.OK);
						}
							else {
								return new ResponseEntity<Object>(new ResponseModel(400,"Ropana record add Fail",null),HttpStatus.BAD_REQUEST);
						}
							
						}								
				
		//create join peats details		
		@RequestMapping("/join-peats")
		public ResponseEntity<?> createUsedCrops(@RequestBody JoinPeatsControlModel joinPeatsControlModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createJoinPeatsControl(joinPeatsControlModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"Join Peats  record add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"Join Peats   record add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
					
													
		//create disease details		
		@RequestMapping("/deases")
		public ResponseEntity<?> createdeases(@RequestBody DiseaseModel diseaseModel,HttpServletRequest request){
								
				UserModel user = super.getUserModel(request);
								
					if(recordBookService.createDisease(diseaseModel)) {
							return new ResponseEntity<Object>(new ResponseModel(200,"Disease Model record add successfull",null),HttpStatus.OK);
					}
					else {
							return new ResponseEntity<Object>(new ResponseModel(400,"Disease Model record add Fail",null),HttpStatus.BAD_REQUEST);
					}
								
			}
							
							
		//create weediside details		
							@RequestMapping("/weedeside")
							public ResponseEntity<?> createWeedeside(@RequestBody WeedicideModel weedicideModel  ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createWeedicide(weedicideModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"weedicide Model  record add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"weedicide Model   record add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
							
							
							
		//create insectiside details		
							@RequestMapping("/insectiside")
							public ResponseEntity<?> createInsectiside(@RequestBody InsecticideModel insecticideModel ,HttpServletRequest request){
								
								UserModel user = super.getUserModel(request);
								
								if(recordBookService.createInsectiside(insecticideModel)) {
									return new ResponseEntity<Object>(new ResponseModel(200,"Insectiside record add successfull",null),HttpStatus.OK);
								}
								else {
									return new ResponseEntity<Object>(new ResponseModel(400,"Insectiside record add Fail",null),HttpStatus.BAD_REQUEST);
								}
								
							}
		
		
		
		//get used crops details
		@RequestMapping("/getusedcrops")					
		public List<UsedCropsEntity> getUsedCropDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
			
			return recordBookService.getUsedCropDetalis(recordBookId);
		}
		
		
		//get used crops details
			@RequestMapping("/getropana")					
			public List<RopanEntity> getRopanaDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
				
				return recordBookService.getRopanaDetalis(recordBookId);
			}
			
			//get used crops details
			@RequestMapping("/get-fertilize-purchase")					
			public List<FertilizerPurchaseEntity> getFertilizepurchaseDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
						
				return recordBookService.getfertilizePurchase(recordBookId);
			}
		
			//get organic fertilsze details
			@RequestMapping("/get-organic-fertilize")					
			public List<OrganicFertilizerEntity> getOrganicFertilizeDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
								
				return recordBookService.getOrganicfertilize(recordBookId);
			}
			
			//get organic fertilsze details
					@RequestMapping("/get-chemical-fertilize")					
					public List<ChemicalFertilizerEntity> getChemicalFertilizeDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getChemicalfertilize(recordBookId);
					}
					
			//get Join peats========
					@RequestMapping("/get-join-peats")					
					public List<JoinPeatsControlEntity> getJoinPeatsDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getJoinPeats(recordBookId);
					}
			
					//get disease method details========
					@RequestMapping("/get-disease")					
					public List<DiseaseEntity> getDiseaseDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getDisease(recordBookId);
					}
					
					//get weedeside details========
					@RequestMapping("/get-weedeside")					
					public List<WeedicideEntity> getWeedesideDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getweedeside(recordBookId);
					}	
					
					//get insecttiside details========
					@RequestMapping("/get-insectiside")					
					public List<InsecticideEntity> getInsectiseideDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getInsectiside(recordBookId);
					}
					
					//get water suppy================
					@RequestMapping("/get-water")					
					public List<WaterSupplyEntity> getWaterDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getWaterResource(recordBookId);
					}
					
					//get water supply data=============
					@RequestMapping("/get-supply")					
					public List<WaterSupplyDataEntity> getWaterSupplydeDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getWaterSupplyMethod(recordBookId);
					}
					
					//get Harvest data=============
					@RequestMapping("/get-harvest")					
					public List<HarvestEntity> getHarverstDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getHarvestMethod(recordBookId);
					}
					
					//get damage harvest data=============
					@RequestMapping("/get-harvest-damage")					
					public List<SpoilDetailEntity> getDamageHarvetsDetails(@RequestParam Long recordBookId,HttpServletRequest request) {
										
						return recordBookService.getDamageHarvestMethod(recordBookId);
					}
					
					
					//get record book info................
					@RequestMapping("/get-record-book-details")					
					public RecordBookEntity getRecordBookDetails(@RequestParam String startDate,HttpServletRequest request) {
										
						return recordBookService.getRecordBookDetailsMethod(startDate);
					}
					
					
					// Get harvest qty for analysis..... 2018/09/08
					@RequestMapping("/harvestAnalysis")
					public int[] getHarvestQty(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getHarvestQtyForCrops(user.getUser_id());
					}
					
					// Get crop names for analysis..... 2018/09/08
					@RequestMapping("/harvestAnalysisCrops")
					public List<String> getHarvestCrops(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getAllCropNames(user.getUser_id());
					}
					
					// Get organic fertilizer qty for analysis..... 2018/09/08
					@RequestMapping("/organicFertilizerAnalysis")
					public int[] getOrganicFertilizerQty(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getOrganicFertilizerQtyForCrops(user.getUser_id());
					}
					
					// Get crop names for analysis..... 2018/09/08
					@RequestMapping("/organicFertilizerAnalysisCrops")
					public List<String> getOrganicFertilizerCrops(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getAllCropNamesOrganic(user.getUser_id());
					}
					
					// Get chemical fertilizer qty for analysis..... 2018/09/08
					@RequestMapping("/chemicalFertilizerAnalysis")
					public int[] getChemicalFertilizerQty(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getChemicalFertilizerQtyForCrops(user.getUser_id());
					}
					
					// Get crop names for analysis..... 2018/09/08
					@RequestMapping("/chemicalFertilizerAnalysisCrops")
					public List<String> getChemicalFertilizerCrops(HttpServletRequest request){
						UserModel user = super.getUserModel(request);
						
						return farmerService.getAllCropNamesChemical(user.getUser_id());
					}
					
					
	
	
	
	
}
