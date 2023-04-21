package ch.ilv5.demoapp.service;

import ch.ilv5.demoapp.base.MessageResponse;
import ch.ilv5.demoapp.model.Size;
import ch.ilv5.demoapp.repository.SizeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    public Size getById(Long id) {
        return sizeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public MessageResponse deleteSize(Long id) {
        sizeRepository.deleteById(id);
        return new MessageResponse("Size " + id + " deleted");
    }

    public Size insertSize(Size size) {
        return sizeRepository.save(size);
    }

    public Size updateSize(Size size, Long id) {
        return sizeRepository.findById(id)
                .map(sizeDB -> {
                    sizeDB.setEu(size.getEu());
                    sizeDB.setFra(size.getFra());
                    sizeDB.setUsa(size.getUsa());
                    return sizeRepository.save(sizeDB);
                })
                .orElseGet(() -> sizeRepository.save(size));
    }
}
