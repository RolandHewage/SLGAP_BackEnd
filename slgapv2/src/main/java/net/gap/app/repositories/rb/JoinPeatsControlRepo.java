package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.JoinPeatsControlEntity;

public interface JoinPeatsControlRepo extends JpaRepository<JoinPeatsControlEntity, Long> {
	
	List<JoinPeatsControlEntity> findByRecordBookId(Long recordBookId);

}
