package net.gap.app.repositories.rb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.LandReportEntity;

public interface LandReportRepo extends JpaRepository<LandReportEntity, Long> {
	
	LandReportEntity findByRecordBookId(Long recordBookId);

}
