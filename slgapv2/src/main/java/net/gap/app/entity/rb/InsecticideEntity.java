package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "insecticide")
public class InsecticideEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private String insect;
	private String chemical;
	private String date;
	private float qty;
	private String method;
	private String company;
	private String usedName;
	private int partNo;
	private boolean isRegistered;

}
