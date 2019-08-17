package net.gap.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.gap.app.entity.GapFormEntity;
import net.gap.app.services.AiService;
import net.gap.app.services.CabService;

@RestController
@RequestMapping("/cab-officer")
public class CabOfficerController {

	@Autowired
	CabService cabservice;
	
//	//get all gap farmers
//	@RequestMapping(value="/gapfarmers",method=RequestMethod.GET)
//	public List<GapFormEntity> getAllGapFarmers(){
//		
//		return cabservice.getAllGapFarmers();
//	}
	
//	//gap from send to Audit
//			@RequestMapping(value="/formsendtoaudit",method=RequestMethod.GET)
//			public ResponseEntity<?> gapfomsendtocab(@RequestParam long gapForm_id){
//				if(cabService.changeStatusGapForm(gapForm_id)) {
//						return new ResponseEntity("Gap Form send successfull",HttpStatus.OK);
//				}
//				else {
//					return new ResponseEntity("Gap from send fail",HttpStatus.BAD_REQUEST);
//				}
//			}
	
}
