package ch.ilv5.demoapp.repository;

import ch.ilv5.demoapp.model.Places;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Long> { }
