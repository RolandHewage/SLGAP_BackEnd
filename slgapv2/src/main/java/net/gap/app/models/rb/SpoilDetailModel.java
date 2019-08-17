package net.gap.app.models.rb;

import lombok.Data;

@Data
public class SpoilDetailModel {
	
	private Long id;
	private Long recordBookId;
	
	private String date;
	private String crop;
	private float spoilQty;
	private String reason;

}
