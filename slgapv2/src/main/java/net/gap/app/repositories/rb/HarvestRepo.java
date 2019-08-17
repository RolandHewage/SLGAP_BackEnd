package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.rb.HarvestEntity;

public interface HarvestRepo extends JpaRepository<HarvestEntity, Long>{
	
//	// Get crop names for harvest analysis based on record book id
//	@Query(value="Select crop from harvest where record_book_id in ?1",nativeQuery=true)
//	List<String> getAllCrops(List<Long> recordBookId);
	
	List<HarvestEntity> findByRecordBookId(Long recordBookId);
	
	// Get Harvest Entity for harvest analysis based on record book id..... 2018/09/08
	List<HarvestEntity> findByRecordBookIdIn(List<Long> recordBookId);

}
