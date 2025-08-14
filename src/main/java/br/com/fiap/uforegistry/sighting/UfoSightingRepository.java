package br.com.fiap.uforegistry.sighting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UfoSightingRepository extends JpaRepository<UfoSighting, Long> {
    
    List<UfoSighting> findByLocationContainingIgnoreCase(String location);
    
    List<UfoSighting> findByShape(String shape);
    
    @Query("SELECT u FROM UfoSighting u ORDER BY u.sightingDate DESC")
    List<UfoSighting> findAllOrderByDateDesc();
    
}