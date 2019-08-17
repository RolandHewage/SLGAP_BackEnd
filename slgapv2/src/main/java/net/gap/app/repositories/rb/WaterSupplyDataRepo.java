package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.WaterSupplyDataEntity;



public interface WaterSupplyDataRepo extends JpaRepository<WaterSupplyDataEntity, Long> {
	
	List<WaterSupplyDataEntity> findByRecordBookId(Long recordBookId);

}
