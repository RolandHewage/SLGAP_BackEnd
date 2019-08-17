package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="FertilizerPurchase")
public class FertilizerPurchaseEntity {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String purchaseDate;
	private String fertizer;
	private float qty;
	private String purchasePlace;
	private String company;
	private String batchNo;
	
}
