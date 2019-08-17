package net.gap.app.models.rb;

import lombok.Data;

@Data
public class WaterSupplyDataModel {
	
	private Long id;
	private Long recordBookId;
	
	private String date;
	private String crop;
	private String waterSource;
	private String method;
	private int partNo;
	private String usedPerson;

}
