package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.InsecticideEntity;

public interface InsecticideRepo extends JpaRepository<InsecticideEntity, Long>{
	
	List<InsecticideEntity> findByRecordBookId(Long recordBookId);

}
