package net.gap.app.models.rb;

import lombok.Data;

@Data
public class FertilizerPurchaseModel {
	
	private Long id;
	private Long recordBookId;
	
	private String purchaseDate;
	private String fertizer;
	private float qty;
	private String purchasePlace;
	private String company;
	private String batchNo;

}
