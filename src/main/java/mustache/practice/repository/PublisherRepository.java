package mustache.practice.repository;

import mustache.practice.domain.entity.Author;
import mustache.practice.domain.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
