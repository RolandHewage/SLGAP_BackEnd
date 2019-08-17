package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import net.gap.app.entity.GapCropsEntity;

@Data
@Entity(name = "spoil_detail")
public class SpoilDetailEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String date;
	private String crop;
	private float spoilQty;
	private String reason;

}
