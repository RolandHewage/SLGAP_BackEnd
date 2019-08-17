package net.gap.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "gap_crops")
public class GapCropsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long gapFormId;
	private String cropName;
	private String planingDate;
	private String variety;
	private String extent;
	private String yield;
}
