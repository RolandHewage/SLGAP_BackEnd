package net.gap.app.models.rb;

import lombok.Data;

@Data
public class InsecticideModel {
	
	private Long id;
	private Long recordBookId;
	
	private String insect;
	private String chemical;
	private String date;
	private float qty;
	private String method;
	private String company;
	private String usedName;
	private int partNo;
	private boolean isRegistered;

}
