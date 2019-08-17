package net.gap.app.models.gf;

import lombok.Data;

@Data
public class FinancialLoanModel {

	private String loanType;
	private int amount;
	private String instituteName;
	private String branchName;
}
