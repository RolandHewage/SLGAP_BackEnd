package net.gap.app.entity.rb;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import net.gap.app.entity.GapFormEntity;

@Data
@Entity(name = "record_book")
public class RecordBookEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String farmerId;
	private String startDate;
	private float landArea;
	private int trainedLabours;
	private int unTrainedLabours;
	private String targetMarket;
	private String siteMapUrl;
	

}
