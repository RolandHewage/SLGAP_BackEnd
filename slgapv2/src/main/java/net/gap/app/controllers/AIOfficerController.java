package net.gap.app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

import net.gap.app.entity.FinancialEntity;
import net.gap.app.entity.GapCropsEntity;
import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.OuterGrowersEntity;
import net.gap.app.models.GapRequestFarmers;
import net.gap.app.models.ar.AuditReportModel;
import net.gap.app.models.gf.GapFormModel;
import net.gap.app.services.AiService;
import net.gap.app.services.FarmerService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("/ai-officer")
public class AIOfficerController extends SecureRoute {
	
	@Autowired
	AiService aiService;
	
	@Autowired
	FarmerService farmerService;
	
	@Autowired
	ApplicationContext applicationContext; 
	
	@RequestMapping(value = "helloReport1", method = RequestMethod.GET)
	  @ResponseBody
	  public void getRpt1(HttpServletResponse response) throws JRException, IOException {
	    InputStream jasperStream = this.getClass().getResourceAsStream("/jasperreports/HelloWorld1.jasper");
	    Map<String,Object> params = new HashMap<>();
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());

	    response.setContentType("application/x-pdf");
	    response.setHeader("Content-disposition", "inline; filename=helloWorldReport.pdf");

	    final ServletOutputStream outStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	  }
	
//	@Bean @Qualifier("helloWorldReport2")
//	public JasperReportsPdfView getHelloWorldReport() {
//	  JasperReportsPdfView v = new JasperReportsPdfView();
//	  v.setUrl("classpath:jasperreports/HelloWorld2.jasper");
//	  v.setReportDataKey("datasource");
//	  return v;
//	}

	//get marks..... 2018/09/09
	@RequestMapping(value="/marks",method=RequestMethod.GET)
	public int getMarks(@RequestParam long aid){
			
		return aiService.getAuditMarks(aid);
				
	}
	
	//get all gap farmers........................................
	@RequestMapping(value="/gapfarmers",method=RequestMethod.GET)
	public List<GapRequestFarmers> getallfarmers(){
		
		return aiService.getAllGapFarmersToRequests();
			
	}
	
	//get specific gap form details...............................
	@RequestMapping(value="/gapformdetail",method=RequestMethod.GET)
	public GapFormEntity getGapFormDetail(@RequestParam long gapForm_id) {
		
		return aiService.getGapFormDetail(gapForm_id);
			//return null;
	}
	
	//get crop detail and show AI request table
	@RequestMapping(value="/getcrops",method=RequestMethod.GET)
	public List<GapCropsEntity> getgapcropdetail(@RequestParam long gapForm_id){
			
		List<GapCropsEntity> gapcrop = new ArrayList<GapCropsEntity>();
			
		gapcrop  = aiService.getGapCropmDetail(gapForm_id);
	
		return gapcrop;
	}
	
	//get outer crop detail and show AI request table
	@RequestMapping(value="/outercrop",method=RequestMethod.GET)
		public List<OuterGrowersEntity> getgapoutercropdetail(@RequestParam long gapForm_id){
			
			List<OuterGrowersEntity> gapcrop = new ArrayList<OuterGrowersEntity>();
			
			gapcrop  = aiService.getGapOuterCropDetail(gapForm_id);
	
		return gapcrop;
	}
	
	//get fiancial detail and show AI request table
	@RequestMapping(value="/financial",method=RequestMethod.GET)
	public List<FinancialEntity> getgapfinancialdetail(@RequestParam long gapForm_id){
				
			List<FinancialEntity> gapcrop = new ArrayList<FinancialEntity>();
			
			gapcrop  = aiService.getFinancilaloanDetails(gapForm_id);
		
		return gapcrop;
	}
	
	
	// Send Audit report from AI to CAB.......... 2018/09/06
	@RequestMapping(value="/submitAuditReport",method=RequestMethod.POST)
	public Long submitGapForm(@RequestBody AuditReportModel auditReportModel){
		
		return aiService.saveAuditReport(auditReportModel);
	}
	
	
//	//gap from send to CAD Officer
//		@RequestMapping(value="/formsendtocab",method=RequestMethod.GET)
//		public ResponseEntity<?> gapfomsendtocab(@RequestParam long gapForm_id){
//			if(aiService.gapFromSendToCab(gapForm_id)) {
//					return new ResponseEntity("Gap Form send successfull",HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity("Gap from send fail",HttpStatus.BAD_REQUEST);
//			}
//		}

}
