package net.gap.app.models.rb;

import lombok.Data;

@Data
public class HarvestModel {
	
	private Long id;
	private Long recordBookId;
	
	private String date;
	private String crop;
	private int partNo;
	private float harvestQty;
	private float productionQty;
	private float marketLocalQty;
	private float marketExportQty;
	private String other;

}
