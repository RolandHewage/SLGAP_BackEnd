package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "disease_prevent")
public class DiseasePreventEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String crop;
	private String type;
	private String antiDiseaseName;
	private String usedSubstance;
	private boolean isRegistered;
	private String registeredCompany;
	private float qty;
	private String date;
	private String method;
	private int partNo;
	private String usedPerson;
	private String purchaseCompany;

}
