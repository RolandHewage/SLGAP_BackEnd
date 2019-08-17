package net.gap.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "outer_grower")
public class OuterGrowersEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long gapFormId;
	private String cropName;
	private String extent;
	private String company;
	private int period;
}
