package net.gap.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.gap.app.entity.GapFormEntity;
import net.gap.app.entity.ar.AuditReportEntity;


public interface AuditReportRepo extends JpaRepository<AuditReportEntity, Long> {
	
	@Query(value="Select COALESCE(a1_1,0)+COALESCE(a1_2,0)+COALESCE(a1_3,0)+COALESCE(a1_4,0)+COALESCE(a1_5,0)+COALESCE(a1_6,0)+"
			+ "COALESCE(a2_1,0)+COALESCE(a2_2,0)+COALESCE(a2_3,0)+COALESCE(a2_4,0)+COALESCE(a2_5,0)+COALESCE(a2_6,0)+COALESCE(a2_7,0)+"
			+ "COALESCE(a3_1,0)+COALESCE(a3_2,0)+COALESCE(a3_3,0)+"
			+ "COALESCE(a4_1,0)+COALESCE(a4_2,0)+COALESCE(a4_2_1,0)+COALESCE(a4_3,0)+COALESCE(a4_3_1,0)+COALESCE(a4_4,0)+COALESCE(a4_5,0)+COALESCE(a4_6,0)+COALESCE(a4_7,0)+COALESCE(a4_8,0)+COALESCE(a4_9,0)+COALESCE(a4_10,0)+COALESCE(a4_11,0)+COALESCE(a4_12,0)+COALESCE(a4_13,0)+COALESCE(a4_14,0)+COALESCE(a4_15,0)+"
			+ "COALESCE(a5_1,0)+COALESCE(a5_2,0)+COALESCE(a5_3,0)+COALESCE(a5_4,0)+COALESCE(a5_5,0)+"
			+ "COALESCE(a6_1,0)+COALESCE(a6_2,0)+COALESCE(a6_3,0)+COALESCE(a6_4,0)+COALESCE(a6_5,0)+COALESCE(a6_6,0)+COALESCE(a6_7,0)+COALESCE(a6_8,0)+COALESCE(a6_9,0)+COALESCE(a6_10,0)+COALESCE(a6_11,0)+COALESCE(a6_12,0)+COALESCE(a6_13,0)+COALESCE(a6_14,0)+COALESCE(a6_15,0)+COALESCE(a6_16,0)+COALESCE(a6_17,0)+COALESCE(a6_18,0)+COALESCE(a6_19,0)+COALESCE(a6_20,0)+COALESCE(a6_21,0)+COALESCE(a6_22,0)+"
			+ "COALESCE(a7_1,0)+COALESCE(a7_2,0)+COALESCE(a7_3,0)+COALESCE(a7_4,0)+COALESCE(a7_5,0)+"
			+ "COALESCE(a8_1,0)+COALESCE(a8_2,0)+COALESCE(a8_3,0)+COALESCE(a8_4,0)+"
			+ "COALESCE(a9_1,0)+COALESCE(a9_2,0)+COALESCE(a9_3,0)+COALESCE(a9_4,0)+"
			+ "COALESCE(a10_1,0)+COALESCE(a10_2,0)+"
			+ "COALESCE(a11_1,0)+COALESCE(a11_2,0)"
			+ " AS Total from audit_report where audit_id=?1",nativeQuery=true)
	int getMarks(Long auditId);
	
	AuditReportEntity findByGapFormId(Long gapFormId);

}
