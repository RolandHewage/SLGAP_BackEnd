package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.HarvestEntity;
import net.gap.app.entity.rb.OrganicFertilizerEntity;

public interface OrganicFertilizerRepo extends JpaRepository<OrganicFertilizerEntity, Long> {
	
	List<OrganicFertilizerEntity> findByRecordBookId(Long recordBookId);
	
	// Get OrganicFertilizerEntity for organic fertilizer analysis based on record book id..... 2018/09/08
	List<OrganicFertilizerEntity> findByRecordBookIdIn(List<Long> recordBookId);

}
