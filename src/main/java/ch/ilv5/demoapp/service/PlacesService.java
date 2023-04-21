package ch.ilv5.demoapp.service;

import ch.ilv5.demoapp.base.MessageResponse;
import ch.ilv5.demoapp.model.Places;
import ch.ilv5.demoapp.repository.PlacesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacesService {
    @Autowired
    private PlacesRepository placesRepository;

    public List<Places> getAll() {
        return placesRepository.findAll();
    }

    public Places getById(Long id) {
        return placesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public MessageResponse deletePlace(Long id) {
        placesRepository.deleteById(id);
        return new MessageResponse("Place " + id + " deleted");
    }

    public Places insertPlace(Places places) {
        return placesRepository.save(places);
    }

    public Places updatePlace(Places place, Long id) {
        return placesRepository.findById(id)
                .map(placesDB -> {
                    placesDB.setPlz(place.getPlz());
                    placesDB.setOrtsname(place.getOrtsname());
                    placesDB.setStrasse(place.getStrasse());
                    placesDB.setStrassennummer(place.getStrassennummer());
                    return placesRepository.save(placesDB);
                })
                .orElseGet(() -> placesRepository.save(place));
    }
}
