package net.gap.app.models.rb;

import lombok.Data;

@Data
public class LabourModel {
	
	private Long id;
	private Long recordBookId;
	
	private int period;
	private String trainingName;
	private String trainingPerson;
	private String trainingCompany;
	private String other;

}
