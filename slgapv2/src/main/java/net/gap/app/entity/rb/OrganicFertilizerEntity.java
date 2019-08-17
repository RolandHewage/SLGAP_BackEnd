package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="OrganicFertilizer")
public class OrganicFertilizerEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String addDate;
	private String fertilizer;
	private String addCrop;
	private float addQty;
	private String addType;
	private int partNo;
	private String addPerson;
	private String certifiedCompany;
	

}
