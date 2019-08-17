package net.gap.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.gap.app.models.UserModel;
import net.gap.app.services.AuthenticationService;


public class SecureRoute {

	
	@Autowired
	protected AuthenticationService authenticationService;
	
	/*
	 * get user model from token
	 */
	protected UserModel getUserModel(HttpServletRequest request) {
		request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		AccessToken token = ((KeycloakPrincipal<?>) request.getUserPrincipal()).getKeycloakSecurityContext().getToken();
		UserModel userModel = new UserModel();
		userModel.setUser_id(token.getSubject());
		userModel.setUsername(token.getPreferredUsername());
		userModel.setFirst_name(token.getGivenName());
		userModel.setLast_name(token.getFamilyName());
		userModel.setEmail(token.getEmail());
		userModel.setDob(token.getBirthdate());
		userModel.setPhone_no(token.getPhoneNumber());
		UsersResource usersResource = authenticationService.getKeycloakUserResource();
		UserRepresentation user = usersResource.get(token.getSubject()).toRepresentation();
		Map<String, List<String>> att = user.getAttributes();
		userModel.setAddress(att.get("address").get(0));
		userModel.setNic(att.get("nic").get(0));
		userModel.setGender(token.getGender());
		return userModel;
	}
}
