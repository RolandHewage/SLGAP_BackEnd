package net.gap.app.models.rb;

import lombok.Data;

@Data
public class JoinPeatsControlModel {

	private Long id;
	private Long recordBookId;
	
	private boolean isAntiPeats;
	private boolean isSelectedSuitableCrop;
	private boolean isTrustedSource;
	private boolean isStandardLand;
	private boolean isSpace;
	private boolean isUsedFertilizerRecommended;
	private boolean isCutDown;
	private boolean isCoveredLand;
	private boolean isWild;
	private boolean isDestroyed;
	private boolean isHarvested;
	
}
