package org.bedu.java.backend.pet.repository;

import java.util.List;

import org.bedu.java.backend.pet.model.Mascota;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Repository
@Repository
public interface MascotaRepository extends CrudRepository <Mascota, Long> {
    List<Mascota> findAll();
}
