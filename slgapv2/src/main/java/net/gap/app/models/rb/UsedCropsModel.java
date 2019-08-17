package net.gap.app.models.rb;

import lombok.Data;

@Data
public class UsedCropsModel {
	
	private Long id;
	private Long recordBookId;
	
	private String growDate;
	private String crop;
	private String type;
	private float qty;
	private String certifiedDetail;
	private String treatDetails;

}
