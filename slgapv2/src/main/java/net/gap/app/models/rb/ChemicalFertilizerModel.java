package net.gap.app.models.rb;

import lombok.Data;
import net.gap.app.models.gf.GapCropsModel;

@Data
public class ChemicalFertilizerModel {
	
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
