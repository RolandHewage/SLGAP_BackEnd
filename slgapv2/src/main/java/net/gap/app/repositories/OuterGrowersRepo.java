package net.gap.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.OuterGrowersEntity;

public interface OuterGrowersRepo extends JpaRepository<OuterGrowersEntity, Long> {
	
	@Query(value="Select * from outer_grower where gap_form_id=?1",nativeQuery=true)
	List<OuterGrowersEntity> getGapOuterCrops(long gapForm_id);

	List<OuterGrowersEntity> findByGapFormId(Long gap_form_id);
}
