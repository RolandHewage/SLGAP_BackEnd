package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.ChemicalFertilizerEntity;
import net.gap.app.entity.rb.OrganicFertilizerEntity;

public interface ChemicalFertilizerRepo extends JpaRepository<ChemicalFertilizerEntity, Long> {
	
	List<ChemicalFertilizerEntity> findByRecordBookId(Long recordBookId);
	
	// Get ChemicalFertilizerEntity for chemical fertilizer analysis based on record book id..... 2018/09/08
	List<ChemicalFertilizerEntity> findByRecordBookIdIn(List<Long> recordBookId);


}
