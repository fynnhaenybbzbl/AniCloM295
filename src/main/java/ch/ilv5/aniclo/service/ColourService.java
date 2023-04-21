package ch.ilv5.aniclo.service;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Colour;
import ch.ilv5.aniclo.repository.ColourRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColourService {
    @Autowired
    private ColourRepository colourRepository;

    public List<Colour> getAll() {
        return colourRepository.findAll();
    }

    public Colour getById(Long id) {
        return colourRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public MessageResponse deleteColourById(Long id) {
        colourRepository.deleteById(id);
        return new MessageResponse("Colour " + id + " gelöscht");
    }

    public Colour insertColour(Colour colour) {
        return colourRepository.save(colour);
    }

    public Colour updatecolourById(Colour colour, Long id) {
        return colourRepository.findById(id)
                .map(colourDB -> {
                    colourDB.setFarbe(colour.getFarbe());
                    return colourRepository.save(colourDB);
                })
                .orElseGet(() -> colourRepository.save(colour));
    }
}
