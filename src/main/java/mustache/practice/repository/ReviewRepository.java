package mustache.practice.repository;

import lombok.RequiredArgsConstructor;
import mustache.practice.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{

}
