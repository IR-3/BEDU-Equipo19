package org.bedu.java.backend.pet.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.pet.model.CCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CCitaRepository
extends JpaRepository<CCita, Long> {
    Optional<CCita> findCitaByClsDateAndClsTime(LocalDate clsDate, LocalTime clsTime);
    boolean existsByClsDateAndClsTimeLessThanEqualAndClsFinGreaterThanEqual(LocalDate clsDate, LocalTime startTime, LocalTime endTime);
    Optional<CCita> findByClsDateAndClsTimeBetween(LocalDate clsDate, LocalTime startTime, LocalTime endTime);
    @Query("SELECT c FROM CCita c WHERE c.clsDate = :clsDate AND ((c.clsTime >= :startTime AND c.clsTime < :endTime) OR (c.clsFin > :startTime AND c.clsFin <= :endTime))")
    List<CCita> findCitasBetweenTimes(@Param("clsDate") LocalDate clsDate, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime);


}
