package net.gap.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gap.app.entity.FinancialEntity;
import net.gap.app.entity.GapCropsEntity;
import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.OuterGrowersEntity;
import net.gap.app.entity.ar.AuditReportEntity;
import net.gap.app.models.GapRequestFarmers;
import net.gap.app.models.UserModel;
import net.gap.app.models.ar.AuditReportModel;
import net.gap.app.models.gf.GapFormModel;
import net.gap.app.repositories.AuditReportRepo;
import net.gap.app.repositories.FinancialRepo;
import net.gap.app.repositories.GapCropsRepo;
import net.gap.app.repositories.GapFormRepo;
import net.gap.app.repositories.OuterGrowersRepo;


@Service
public class AiService {
	
	@Autowired
	private AuditReportRepo auditReportRepo;
	
	@Autowired
	private GapCropsRepo gapCropsRepo;
	
	@Autowired
	private OuterGrowersRepo outerGrowersRepo;
	
	@Autowired
	private FinancialRepo financialRepo;
	
	@Autowired
	private GapFormRepo gapFormRepo;
	
	@Autowired
	AuthenticationService authenticationService;
	
	
	// Get audit marks..... 2018/09/09
	public int getAuditMarks(Long auditId) {
			
			int x=auditReportRepo.getMarks(auditId);
			return x;
			
	}
		
		
	// Send Gapform from AI to CAB 
	public void changeStatusGapForm(Long gapFormId) {	
		
			GapFormEntity gapFormEntity = new GapFormEntity();
			gapFormEntity=gapFormRepo.getOne(gapFormId);
			gapFormEntity.setCurrentStatus("send_to_cab");
			gapFormRepo.save(gapFormEntity);
			
	}
	
	
	// Send Audit report from AI to CAB
	public Long saveAuditReport(AuditReportModel auditReportModel) {
		try {
			
			AuditReportEntity auditReportEntity= new AuditReportEntity();
			
			// Audit Report Entity Create
			
			
			auditReportEntity.setAuditorId("2324");
			auditReportEntity.setFarmerId(auditReportModel.getFarmerId());
			auditReportEntity.setGapFormId(auditReportModel.getGapFormId());
			auditReportEntity.setCurrentStatus("send_to_cab");
			
			auditReportEntity.setA1_1(auditReportModel.isA1_1());
			System.out.println(auditReportModel.toString());
			auditReportEntity.setA1_2(auditReportModel.isA1_2());
			auditReportEntity.setA1_3(auditReportModel.isA1_3());
			auditReportEntity.setA1_4(auditReportModel.isA1_4());
			auditReportEntity.setA1_5(auditReportModel.isA1_5());
			auditReportEntity.setA1_6(auditReportModel.isA1_6());
			
			auditReportEntity.setA2_1(auditReportModel.isA2_1());
			auditReportEntity.setA2_2(auditReportModel.isA2_2());
			auditReportEntity.setA2_3(auditReportModel.isA2_3());
			auditReportEntity.setA2_4(auditReportModel.isA2_4());
			auditReportEntity.setA2_5(auditReportModel.isA2_5());
			auditReportEntity.setA2_6(auditReportModel.isA2_6());
			auditReportEntity.setA2_7(auditReportModel.isA2_7());
			
			auditReportEntity.setA3_1(auditReportModel.isA3_1());
			auditReportEntity.setA3_2(auditReportModel.isA3_2());
			auditReportEntity.setA3_3(auditReportModel.isA3_3());
			
			auditReportEntity.setA4_1(auditReportModel.isA4_1());
			auditReportEntity.setA4_2(auditReportModel.isA4_2());
			auditReportEntity.setA4_2_1(auditReportModel.isA4_2_1());
			auditReportEntity.setA4_3(auditReportModel.isA4_3());
			auditReportEntity.setA4_3_1(auditReportModel.isA4_3_1());
			auditReportEntity.setA4_4(auditReportModel.isA4_4());
			auditReportEntity.setA4_5(auditReportModel.isA4_5());
			auditReportEntity.setA4_6(auditReportModel.isA4_6());
			auditReportEntity.setA4_7(auditReportModel.isA4_7());
			auditReportEntity.setA4_8(auditReportModel.isA4_8());
			auditReportEntity.setA4_9(auditReportModel.isA4_9());
			auditReportEntity.setA4_10(auditReportModel.isA4_10());
			auditReportEntity.setA4_11(auditReportModel.isA4_11());
			auditReportEntity.setA4_12(auditReportModel.isA4_12());
			auditReportEntity.setA4_13(auditReportModel.isA4_13());
			auditReportEntity.setA4_14(auditReportModel.isA4_14());
			auditReportEntity.setA4_15(auditReportModel.isA4_15());
			
			auditReportEntity.setA5_1(auditReportModel.isA5_1());
			auditReportEntity.setA5_2(auditReportModel.isA5_2());
			auditReportEntity.setA5_3(auditReportModel.isA5_3());
			auditReportEntity.setA5_4(auditReportModel.isA5_4());
			auditReportEntity.setA5_5(auditReportModel.isA5_5());
			auditReportEntity.setA5_6(auditReportModel.isA5_6());
			auditReportEntity.setA5_7(auditReportModel.isA5_7());
			
			auditReportEntity.setA6_1(auditReportModel.isA6_1());
			auditReportEntity.setA6_2(auditReportModel.isA6_2());
			auditReportEntity.setA6_3(auditReportModel.isA6_3());
			auditReportEntity.setA6_4(auditReportModel.isA6_4());
			auditReportEntity.setA6_5(auditReportModel.isA6_5());
			auditReportEntity.setA6_6(auditReportModel.isA6_6());
			auditReportEntity.setA6_7(auditReportModel.isA6_7());
			auditReportEntity.setA6_8(auditReportModel.isA6_8());
			auditReportEntity.setA6_9(auditReportModel.isA6_9());
			auditReportEntity.setA6_10(auditReportModel.isA6_10());
			auditReportEntity.setA6_11(auditReportModel.isA6_11());
			auditReportEntity.setA6_12(auditReportModel.isA6_12());
			auditReportEntity.setA6_13(auditReportModel.isA6_13());
			auditReportEntity.setA6_14(auditReportModel.isA6_14());
			auditReportEntity.setA6_15(auditReportModel.isA6_15());
			auditReportEntity.setA6_16(auditReportModel.isA6_16());
			auditReportEntity.setA6_17(auditReportModel.isA6_17());
			auditReportEntity.setA6_18(auditReportModel.isA6_18());
			auditReportEntity.setA6_19(auditReportModel.isA6_19());
			auditReportEntity.setA6_20(auditReportModel.isA6_20());
			auditReportEntity.setA6_21(auditReportModel.isA6_21());
			auditReportEntity.setA6_22(auditReportModel.isA6_22());
			
			auditReportEntity.setA7_1(auditReportModel.isA7_1());
			auditReportEntity.setA7_2(auditReportModel.isA7_2());
			auditReportEntity.setA7_3(auditReportModel.isA7_3());
			auditReportEntity.setA7_4(auditReportModel.isA7_4());
			auditReportEntity.setA7_5(auditReportModel.isA7_5());
			
			auditReportEntity.setA8_1(auditReportModel.isA8_1());
			auditReportEntity.setA8_2(auditReportModel.isA8_2());
			auditReportEntity.setA8_3(auditReportModel.isA8_3());
			auditReportEntity.setA8_4(auditReportModel.isA8_4());
			
			auditReportEntity.setA9_1(auditReportModel.isA9_1());
			auditReportEntity.setA9_2(auditReportModel.isA9_2());
			auditReportEntity.setA9_3(auditReportModel.isA9_3());
			auditReportEntity.setA9_4(auditReportModel.isA9_4());
			
			auditReportEntity.setA10_1(auditReportModel.isA10_1());
			auditReportEntity.setA10_2(auditReportModel.isA10_2());
			
			auditReportEntity.setA11_1(auditReportModel.isA11_1());
			auditReportEntity.setA11_2(auditReportModel.isA11_2());
			
			auditReportRepo.save(auditReportEntity);
			
			System.out.println(auditReportRepo.findByGapFormId(auditReportModel.getGapFormId()).getAuditId());
			
			return auditReportRepo.findByGapFormId(auditReportModel.getGapFormId()).getAuditId();
			//return (long)5;

			
		}catch(Exception ex){
			
			ex.printStackTrace();
			return null;
			
		}
	}
	
	
	//get gap form request farmers details..............
	public List<GapRequestFarmers> getAllGapFarmersToRequests(){
				
				
		GapRequestFarmers farmer;
		List<GapRequestFarmers> gap_req = new ArrayList<GapRequestFarmers>();
		UserModel userModel = new UserModel();
				
		for(GapFormEntity gapfarmers:gapFormRepo.findAll()) {
			if(gapfarmers.getCurrentStatus().equals("send_to_ai")) {
				farmer = new GapRequestFarmers();		
				userModel.setUser_id(gapfarmers.getUser_id());
				farmer.setFirst_name(authenticationService.getUserDataById(userModel).getFirst_name());
				farmer.setLast_name(authenticationService.getUserDataById(userModel).getLast_name());
				farmer.setUser_id(authenticationService.getUserDataById(userModel).getUser_id());
				farmer.setGapFormId(gapfarmers.getId());
				gap_req.add(farmer);
							
							
							
			}
		}
		return gap_req;
		
	}
	
