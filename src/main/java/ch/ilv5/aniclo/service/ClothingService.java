package ch.ilv5.aniclo.service;

import ch.ilv5.aniclo.base.MessageResponse;
import ch.ilv5.aniclo.model.Clothing;
import ch.ilv5.aniclo.repository.ClothingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingService {
    @Autowired
    private ClothingRepository clothingRepository;

    public List<Clothing> getAll() {
        return clothingRepository.findAll();
    }

    public Clothing getById(Long id) {
        return clothingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public MessageResponse deleteClothingById(Long id) {
        clothingRepository.deleteById(id);
        return new MessageResponse("Clothing " + id + " gelöscht");
    }

    public Clothing insertClothing(Clothing clothing) {
        return clothingRepository.save(clothing);
    }

    public Clothing updateClothingById(Clothing clothing, Long id) {
        return clothingRepository.findById(id)
                .map(clothingDB -> {
                    clothingDB.setName(clothing.getName());
                    clothingDB.setMarke(clothing.getMarke());
                    clothingDB.setPreis(clothing.getPreis());
                    clothingDB.setColour(clothing.getColour());
                    clothingDB.setPlaces(clothing.getPlaces());
                    return clothingRepository.save(clothingDB);
                })
                .orElseGet(() -> clothingRepository.save(clothing));
    }
}
