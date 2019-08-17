package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.SpoilDetailEntity;



public interface SpoilDetailRepo extends JpaRepository<SpoilDetailEntity, Long> {
	
	List<SpoilDetailEntity> findByRecordBookId(Long recordBookId);

}
