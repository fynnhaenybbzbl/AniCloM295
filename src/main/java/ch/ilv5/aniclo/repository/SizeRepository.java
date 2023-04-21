package ch.ilv5.aniclo.repository;

import ch.ilv5.aniclo.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
}
