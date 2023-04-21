package ch.ilv5.aniclo.repository;

import ch.ilv5.aniclo.model.Clothing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {
}
