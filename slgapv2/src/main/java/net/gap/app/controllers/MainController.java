package net.gap.app.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.gap.app.models.ResponseModel;
import net.gap.app.models.UserModel;
import net.gap.app.services.AuthenticationService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/main")
public class MainController {

	@RequestMapping("/hello")
	public ResponseEntity<?> hello(){
		return new ResponseEntity<Object>("Hello",HttpStatus.OK);
	}
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserModel userModel){
		ArrayList<String> roles = new ArrayList<String>();
		roles.add("customer");
		roles.add("farmer");
		if(!roles.contains(userModel.getRole())){
			return new ResponseEntity<Object>(new ResponseModel(400,"User Role Invalid",null),HttpStatus.BAD_REQUEST);
		}
		int status = authenticationService.userSignUp(userModel);
		if(status == 201) {
			return new ResponseEntity<Object>(new ResponseModel(201,"User Registraion Success",null),HttpStatus.CREATED);
		}else if(status == 208) {
			return new ResponseEntity<Object>(new ResponseModel(208,"User Already Registered But not Email Verified",null),HttpStatus.ALREADY_REPORTED);
		}else if(status == 226) {
			return new ResponseEntity<Object>(new ResponseModel(226,"User Already Registered",null),HttpStatus.IM_USED);
		}else {
			return new ResponseEntity<Object>(new ResponseModel(400,"User Registration Failed",null),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/re-sign-up")
	public ResponseEntity<?> userSignUpAgain(@RequestBody UserModel userModel){
		int response = authenticationService.userSignUpAgain(userModel);
		if(response == 201) {
			return new ResponseEntity<Object>(new ResponseModel(201,"User Signup Success",null),HttpStatus.CREATED);
		}else if(response == 226) {
			return new ResponseEntity<Object>(new ResponseModel(226,"User Already Signup",null),HttpStatus.IM_USED);
		}else{
			return new ResponseEntity<Object>(new ResponseModel(400,"User Signup Failed",null),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/email-verification")
	public ResponseEntity<?> emailVerification(@RequestBody UserModel userModel){
		return authenticationService.emailVerification(userModel);
	}
	
	
	@Value("${keycloak.credentials.secret}")
	private String SECRETKEY;

	@Value("${keycloak.resource}")
	private String CLIENTID;

	@Value("${keycloak.auth-server-url}")
	private String AUTHURL;

	@Value("${keycloak.realm}")
	private String REALM;
	
	@PostMapping("/token")
	public Object getToken(@RequestBody UserModel userModel) {
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("grant_type", "password"));
		urlParameters.add(new BasicNameValuePair("client_id", "get-token"));
		urlParameters.add(new BasicNameValuePair("username", userModel.getUsername()));
		urlParameters.add(new BasicNameValuePair("password", userModel.getPassword()));
		//urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpPost post = new HttpPost(AUTHURL+"/realms/"+REALM+"/protocol/openid-connect/token");

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);
			
			HttpStatus httpStatus = HttpStatus.resolve(response.getStatusLine().getStatusCode());
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return new ResponseEntity<String>(result.toString(),httpStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Object>(new ResponseModel(400,"User Login Failed",null),HttpStatus.BAD_REQUEST);
		}

		
		
	}
	
}
