package net.gap.app.models.rb;

import lombok.Data;

@Data
public class RopanModel {
	
	private Long id;
	private Long recordBookId;
	
	private String growDate;
	private String crop;
	private String type;
	private String qty;
	private String chemicalTreat;
	private String place;
	private String certifiedcCompany;

}
