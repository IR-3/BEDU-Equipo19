package org.bedu.java.backend.pet.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.bedu.java.backend.pet.model.CCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCitaRepository
extends JpaRepository<CCita, Long> {
    Optional<CCita> findCitaByClsDateAndClsTime(LocalDate clsDate, LocalTime clsTime);

}
