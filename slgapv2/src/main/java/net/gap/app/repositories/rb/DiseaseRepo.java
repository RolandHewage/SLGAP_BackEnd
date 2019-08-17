package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.DiseaseEntity;

public interface DiseaseRepo extends JpaRepository<DiseaseEntity,Long> {
	
	List<DiseaseEntity> findByRecordBookId(Long recordBookId);

}
