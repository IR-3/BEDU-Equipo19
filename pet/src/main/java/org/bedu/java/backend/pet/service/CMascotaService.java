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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CMascotaService {

  private CMascotaRepository  clsRepository;
  private CMascotaMapper      clsMapper;
  private CTutorRepository    clsTutorRepo;

  @Autowired
  public CMascotaService( 
    CMascotaRepository repository,
    CMascotaMapper     mapper,
    CTutorRepository   tutorRepo ) {

    clsRepository = repository;
    clsMapper     = mapper;
    clsTutorRepo  = tutorRepo;
  }


  // Regresa todas las mascotas
  public List<CMascotaDTO> regresarLista() {
    return clsRepository.findAll().stream()
           .map( x -> clsMapper.enDTO( x, x.getClsTutor() ) )
           .toList();
  }


  // Agrega una mascota a la base de datos
  @Transactional
  public CMascotaDTO nuevo( CMascotaDTOCreate frontInfo )
  throws CMascotaTutorException {

    // Obtiene el tutor
    Optional<CTutor> tutor;
    tutor = clsTutorRepo.findById( frontInfo.getLngTutorID() );
    if( !tutor.isPresent() )
      throw new CMascotaTutorException();

    // Actualiza la tabla
    CMascota nuevo = clsRepository.save
      ( clsMapper.enModel( frontInfo, tutor.get() ) );

    return clsMapper.enDTO( nuevo, nuevo.getClsTutor() );
  }

  //Eliminar la tabla
  @Transactional
  public boolean deleteById( Long mascotaId ) {
    Optional<CMascota> mascota = clsRepository.findById( mascotaId );

    if( mascota.isPresent() ){
      clsRepository.delete( mascota.get() );
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public void actualizarMascota( Long mascotaId, UpdateMascotaDTO data )
  throws MascotaNotFoundException {
    Optional<CMascota> result = clsRepository.findById( mascotaId );
    if( result.isEmpty() ) {
      throw new MascotaNotFoundException();
    }

    CMascota model = result.get();

    // Actualizar campos de mascota
    if( data.getStrNombre() != null ) {
      model.setStrNombre( data.getStrNombre() );
    }
    if( data.getStrEspecie() != null ) {
      model.setStrEspecie( data.getStrEspecie() );
    }
    if( data.getStrRaza() != null ) {
      model.setStrRaza( data.getStrRaza() );
    }
    if( data.getClsTutor() != null ) {
      model.setClsTutor( data.getClsTutor() );
    }

    clsRepository.save( model );
  }
}
