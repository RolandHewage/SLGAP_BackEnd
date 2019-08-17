package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "water_supply")
public class WaterSupplyEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private boolean isDirtyWater;
	private boolean isGetWaterPokuna;
	private boolean isThread;
	private boolean isWaterAnalysis;
	
}
