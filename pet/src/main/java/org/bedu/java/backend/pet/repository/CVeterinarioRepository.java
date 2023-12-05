package org.bedu.java.backend.pet.repository;

import org.bedu.java.backend.pet.model.CVeterinario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CVeterinarioRepository
extends CrudRepository<CVeterinario, Long> {

  List<CVeterinario> findAll();
}
