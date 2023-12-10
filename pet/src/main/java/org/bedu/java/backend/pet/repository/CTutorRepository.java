package org.bedu.java.backend.pet.repository;

import org.bedu.java.backend.pet.model.CTutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CTutorRepository
extends JpaRepository<CTutor, Long> {}
