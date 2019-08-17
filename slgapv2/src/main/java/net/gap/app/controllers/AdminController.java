package net.gap.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.gap.app.models.ResponseModel;
import net.gap.app.models.UserModel;



@RestController
@RequestMapping("/admin")
public class AdminController extends SecureRoute {

	
	@PostMapping("/create-user")
	public ResponseEntity<?> addUsers(@RequestBody UserModel userModel, HttpServletRequest request){
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("ai");
		roles.add("cab");
		roles.add("admin");
		if(!roles.contains(userModel.getRole())){
			return new ResponseEntity<Object>(new ResponseModel(400,"User Role Invalid",null),HttpStatus.BAD_REQUEST);
		}
		String userId = super.getUserModel(request).getUser_id();
		int response = authenticationService.createAdminUsers(userModel,userId);
		if(response == 201) {
			return new ResponseEntity<Object>(new ResponseModel(201,"User Added Success",null),HttpStatus.CREATED);
		}else if(response == 409) {
			return new ResponseEntity<Object>(new ResponseModel(409,"User name or email Already exist",null),HttpStatus.CONFLICT);
		}else {
			return new ResponseEntity<Object>(new ResponseModel(400,"Request Failed",null),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/user-enable")
	public ResponseEntity<?> enabelUser(@RequestBody UserModel userModel,HttpServletRequest request){
		String userId = super.getUserModel(request).getUser_id();
		return authenticationService.userEnable(userModel, userId);
	}
	
	@PostMapping("/user-disable")
	public ResponseEntity<?> disableUser(@RequestBody UserModel userModel,HttpServletRequest request){
		String userId = super.getUserModel(request).getUser_id();
		return authenticationService.userDisable(userModel, userId);
	}
	
	@RequestMapping("/users-list")
	public ResponseEntity<?> usersList(HttpServletRequest request){
		List<UserModel> users = authenticationService.usersList();
		if(authenticationService.usersList() == null) {
			return new ResponseEntity<Object>(new ResponseModel(400,"No Users",null),HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Object>(new ResponseModel(200,"User list",users),HttpStatus.OK);
		}
	}
	
	@RequestMapping("/user")
	public ResponseEntity<?> getUserDataById(@RequestBody UserModel userModel){
		UserModel user = authenticationService.getUserDataById(userModel);
		if(user != null)
			return new ResponseEntity<Object>(new ResponseModel(200,"User Data",user),HttpStatus.OK);
		else
			return new ResponseEntity<Object>(new ResponseModel(400,"Does not have user",null),HttpStatus.BAD_REQUEST);
	}
}
