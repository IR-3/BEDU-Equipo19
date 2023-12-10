package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CCitaDTO;
import org.bedu.java.backend.pet.dto.CCitaDTOCreate;
import org.bedu.java.backend.pet.mapper.CCitaMapper;
import org.bedu.java.backend.pet.model.CCita;
import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.repository.CCitaRepository;
import org.bedu.java.backend.pet.repository.CMascotaRepository;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CCitaService {

  @Autowired
  private CCitaRepository  Repository;

  @Autowired
  private CCitaMapper  Mapper;

  @Autowired
  private CMascotaRepository  MascotaRepo;

  @Autowired
  private CVeterinarioRepository  VetRepo;

  // Regresa todas la citas
  public List<CCitaDTO>  RegresarLista() {
    return Repository.findAll().stream()
            .map( x ->
              Mapper.EnDTO
              ( x, x.getClsMascota(), x.getClsVeterinario() ) )
            .toList();
  }


  // Agrega una cita a la base de datos
  public CCitaDTO Nuevo( CCitaDTOCreate frontInfo ) {

    Optional<CMascota> mascota;
    mascota = MascotaRepo.findById( frontInfo.getLngMascotaID() );
    if( !mascota.isPresent() )
      return null;

    Optional<CVeterinario> veterinario;
    veterinario = VetRepo.findById( frontInfo.getLngVetID() );
    if( !veterinario.isPresent() )
      return null;

    // Actualiza la tabla
    CCita nuevo = Repository.save( Mapper.EnModel
                  ( frontInfo, mascota.get(), veterinario.get() ) );

    return Mapper.EnDTO( nuevo,
             nuevo.getClsMascota(),
             nuevo.getClsVeterinario() );
  }

}
