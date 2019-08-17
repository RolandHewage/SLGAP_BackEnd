package net.gap.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="financial_loan")
public class FinancialEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long gapFormId;
	private String loanType;
	private int amount;
	private String instituteName;
	private String branchName;
}
