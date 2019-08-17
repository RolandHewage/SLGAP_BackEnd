package net.gap.app.models.rb;

import lombok.Data;

@Data
public class DiseaseModel {

	private Long id;
	private Long recordBookId;
	
	private String disease;
	private String chemical;
	private String addedDate;
	private float qty;
	private String method;
	private String company;
	private String usedName;
	private int partNo;
	private boolean isRegistered;
	
}
