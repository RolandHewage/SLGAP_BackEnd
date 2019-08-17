package net.gap.app.models.rb;

import lombok.Data;

@Data
public class WeedicideModel {
	
	private Long id;
	private Long recordBookId;
	
	private String weeds;
	private String chemical;
	private String addedDate;
	private float qty;
	private String method;
	private String company;
	private String usedName;
	private int partNo;
	private boolean isRegistered;

}
