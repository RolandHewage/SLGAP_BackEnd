package net.gap.app.repositories.rb;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.RopanEntity;


public interface RopanRepo extends JpaRepository<RopanEntity, Long> {
	
	List<RopanEntity> findByRecordBookId(Long recordBookId);

}
