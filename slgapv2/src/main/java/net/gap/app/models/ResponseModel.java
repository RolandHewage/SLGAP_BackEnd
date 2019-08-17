package net.gap.app.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {

	private int message_code;
	private String message;
	private Object obj;
	
	public ResponseModel() {
		
	}
}
