package mustache.practice.repository;

import mustache.practice.domain.entity.Article;
import mustache.practice.domain.entity.Hospital;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {
    @Override
    List<Hospital> findAll();
}
