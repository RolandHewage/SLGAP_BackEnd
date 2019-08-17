package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "labour")
public class LabourEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long recordBookId;
	
	private int period;
	private String trainingName;
	private String trainingPerson;
	private String trainingCompany;
	private String other;

}
