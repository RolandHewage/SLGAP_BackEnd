package net.gap.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.FinancialEntity;

public interface FinancialRepo extends JpaRepository<FinancialEntity, Long> {
	
	@Query(value="Select * from financial_loan where gap_form_id=?1",nativeQuery=true)
	List<FinancialEntity> getGapFinancialDetails(long gapForm_id);
	
	List<FinancialEntity> findByGapFormId(Long gap_form_id);

}
