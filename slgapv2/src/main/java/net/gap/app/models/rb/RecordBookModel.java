package net.gap.app.models.rb;

import lombok.Data;

@Data
public class RecordBookModel {
	
	private Long id;
	
	private String farmerId;
	private String startDate;
	private float landArea;
	private int trainedLabours;
	private int unTrainedLabours;
	private String targetMarket;
	private String siteMapUrl;
	

}
