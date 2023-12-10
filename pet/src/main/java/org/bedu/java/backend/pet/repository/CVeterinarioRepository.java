package org.bedu.java.backend.pet.repository;

import org.bedu.java.backend.pet.model.CVeterinario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CVeterinarioRepository
extends JpaRepository<CVeterinario, Long> {}
