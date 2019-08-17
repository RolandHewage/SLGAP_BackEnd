package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.WeedicideEntity;

public interface WeedicideRepo extends JpaRepository<WeedicideEntity, Long> {
	
	List<WeedicideEntity> findByRecordBookId(Long recordBookId);

}
