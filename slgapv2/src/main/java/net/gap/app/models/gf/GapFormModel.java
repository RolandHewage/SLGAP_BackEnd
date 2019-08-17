package net.gap.app.models.gf;

import java.util.List;

import lombok.Data;

@Data
public class GapFormModel {

	private String gap_form_no;
	private String provience;
	private String distric;
	private String dsDivision;
	private String gnDivision;
	private String arpaDivision;
	private String soilType;
	private String land_ownership;
	private String plrNo;
	private String waterResource;
	private String irrigationType;
	private String currentStatus;
	
	private List<GapCropsModel> gapCropsModels;
	private List<OuterGrowerModel> outerGrowerModels;
	private List<FinancialLoanModel> financialLoanModels;
	
	
}
