package net.gap.app.models.rb;

import lombok.Data;

@Data
public class DiseasePreventModel {

	private Long id;
	private Long recordBookId;
	
	private String crop;
	private String type;
	private String antiDiseaseName;
	private String usedSubstance;
	private boolean isRegistered;
	private String registeredCompany;
	private float qty;
	private String date;
	private String method;
	private int partNo;
	private String usedPerson;
	private String purchaseCompany;
	
}
