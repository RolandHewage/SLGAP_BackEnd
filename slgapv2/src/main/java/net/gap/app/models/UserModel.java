package net.gap.app.models;

import lombok.Data;

@Data
public class UserModel {

	private String user_id;
	private String first_name;
	private String last_name;
	private String username;
	private String email;
	private String gender;
	private String phone_no;
	private String nic;
	private String dob;
	private String password;
	private String address;
	private String role;
	private boolean enbaled;
	private String verification_code;
	
	public UserModel() {
		
	}
	
}
