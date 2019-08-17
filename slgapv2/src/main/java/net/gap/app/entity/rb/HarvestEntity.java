package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "harvest")
public class HarvestEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
