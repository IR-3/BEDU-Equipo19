package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateMascotaDTO;
import org.bedu.java.backend.pet.exception.CMascotaTutorException;
import org.bedu.java.backend.pet.exception.MascotaNotFoundException;
import org.bedu.java.backend.pet.mapper.CMascotaMapper;
import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.repository.CMascotaRepository;
import org.bedu.java.backend.pet.repository.CTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CMascotaService {

  @Autowired
  private CMascotaRepository  Repository;

  @Autowired
  private CMascotaMapper  Mapper;

  @Autowired
  private CTutorRepository  TutorRepo;


  // Regresa todas las mascotas
  public List<CMascotaDTO> RegresarLista() {
    return Repository.findAll().stream()
           .map( x -> Mapper.EnDTO( x, x.getClsTutor() ) )
           .toList();
  }


  // Agrega una mascota a la base de datos
  public CMascotaDTO Nuevo( CMascotaDTOCreate frontInfo )
  throws CMascotaTutorException {

    // Obtiene el tutor
    Optional<CTutor> tutor;
    tutor = TutorRepo.findById( frontInfo.getLngTutorID() );
    if( !tutor.isPresent() )
      throw new CMascotaTutorException();

    // Actualiza la tabla
    CMascota nuevo = Repository.save
      ( Mapper.EnModel( frontInfo, tutor.get() ) );

    return Mapper.EnDTO( nuevo, nuevo.getClsTutor() );
  }

  //Eliminar la tabla
  public boolean deleteById(Long mascotaId){
    Optional<CMascota> mascota = Repository.findById(mascotaId);

    if(mascota.isPresent()){
      Repository.delete(mascota.get());
      return true;
    }else{
      return false;
    }
  }

  public void actualizarMascota(Long mascotaId, UpdateMascotaDTO data) throws MascotaNotFoundException {
    Optional<CMascota> result = Repository.findById(mascotaId);
    if (result.isEmpty()) {
      throw new MascotaNotFoundException();
    }

    CMascota model = result.get();

    // Actualizar campos de mascota
    if (data.getStrNombre() != null) {
      model.setStrNombre(data.getStrNombre());
    }
    if (data.getStrEspecie() != null) {
        model.setStrEspecie(data.getStrEspecie());
    }
    if (data.getStrRaza() != null) {
        model.setStrRaza(data.getStrRaza());
    }

    if (data.getClsTutor() != null) {
      model.setClsTutor(data.getClsTutor());
    }

    Repository.save(model);
  }


}
