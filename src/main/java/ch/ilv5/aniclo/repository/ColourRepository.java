package ch.ilv5.aniclo.repository;

import ch.ilv5.aniclo.model.Colour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColourRepository extends JpaRepository<Colour, Long> {
}
