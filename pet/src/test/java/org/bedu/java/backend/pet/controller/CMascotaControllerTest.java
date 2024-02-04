package org.bedu.java.backend.pet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.bedu.java.backend.pet.dto.CMascotaDTO;
import org.bedu.java.backend.pet.dto.CMascotaDTOCreate;
import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.exception.CMascotaTutorException;
import org.bedu.java.backend.pet.service.CMascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
@ExtendWith( MockitoExtension.class )
class CMascotaControllerTest {

  @MockBean
  private CMascotaService clsService;

  @Autowired
  private CMascotaController clsController;

  @Test
  @DisplayName( "CMascotaController should be injected" )
  void smokeTest() {
    assertNotNull( clsController );
  }

  @Test
  @DisplayName( "CMascotaController should return a list of pets" )
  void regresarListaTest() {

    List<CMascotaDTO> mockList = new LinkedList<>();

    CPersonaDTO persona = new CPersonaDTO();
    persona.setStrNombre( "Vladimir" );
    persona.setStrPaterno( "Rios" );
    persona.setStrMaterno( "Torres" );
    persona.setStrEmail( "vlad.rioto@gmail.com" );
    persona.setStrTelefono( "4761234567" );

    CTutorDTO tutor = new CTutorDTO();
    tutor.setClsTutor( persona );
    tutor.setLngTutorID( 1 );

    CMascotaDTO mascota = new CMascotaDTO();
    mascota.setLngMascotaID( 1 );
    mascota.setStrNombre( "Hermes" );
    mascota.setStrEspecie( "Tortuga" );
    mascota.setStrRaza( "Japonesa" );
    mascota.setClsTutor( tutor );

    mockList.add( mascota );

    when( clsService.regresarLista() ).thenReturn( mockList );
    List<CMascotaDTO> lista = clsController.regresarLista();
    assertEquals( mockList, lista );
  }
/*
  @Test
  @DisplayName( "CMascotaController should save a pet" )
  void nuevoTest()
  throws CMascotaTutorException {

    CMascotaDTOCreate nuevo = new CMascotaDTOCreate();
    nuevo.setStrNombre( "Hermes" );
    nuevo.setStrEspecie( "Tortuga" );
    nuevo.setStrRaza( "Japonesa" );
    nuevo.setLngTutorID( 1 );

    CMascotaDTO dataBase = new CMascotaDTO();
    dataBase.setStrNombre( "Hermes" );
    dataBase.setStrEspecie( "Tortuga" );
    dataBase.setStrRaza( "Japonesa" );
    dataBase.setLngMascotaID( 1 );

    when( mService.nuevo( any( CMascotaDTOCreate.class ) ) )
      .thenReturn( dataBase );

    CMascotaDTO resultado = mController.nuevo( nuevo );
    assertNotNull( resultado );
    assertEquals( resultado.getStrNombre(), nuevo.getStrNombre() );
    assertEquals( resultado.getStrEspecie(), nuevo.getStrEspecie() );
    assertEquals( resultado.getStrRaza(), nuevo.getStrRaza() );
  }*/

}
