package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="LandReport")
public class LandReportEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
