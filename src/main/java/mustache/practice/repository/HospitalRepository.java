package mustache.practice.repository;

import mustache.practice.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByTotalNumberOfBedsBetweenOrderByTotalNumberOfBedsAsc(Integer num1, Integer num2);

    List<Hospital> findByRoadNameAddressContainingAndBusinessTypeNameIn(String address, List<String> businessTypes);

    Page<Hospital> findByRoadNameAddressContaining(String str, Pageable pageable);
}
