package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "join_peats_control")
public class JoinPeatsControlEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
