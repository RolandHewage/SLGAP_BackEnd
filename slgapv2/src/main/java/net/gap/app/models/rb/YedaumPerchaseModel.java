package net.gap.app.models.rb;

import lombok.Data;

@Data
public class YedaumPerchaseModel {
	
	private Long id;
	private Long recordBookId;
	private String perchaseDate;
	private String type;
	private float qty;
	private String purchasePlace;
	private String batchNo;
	private String ProductData;
	private String ExpireDate;
	private String company;

}