	// get Audit report request farmers details.......... 2018/09/03
	public List<GapRequestFarmers> getAllGapFarmersToAudit(){
					
					
		GapRequestFarmers farmer;
		List<GapRequestFarmers> gap_req = new ArrayList<GapRequestFarmers>();
		UserModel userModel = new UserModel();
					
		for(GapFormEntity gapfarmers:gapFormRepo.findAll()) {
			if(gapfarmers.getCurrentStatus().equals("send_to_audit")) {
				farmer = new GapRequestFarmers();		
				userModel.setUser_id(gapfarmers.getUser_id());
				farmer.setFirst_name(authenticationService.getUserDataById(userModel).getFirst_name());
				farmer.setLast_name(authenticationService.getUserDataById(userModel).getLast_name());
				farmer.setUser_id(authenticationService.getUserDataById(userModel).getUser_id());
				farmer.setGapFormId(gapfarmers.getId());
				gap_req.add(farmer);
								
			}
		}
		return gap_req;
			
	}	
			
	//get gap form request detail specific farmer...............
	public GapFormEntity getGapFormDetail(long id){
				
		GapFormEntity gap = new GapFormEntity();
				
			try {
					
					gap = gapFormRepo.getgapformdetaild(id);
					return gap;
			}
			catch (Exception e) {
					e.printStackTrace();
					return null;
			}
				
		}
	
