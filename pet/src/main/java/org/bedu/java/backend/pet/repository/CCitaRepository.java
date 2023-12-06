package org.bedu.java.backend.pet.repository;

import org.bedu.java.backend.pet.model.CCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCitaRepository extends JpaRepository<CCita, Long> {

}
