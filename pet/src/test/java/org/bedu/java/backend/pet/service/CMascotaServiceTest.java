package org.bedu.java.backend.pet.service;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateMascotaDTO;
import org.bedu.java.backend.pet.exception.CMascotaTutorException;
import org.bedu.java.backend.pet.exception.MascotaNotFoundException;
import org.bedu.java.backend.pet.model.CMascota;
import org.bedu.java.backend.pet.model.CPersona;
import org.bedu.java.backend.pet.model.CTutor;
import org.bedu.java.backend.pet.repository.CMascotaRepository;
import org.bedu.java.backend.pet.repository.CTutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CMascotaServiceTest {

  @MockBean
  private CTutorRepository clsTutorRepo;

  @MockBean
  private CMascotaRepository clsRepository;

  @Autowired
  private CMascotaService clsService;

  @Test
  @DisplayName( "CMascotaService should be injected" )
  void smokeTest() {
    assertNotNull( clsService );
  }

  @Test
  @DisplayName( "CMascotaService should return a list of pets" )
  void regresarListaTest() {

    List<CMascota> mockList = new LinkedList<>();

    CPersona persona = new CPersona();
    persona.setStrNombre( "Vladimir" );
    persona.setStrPaterno( "Rios" );
    persona.setStrMaterno( "Torres" );
    persona.setStrEmail( "vlad.rioto@gmail.com" );
    persona.setStrTelefono( "4761234567" );

    CTutor tutor = new CTutor();
    tutor.setClsTutor( persona );
    tutor.setLngTutorID( 1 );

    CMascota mascota = new CMascota();
    mascota.setLngMascotaID( 1 );
    mascota.setStrNombre( "Hermes" );
    mascota.setStrEspecie( "Tortuga" );
    mascota.setStrRaza( "Japonesa" );
    mascota.setClsTutor( tutor );

    mockList.add( mascota );

    when( clsRepository.findAll() ).thenReturn( mockList );
    List<CMascotaDTO> result = clsService.regresarLista();
    assertTrue( result.size() > 0 );
    assertEquals( mockList.get(0).getLngMascotaID(), result.get(0).getLngMascotaID() );
    assertEquals( mockList.get(0).getStrNombre(), result.get(0).getStrNombre() );
    assertEquals( mockList.get(0).getStrEspecie(), result.get(0).getStrEspecie() );
    assertEquals( mockList.get(0).getStrRaza(), result.get(0).getStrRaza() );
    assertEquals(
            mockList.get(0).getClsTutor().getLngTutorID(),
            result.get(0).getClsTutor().getLngTutorID() );
  }

  @Test
  @DisplayName( "CMascotaController should save a pet" )
  void nuevoTest() throws CMascotaTutorException {

    CPersona persona = new CPersona();
    persona.setStrNombre( "Alejandro" );
    persona.setStrMaterno( "Magno" );
    persona.setStrTelefono( "4771234567" );
    persona.setStrEmail( "alexMagnus@gmail.com" );

    CTutor tutor = new CTutor();
    tutor.setClsTutor( persona );
    tutor.setLngTutorID( 100L );

    when( clsTutorRepo.findById( anyLong() ) )
            .thenReturn( Optional.of( tutor ) );

    CMascota dataBase = new CMascota();
    dataBase.setStrNombre( "Bucefalo" );
    dataBase.setStrEspecie( "Caballo" );
    dataBase.setStrRaza( "Arabe" );
    dataBase.setLngMascotaID( 1 );
    dataBase.setClsTutor( tutor );

    when( clsRepository.save( any( CMascota.class ) ) )
      .thenReturn( dataBase );

    CMascotaDTOCreate dummy = new CMascotaDTOCreate();
    CMascotaDTO resultado = clsService.nuevo( dummy );
    assertEquals( resultado.getStrNombre(), dataBase.getStrNombre() );
    assertEquals( resultado.getStrEspecie(), dataBase.getStrEspecie() );
    assertEquals( resultado.getStrRaza(), dataBase.getStrRaza() );
    assertEquals( resultado.getLngMascotaID(), dataBase.getLngMascotaID() );
  }

  @Test
  @DisplayName( "CMascotaService should throws when a movie is not found" )
  void actualizarMascotaErrorTest() {
    UpdateMascotaDTO dto = new UpdateMascotaDTO();
    Optional<CMascota> dummy = Optional.empty();

    when( clsRepository.findById( anyLong() ) ).thenReturn( dummy );

    assertThrows(
      MascotaNotFoundException.class,
      () -> clsService.actualizarMascota(100L, dto ) );
  }

  @Test
  @DisplayName( "CMascotaService shuold update a movie" )
  void actualizarMascotaTest()
  throws MascotaNotFoundException {

    UpdateMascotaDTO dto = new UpdateMascotaDTO();
    dto.setStrNombre( "Canito" );
    dto.setStrEspecie( "Perro" );
    dto.setStrRaza( "Mestizo" );

    CMascota dataBase = new CMascota();
    dataBase.setStrNombre( "Canito" );
    dataBase.setStrEspecie( "Perro" );
    dataBase.setStrRaza( "Mestizo" );

    when( clsRepository.findById( anyLong() ) )
      .thenReturn( Optional.of( dataBase ) );

    clsService.actualizarMascota( 100L, dto );
    assertEquals( dto.getStrNombre(), dataBase.getStrNombre() );
    assertEquals( dto.getStrEspecie(), dataBase.getStrEspecie() );
    assertEquals( dto.getStrRaza(), dataBase.getStrRaza() );
  }
}