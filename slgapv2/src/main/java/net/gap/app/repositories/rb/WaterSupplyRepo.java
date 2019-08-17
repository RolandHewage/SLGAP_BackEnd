package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.WaterSupplyEntity;



public interface WaterSupplyRepo extends JpaRepository<WaterSupplyEntity, Long>{
	
	List<WaterSupplyEntity> findByRecordBookId(Long recordBookId);

}
