package net.gap.app.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.gap.app.models.ResponseModel;
import net.gap.app.models.UserModel;


@Component
public class AuthenticationService {

	@Value("${keycloak.credentials.secret}")
	private String SECRETKEY;

	@Value("${keycloak.resource}")
	private String CLIENTID;

	@Value("${keycloak.auth-server-url}")
	private String AUTHURL;

	@Value("${keycloak.realm}")
	private String REALM;
	
	@Value("${mykeycloak.user}")
	private String USER;
	
	@Value("${mykeycloak.password}")
	private String PASSWORD;
	
	public UsersResource getKeycloakUserResource() {

		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username(USER).password(PASSWORD)
				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build();

		RealmResource realmResource = kc.realm(REALM);
		UsersResource userRessource = realmResource.users();

		return userRessource;
	}

	private RealmResource getRealmResource() {

		Keycloak kc = KeycloakBuilder.builder().serverUrl(AUTHURL).realm("master").username(USER).password(PASSWORD)
				.clientId("admin-cli").resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
				.build();

		RealmResource realmResource = kc.realm(REALM);

		return realmResource;

	}
	
	/*
	 * Create User in keycloak
	 */
	
	public int userSignUp(UserModel userModel) {
		int statusId = 400;
		try {
			//get users resource
			UsersResource usersResource = getKeycloakUserResource();
			
			//create user model
			UserRepresentation userRepresentation = new UserRepresentation();
			userRepresentation.setFirstName(userModel.getFirst_name());
			userRepresentation.setLastName(userModel.getLast_name());
			userRepresentation.setUsername(userModel.getUsername());
			userRepresentation.setEmail(userModel.getEmail());
			
			//set user attribute
			Map<String, List<String>> otherAtribute = new HashMap<String, List<String>>();
			otherAtribute.put("phoneNumber", Arrays.asList(userModel.getPhone_no()));
			otherAtribute.put("gender", Arrays.asList(userModel.getGender()));
			otherAtribute.put("birthdate", Arrays.asList(userModel.getDob()));
			otherAtribute.put("nic", Arrays.asList(userModel.getNic()));
			otherAtribute.put("address", Arrays.asList(userModel.getAddress()));
			String verificationCode = this.genarateCode();
			otherAtribute.put("verification code", Arrays.asList(verificationCode));
			userRepresentation.setAttributes(otherAtribute);
			
			//set user disabled
			userRepresentation.setEnabled(false);
			
			//set user email not verified
			userRepresentation.setEmailVerified(false);
			
			//create user in keycloak
			Response result = usersResource.create(userRepresentation);
			
			statusId = result.getStatus();
			if(statusId == 201) {
				//get user id
				String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
				
				//send email
				try {
					this.sendVerificationEmail(userModel.getEmail(), verificationCode);
				}catch(Exception ex) {
					System.out.println("Email Sending Failed");
				}
				
				//create user password
				CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
				credentialRepresentation.setTemporary(false);
				credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
				credentialRepresentation.setValue(userModel.getPassword());
				usersResource.get(userId).resetPassword(credentialRepresentation);
				
				//set user role as 'user'
				RealmResource realmResource = getRealmResource();
				RoleRepresentation savedRoleRepresentation = realmResource.roles().get(userModel.getRole()).toRepresentation();
				realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(savedRoleRepresentation));
				
				return statusId;
			}else if(statusId == 409) {
				UserRepresentation existUser = usersResource.search(userModel.getEmail()).get(0);
				if(!existUser.isEmailVerified()) {
					if(!existUser.isEnabled()) {
						return 208;
					}else {
						return 226;
					}
				}else {
					return 226;
				}	
			}else {
				return 400;
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return 400;
		}
	}
	
	/*
	 * User Sign up again
	 */
	
	public int userSignUpAgain(UserModel userModel) {
		//get users resource
		UsersResource usersResource = getKeycloakUserResource();
		if(usersResource.search(userModel.getEmail()).isEmpty()) {
			return 400;
		}else{
			UserRepresentation existUser = usersResource.search(userModel.getEmail()).get(0);
			if(!existUser.isEmailVerified()) {
				if(!existUser.isEnabled()) {
					usersResource.delete(existUser.getId());
					return userSignUp(userModel);
				}else {
					return 226;
				}
			}else {
				return 226;
			}	
		}
	}
	
