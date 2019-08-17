package net.gap.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.ar.AuditReportEntity;
import net.gap.app.models.GapRequestFarmers;
import net.gap.app.models.UserModel;
import net.gap.app.repositories.AuditReportRepo;
import net.gap.app.repositories.GapFormRepo;

@Service
public class CabService {
	
	@Autowired
	GapFormRepo gapFormRepo;
	
	@Autowired
	AuditReportRepo auditReportRepo;
	
	@Autowired
	AuthenticationService authenticationService;
	
	// Send Gapform from CAB to Audit
	public void changeStatusGapForm(Long gapFormId) {	
			
				GapFormEntity gapFormEntity = new GapFormEntity();
				gapFormEntity=gapFormRepo.getOne(gapFormId);
				gapFormEntity.setCurrentStatus("send_to_audit");
				gapFormRepo.save(gapFormEntity);
				
	}
	
	// Send Audit report from CAB to GAP
	public void changeStatusAuditReport(Long gapFormId) {	
				
				AuditReportEntity auditReportEntity = new AuditReportEntity();
				auditReportEntity=auditReportRepo.getOne(gapFormId);
				auditReportEntity.setCurrentStatus("send_to_GAP");
				auditReportRepo.save(auditReportEntity);
					
	}
		
	
	//get gap form request farmers details.......... 2018/09/03
	public List<GapRequestFarmers> getAllGapFarmersToRequests(){
					
					
			GapRequestFarmers farmer;
			List<GapRequestFarmers> gap_req = new ArrayList<GapRequestFarmers>();
			UserModel userModel = new UserModel();
					
			for(GapFormEntity gapfarmers:gapFormRepo.findAll()) {
				if(gapfarmers.getCurrentStatus().equals("send_to_cab")) {
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
					
			for(AuditReportEntity gapfarmers:auditReportRepo.findAll()) {
				if(gapfarmers.getCurrentStatus().equals("send_to_cab")) {
					farmer = new GapRequestFarmers();		
					userModel.setUser_id(gapfarmers.getFarmerId());
					farmer.setFirst_name(authenticationService.getUserDataById(userModel).getFirst_name());
					farmer.setLast_name(authenticationService.getUserDataById(userModel).getLast_name());
					farmer.setUser_id(authenticationService.getUserDataById(userModel).getUser_id());
					farmer.setGapFormId(gapfarmers.getAuditId());
					gap_req.add(farmer);																
								
				}
			}
			return gap_req;
			
	}	
	
	
	// get GAP registered farmers details.......... 2018/09/03
	public List<GapRequestFarmers> getAllGapFarmers(){
						
						
			GapRequestFarmers farmer;
			List<GapRequestFarmers> gap_req = new ArrayList<GapRequestFarmers>();
			UserModel userModel = new UserModel();
						
			for(AuditReportEntity gapfarmers:auditReportRepo.findAll()) {
				if(gapfarmers.getCurrentStatus().equals("send_to_GAP")) {
					farmer = new GapRequestFarmers();		
					userModel.setUser_id(gapfarmers.getFarmerId());
					farmer.setFirst_name(authenticationService.getUserDataById(userModel).getFirst_name());
					farmer.setLast_name(authenticationService.getUserDataById(userModel).getLast_name());
					farmer.setUser_id(authenticationService.getUserDataById(userModel).getUser_id());
					farmer.setGapFormId(gapfarmers.getAuditId());
					gap_req.add(farmer);																
									
				}
			}
			return gap_req;
				
	}
	
	//get gap form request detail specific farmer.......... 2018/09/03 
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

}
