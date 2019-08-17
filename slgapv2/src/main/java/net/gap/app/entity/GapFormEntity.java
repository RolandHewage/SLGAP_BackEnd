package net.gap.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "gap_form")
public class GapFormEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String user_id;
	@Column(unique=true)
	private String gap_form_no;
	private String provience;
	private String distric;
	private String dsDivision;
	private String gnDivision;
	private String arpaDivision;
	private String soilType;
	private String landOwnership;
	private String plrNo;
	private String waterResource;
	private String irrigationType;
	private String currentStatus;
	private String appliedDate;
	
}