	/*
	 * Verification Code macthing
	 */
	public ResponseEntity<?> emailVerification(UserModel userModel) {
		//get users resource
		UsersResource usersResource = getKeycloakUserResource();
		if(usersResource.search(userModel.getEmail()).isEmpty()) {
			return new ResponseEntity<Object>(new ResponseModel(400,"User does not Exist",null),HttpStatus.BAD_REQUEST);
		}else{
			UserRepresentation user = usersResource.search(userModel.getEmail()).get(0);
			Map<String, List<String>> att = user.getAttributes();
			if(att.get("verification code").get(0).equals(userModel.getVerification_code())) {
				user.setEnabled(true);
				user.setEmailVerified(true);
				usersResource.get(user.getId()).update(user);
				return new ResponseEntity<Object>(new ResponseModel(202,"Email Verification Success and User now Enabled",null),HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity<Object>(new ResponseModel(400,"Email Verification Failed",null),HttpStatus.BAD_REQUEST);
			}
			
		}
	}
	
	/*
	 * Create ai and cab officer
	 */
	
	public int createAdminUsers(UserModel userModel,String addedBy) {
		int statusId = 400;
		try {
			//get users resource
			UsersResource usersResource = getKeycloakUserResource();
			
			//create user model
			UserRepresentation userRepresentation = new UserRepresentation();
			userRepresentation.setFirstName(userModel.getFirst_name());
			userRepresentation.setLastName(userModel.getLast_name());
			userRepresentation.setUsername(userModel.getUsername());
			userRepresentation.setEmail(userModel.getEmail());
			
			//set user attribute
			Map<String, List<String>> otherAtribute = new HashMap<String, List<String>>();
			otherAtribute.put("phoneNumber", Arrays.asList(userModel.getPhone_no()));
			otherAtribute.put("gender", Arrays.asList(userModel.getGender()));
			otherAtribute.put("birthdate", Arrays.asList(userModel.getDob()));
			otherAtribute.put("nic", Arrays.asList(userModel.getNic()));
			otherAtribute.put("address", Arrays.asList(userModel.getAddress()));
			otherAtribute.put("addedBy", Arrays.asList(addedBy));
			userRepresentation.setAttributes(otherAtribute);
			
			//set user disabled
			userRepresentation.setEnabled(false);
			
			//set user email not verified
			userRepresentation.setEmailVerified(false);
			
			//create user in keycloak
			Response result = usersResource.create(userRepresentation);
			
			statusId = result.getStatus();
			if(statusId == 201) {
				//get user id
				String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
				
				//send email
				try {
					this.sendNotificationEmail(userModel.getEmail(), "You are now member");
				}catch(Exception ex) {
					System.out.println("Email Sending Failed");
				}
				
				//create user password
				CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
				credentialRepresentation.setTemporary(false);
				credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
				credentialRepresentation.setValue(userModel.getPassword());
				usersResource.get(userId).resetPassword(credentialRepresentation);
				
				//set user role as 'user'
				RealmResource realmResource = getRealmResource();
				RoleRepresentation savedRoleRepresentation = realmResource.roles().get(userModel.getRole()).toRepresentation();
				realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(savedRoleRepresentation));
				
				return statusId;
			}else { 
				return statusId;
			}
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return 400;
		}
	}
	
	
	/*
	 * User enabled
	 */
	public ResponseEntity<?> userEnable(UserModel userModel,String enabledBy){
		//get users resource
		UsersResource usersResource = getKeycloakUserResource();
		if(usersResource.search(userModel.getEmail()).isEmpty()) {
			return new ResponseEntity<Object>(new ResponseModel(400,"User does not Exist",null),HttpStatus.BAD_REQUEST);
		}else{
			UserRepresentation user = usersResource.search(userModel.getEmail()).get(0);
			if(user.isEnabled()) {
				return new ResponseEntity<Object>(new ResponseModel(400,"User already Enabled",null),HttpStatus.BAD_REQUEST);
			}else {
				Map<String, List<String>> att = user.getAttributes();
				att.put("enabledBy", Arrays.asList(enabledBy));
				user.setEnabled(true);
				user.setAttributes(att);
				usersResource.get(user.getId()).update(user);
				return new ResponseEntity<Object>(new ResponseModel(202,"User now Enabled",null),HttpStatus.ACCEPTED);
			}
		}
	}
	
	
	/*
	 * User Disable
	 */
	public ResponseEntity<?> userDisable(UserModel userModel,String disbaledBy){
		//get users resource
		UsersResource usersResource = getKeycloakUserResource();
		if(usersResource.search(userModel.getEmail()).isEmpty()) {
			return new ResponseEntity<Object>(new ResponseModel(400,"User does not Exist",null),HttpStatus.BAD_REQUEST);
		}else{
			UserRepresentation user = usersResource.search(userModel.getEmail()).get(0);
			if(!user.isEnabled()) {
				return new ResponseEntity<Object>(new ResponseModel(400,"User already Disabled",null),HttpStatus.BAD_REQUEST);
			}else if(user.getId() == disbaledBy) {
				return new ResponseEntity<Object>(new ResponseModel(400,"You can't Disable Your self",null),HttpStatus.BAD_REQUEST);
			}else {
				Map<String, List<String>> att = user.getAttributes();
				att.put("disabledBy", Arrays.asList(disbaledBy));
				user.setEnabled(true);
				user.setAttributes(att);
				usersResource.get(user.getId()).update(user);
				return new ResponseEntity<Object>(new ResponseModel(202,"User now Enabled",null),HttpStatus.ACCEPTED);
			}
		}
	}
	
