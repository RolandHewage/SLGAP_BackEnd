package net.gap.app.repositories.rb;

import org.springframework.data.jpa.repository.JpaRepository;

import net.gap.app.entity.rb.DiseasePreventEntity;

public interface DiseasePreventRepo extends JpaRepository<DiseasePreventEntity,Long> {

}