	// get audit report request detail specific farmer.......... 2018/09/03 
	public AuditReportEntity getAuditReportDetail(long id){
				
			try {
					
					AuditReportEntity gap = auditReportRepo.findById(id)
		                    .orElse(new AuditReportEntity());
					return gap;
					
			}
			catch (Exception e) {
					e.printStackTrace();
					return null;
			}
				
	}		
			
	//get gap crops details...........................
	public List<GapCropsEntity> getGapCropmDetail(long id){
			
		List<GapCropsEntity> gapcrop = new ArrayList<GapCropsEntity>();
						
			gapcrop = gapCropsRepo.getGapFormCropsDetail(id);
				
		return gapcrop;
	}
		
	//get outer  grower crop detail......................................
	public List<OuterGrowersEntity> getGapOuterCropDetail(long id){
		List<OuterGrowersEntity> outergorwer = new ArrayList<OuterGrowersEntity>();
			
			outergorwer = outerGrowersRepo.getGapOuterCrops(id);
			
		return outergorwer;
	}
		
	//get financial loan details.........................................
	public List<FinancialEntity> getFinancilaloanDetails(long id){
		List<FinancialEntity> financial_loan = new ArrayList<FinancialEntity>();
			
			financial_loan = financialRepo.getGapFinancialDetails(id);
				
		return financial_loan;
	}
	
}