	/*
	 * get users list
	 */
	public List<UserModel> usersList(){
		UsersResource usersResource = getKeycloakUserResource();
		if(usersResource.list().isEmpty()) {
			System.out.println("Test 1");
			return null;
		}else {
			System.out.println("Test 2");
			List<UserRepresentation> users = usersResource.list();
			
			List<UserModel> userModels = new ArrayList<UserModel>();
			UserModel model;
			for(int i = 0; i < users.size(); i++) {
				UserRepresentation user = users.get(i);
				System.out.println(user.getEmail());
				model = new UserModel();
				model.setUser_id(user.getId());
				model.setUsername(user.getUsername());
				model.setFirst_name(user.getFirstName());
				model.setLast_name(user.getLastName());
				model.setEmail(user.getEmail());
				model.setEnbaled(user.isEnabled());
//				List<String> roles = user.getRealmRoles();
//				if(roles.contains("admin")) {
//					model.setRole("admin");
//				}else if(roles.contains("cab")) {
//					model.setRole("cab");
//				}else if(roles.contains("ai")) {
//					model.setRole("ai");
//				}else if(roles.contains("farmer")) {
//					model.setRole("farmer");
//				}else if(roles.contains("customer")) {
//					model.setRole("customer");
//				}
				userModels.add(model);
			}
			return  userModels;
		}
		
		
	}
	
	/*
	 * get user data
	 */
	public UserModel getUserDataById(UserModel userModel) {
		UsersResource usersResource = getKeycloakUserResource();
		UserRepresentation user = usersResource.get(userModel.getUser_id()).toRepresentation();
		UserModel model = new UserModel();
		model = new UserModel();
		model.setUser_id(user.getId());
		model.setUsername(user.getUsername());
		model.setFirst_name(user.getFirstName());
		model.setLast_name(user.getLastName());
		model.setEmail(user.getEmail());
		model.setEnbaled(user.isEnabled());
		List<RoleRepresentation> roles = getRealmResource().users().get(user.getId()).roles().getAll().getRealmMappings();
		System.out.println(roles.get(0).getName());
//		if(roles.contains("admin")) {
//			model.setRole("admin");
//		}else if(roles.contains("cab")) {
//			model.setRole("cab");
//		}else if(roles.contains("ai")) {
//			model.setRole("ai");
//		}else if(roles.contains("farmer")) {
//			model.setRole("farmer");
//		}else if(roles.contains("customer")) {
//			model.setRole("customer");
//		}
		
		return model;
	}
	/*
	 * Verification Code Genaration
	 */
	private String genarateCode() {
		int ac = 0;
		String authCode="";
		while(authCode.length() != 6) {
			ac = 5 + (int)(Math.random() * ((999999) + 1));
			authCode = String.valueOf(ac);
		}
		return authCode;
	}
	
	/*
	 * Send Email with verification code
	 */
	
	private void sendVerificationEmail(String email,String authCode) throws Exception  {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://mail.slgap.net");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("code", authCode));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		client.execute(post);
	}
	
	/*
	 * Send Email with Notifiction Email
	 */
	
	private void sendNotificationEmail(String email,String message) throws Exception  {
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost("https://mail.slgap.net");
		
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("code", message));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		client.execute(post);
	}
}
