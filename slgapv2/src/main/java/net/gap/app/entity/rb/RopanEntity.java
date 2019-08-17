package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="Ropan")
public class RopanEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String growDate;
	private String crop;
	private String type;
	private String qty;
	private String chemicalTreat;
	private String place;
	private String certifiedcCompany;
	
	

}
