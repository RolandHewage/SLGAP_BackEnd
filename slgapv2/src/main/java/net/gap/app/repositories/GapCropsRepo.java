package net.gap.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.GapCropsEntity;

public interface GapCropsRepo extends JpaRepository<GapCropsEntity, Long> {
	
	@Query(value="Select * from gap_crops where gap_form_id=?1",nativeQuery=true)
	List<GapCropsEntity> getGapFormCropsDetail(long id);
	
	List<GapCropsEntity> findByGapFormId(Long gap_form_id);
	
	//GapCropsEntity findAllByGapFormId(long id);

}
