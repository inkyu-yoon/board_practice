package mustache.practice.repository;

import mustache.practice.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);


}
