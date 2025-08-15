package br.com.fiap.uforegistry.sighting;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UfoSightingService {

    private final UfoSightingRepository ufoSightingRepository;

    public UfoSightingService(UfoSightingRepository ufoSightingRepository) {
        this.ufoSightingRepository = ufoSightingRepository;
    }

    public List<UfoSighting> getAllSightings() {
        return ufoSightingRepository.findAllOrderByDateDesc();
    }

    public UfoSighting save(UfoSighting ufoSighting) {
        if (ufoSighting.getSightingDate() == null) {
            ufoSighting.setSightingDate(LocalDateTime.now());
        }
        return ufoSightingRepository.save(ufoSighting);
    }

    public Optional<UfoSighting> findById(Long id) {
        return ufoSightingRepository.findById(id);
    }

    public void deleteById(Long id) {
        ufoSightingRepository.deleteById(id);
    }

    public List<UfoSighting> findByLocation(String location) {
        return ufoSightingRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<UfoSighting> findByShape(String shape) {
        return ufoSightingRepository.findByShape(shape);
    }
}