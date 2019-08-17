package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.UsedCropsEntity;



public interface UsedCropsRepo extends JpaRepository<UsedCropsEntity, Long> {
	
	List<UsedCropsEntity> findByRecordBookId(Long rd_id);

}
