package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.dto.CCitaFindByFechaHora;
import org.bedu.java.backend.pet.dto.UpdateCitaDTO;
import org.bedu.java.backend.pet.exception.CitaExistenteException;
import org.bedu.java.backend.pet.exception.CitaNotFoundException;
import org.bedu.java.backend.pet.exception.MascotaNotFoundException;
import org.bedu.java.backend.pet.exception.VeterinarioNotFoundException;
import org.bedu.java.backend.pet.mapper.CCitaMapper;
import org.bedu.java.backend.pet.model.CCita;
import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.repository.CCitaRepository;
import org.bedu.java.backend.pet.repository.CMascotaRepository;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CCitaService {

  private CCitaRepository        clsRepository;
  private CCitaMapper            clsMapper;
  private CMascotaRepository     clsMascotaRepo;
  private CVeterinarioRepository clsVetRepo;

  @Autowired
  public CCitaService( 
    CCitaRepository         repository,
    CCitaMapper             mapper,
    CMascotaRepository      mascotaRepo,
    CVeterinarioRepository  vetRepo ) {

    clsRepository  = repository;
    clsMapper      = mapper;
    clsMascotaRepo = mascotaRepo;
    clsVetRepo     = vetRepo;
  }

  // Regresa todas la citas
  public List<CCitaDTO> regresarLista() {
    return clsRepository.findAll().stream()
        .map( x -> clsMapper.enDTO( x, x.getClsMascota(), x.getClsVeterinario() ) )
        .toList();
  }

  public CCitaDTO findByDateHour( CCitaFindByFechaHora data )
  throws CitaNotFoundException {
    Optional<CCita> optional = clsRepository.findCitaByClsDateAndClsTime( data.getClsDate(), data.getClsTime() );

    if( optional.isPresent() ) {
      CCita cita = optional.get();
      return clsMapper.toDto( cita );
    } else {
      throw new CitaNotFoundException();
    }
  }

  // Agrega una cita a la base de datos
  @Transactional
  public CCitaDTO nuevo( CCitaDTOCreate frontInfo )
  throws MascotaNotFoundException, VeterinarioNotFoundException, CitaExistenteException {

    Optional<CMascota> mascota = clsMascotaRepo.findById( frontInfo.getLngMascotaID() );
    if( !mascota.isPresent() ) {
      throw new MascotaNotFoundException();
    }

    Optional<CVeterinario> veterinario = clsVetRepo.findById( frontInfo.getLngVetID() );
    if( !veterinario.isPresent() ) {
      throw new VeterinarioNotFoundException();
    }

    LocalTime horaDeInicio = frontInfo.getClsTime();
    int duracionEnMinutos = frontInfo.getIntMinutos();
    LocalTime clsFin = horaDeInicio.plusMinutes( duracionEnMinutos );

    List<CCita> citasSolapadas = clsRepository.findCitasBetweenTimes( frontInfo.getClsDate(), frontInfo.getClsTime(), clsFin );
    if( !citasSolapadas.isEmpty() ) {
      throw new CitaExistenteException();
    }

    // Actualiza la tabla
    CCita nuevo = clsRepository.save( clsMapper.enModel( frontInfo, mascota.get(), veterinario.get() ));
    nuevo.setClsFin( clsFin );

    return clsMapper.enDTO( nuevo,
        nuevo.getClsMascota(),
        nuevo.getClsVeterinario() );
  }

  // Eliminar la tabla
  @Transactional
  public boolean deleteById( Long citaId ) {
    Optional<CCita> citaOptional = clsRepository.findById( citaId );

    if( citaOptional.isPresent() ) {     // La cita existe, la eliminamos
      clsRepository.delete( citaOptional.get() );
      return true;
    } else {                             // La cita no existe
      return false;
    }
  }

  // Actualizar tabla cita
  @Transactional
  public void actualizarCita( Long citaId, UpdateCitaDTO data )
  throws CitaNotFoundException {
    Optional<CCita> result = clsRepository.findById( citaId );
    if( result.isEmpty() ) {
      throw new CitaNotFoundException();
    }

    CCita model = result.get();

    // actualizar datos de Cita
    if( data.getClsDate() != null ) {
      model.setClsDate( data.getClsDate() );
    }

    if( data.getClsTime() != null ) {
      model.setClsTime( data.getClsTime() );
    }

    if( data.getStrTratamiento() != null ) {
      model.setStrTratamiento( data.getStrTratamiento() );
    }

    if( data.getClsMascota() != null ) {
      model.setClsMascota( data.getClsMascota() );
    }

    if( data.getClsVeterinario() != null ) {
      model.setClsVeterinario( data.getClsVeterinario() );
    }

    clsRepository.save( model );
  }

}
