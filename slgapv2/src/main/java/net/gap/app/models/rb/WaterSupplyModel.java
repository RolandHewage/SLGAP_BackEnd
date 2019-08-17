package net.gap.app.models.rb;

import lombok.Data;

@Data
public class WaterSupplyModel {
	
	private Long id;
	private Long recordBookId;
	
	private boolean isDirtyWater;
	private boolean isGetWaterPokuna;
	private boolean isThread;
	private boolean isWaterAnalysis;

}
