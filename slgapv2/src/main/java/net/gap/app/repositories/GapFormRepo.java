package net.gap.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.gap.app.entity.GapFormEntity;

public interface GapFormRepo extends JpaRepository<GapFormEntity, Long> {
	
	@Query(value="Select * from gap_form where id=?1",nativeQuery=true)
	GapFormEntity getgapformdetaild(long id);
	
	@Query(value="Select applied_date from gap_form where user_id=?1",nativeQuery=true)
	List<String> getAllGapForms(String user_id);
	
	
	GapFormEntity findByappliedDate(String startDate);
	
}
