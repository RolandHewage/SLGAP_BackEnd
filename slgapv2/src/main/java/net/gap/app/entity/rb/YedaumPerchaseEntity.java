package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="YedaumPerchase")
public class YedaumPerchaseEntity {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	private String perchaseDate;
	private String type;
	private float qty;
	private String purchasePlace;
	private String batchNo;
	private String ProductData;
	private String ExpireDate;
	private String company;
	

}
