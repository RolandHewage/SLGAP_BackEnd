package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.FertilizerPurchaseEntity;

public interface FertilizerPurchaseRepo  extends JpaRepository<FertilizerPurchaseEntity,Long>{
	
	List<FertilizerPurchaseEntity> findByRecordBookId(Long recordBookId);

}
