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

  @Autowired
  private CCitaRepository Repository;

  @Autowired
  private CCitaMapper Mapper;

  @Autowired
  private CMascotaRepository MascotaRepo;

  @Autowired
  private CVeterinarioRepository VetRepo;

  // Regresa todas la citas
  public List<CCitaDTO> RegresarLista() {
    return Repository.findAll().stream()
        .map(x -> Mapper.EnDTO(x, x.getClsMascota(), x.getClsVeterinario()))
        .toList();
  }

  public CCitaDTO findByDateHour(CCitaFindByFechaHora data) throws CitaNotFoundException {
    Optional<CCita> optional = Repository.findCitaByClsDateAndClsTime(data.getClsDate(), data.getClsTime());

    if (optional.isPresent()) {
        CCita cita = optional.get();
        return Mapper.toDto(cita);
    } else {
        throw new CitaNotFoundException();
    }
}

  // Agrega una cita a la base de datos
  @Transactional
  public CCitaDTO Nuevo(CCitaDTOCreate frontInfo)
      throws MascotaNotFoundException, VeterinarioNotFoundException, CitaExistenteException {
  
      Optional<CMascota> mascota = MascotaRepo.findById(frontInfo.getLngMascotaID());
      if (!mascota.isPresent()) {
          throw new MascotaNotFoundException();
      }
  
      Optional<CVeterinario> veterinario = VetRepo.findById(frontInfo.getLngVetID());
      if (!veterinario.isPresent()) {
          throw new VeterinarioNotFoundException();
      }
  
      Optional<CCita> citaExistente = Repository.findCitaByClsDateAndClsTime(frontInfo.getClsDate(), frontInfo.getClsTime());
      if (citaExistente.isPresent()) {
          throw new CitaExistenteException();
      }
  
      // Actualiza la tabla
      CCita nuevo = Repository.save(Mapper.EnModel(frontInfo, mascota.get(), veterinario.get()));
  
      return Mapper.EnDTO(nuevo,
          nuevo.getClsMascota(),
          nuevo.getClsVeterinario());
  }
  

  // Eliminar la tabla
  @Transactional
  public boolean deleteById(Long citaId) {
    Optional<CCita> citaOptional = Repository.findById(citaId);

    if (citaOptional.isPresent()) {
      // La cita existe, la eliminamos
      Repository.delete(citaOptional.get());
      return true;
    } else {
      // La cita no existe
      return false;
    }
  }

  // Actualizar tabla cita
  @Transactional
  public void actualizarCita(Long citaId, UpdateCitaDTO data) throws CitaNotFoundException {
    Optional<CCita> result = Repository.findById(citaId);
    if (result.isEmpty()) {
      throw new CitaNotFoundException();
    }

    CCita model = result.get();

    // actualizar datos de Cita
    if (data.getClsDate() != null) {
      model.setClsDate(data.getClsDate());
    }

    if (data.getClsTime() != null) {
      model.setClsTime(data.getClsTime());
    }

    if (data.getStrTratamiento() != null) {
      model.setStrTratamiento(data.getStrTratamiento());
    }

    if (data.getClsMascota() != null) {
      model.setClsMascota(data.getClsMascota());
    }

    if (data.getClsVeterinario() != null) {
      model.setClsVeterinario(data.getClsVeterinario());
    }

    Repository.save(model);
  }

}
