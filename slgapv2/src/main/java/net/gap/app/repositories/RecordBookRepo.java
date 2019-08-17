package net.gap.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.rb.RecordBookEntity;

public interface RecordBookRepo extends JpaRepository<RecordBookEntity, Long>{
	
	@Query(value="Select start_date from record_book where farmer_id=?1",nativeQuery=true)
	List<String> getAllRecordBookDate(String user_id);
	
	@Query(value="Select id from record_book where farmer_id=?1 and start_date=?2",nativeQuery=true)
	Long getRecordFarmerId(String farmer_id,String date);
	
	
	// Get record book id of each farmer ..... 2018/09/08
	@Query(value="Select * from record_book where farmer_id=?1",nativeQuery=true)
	List<RecordBookEntity> getRecordBookIds(String farmer_id);
	
	RecordBookEntity findByStartDate(String startDate);

}
