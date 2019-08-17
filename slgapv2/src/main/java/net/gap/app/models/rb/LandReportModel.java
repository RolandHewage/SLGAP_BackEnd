package net.gap.app.models.rb;

import lombok.Data;

@Data
public class LandReportModel {
	
	private Long id;
	private Long recordBookId;
	private String landOwn;
	private boolean isHospitalBefore;
	private boolean isSoilLow;
	private boolean isSoilProtect;
	private boolean isBeforeGrow;
	private String crop;
	private String chemical;

}
