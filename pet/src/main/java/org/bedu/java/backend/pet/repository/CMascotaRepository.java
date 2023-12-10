package org.bedu.java.backend.pet.repository;

import org.bedu.java.backend.pet.model.CMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CMascotaRepository
extends JpaRepository<CMascota, Long> {}
