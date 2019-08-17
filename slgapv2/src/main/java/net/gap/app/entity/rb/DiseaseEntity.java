package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "disease")
public class DiseaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String disease;
	private String chemical;
	private String addedDate;
	private float qty;
	private String method;
	private String company;
	private String usedName;
	private int partNo;
	private boolean isRegistered;
}
