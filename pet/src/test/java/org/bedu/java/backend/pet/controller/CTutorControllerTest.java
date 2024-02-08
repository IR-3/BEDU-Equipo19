package org.bedu.java.backend.pet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CTutorDTO;
import org.bedu.java.backend.pet.service.CTutorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@ExtendWith( MockitoExtension.class )
class CTutorControllerTest {

    @MockBean
    private CTutorService clsService;

    @Autowired
    private CTutorController clsController;

    @Test
    @DisplayName( "CTutorController should be injected" )
    void smokeTest(){
        assertNotNull ( clsController );
    }

    @Test
    @DisplayName( "CTutorController should return a list" )
    void regresarListaTest() {

        List<CTutorDTO> mockList = new LinkedList<>();

        CPersonaDTO persona = new CPersonaDTO();
        persona.setStrNombre( "Vladimir" );
        persona.setStrPaterno( "Rios" );
        persona.setStrMaterno( "Torres" );
        persona.setStrEmail( "vlad.rioto@gmail.com" );
        persona.setStrTelefono( "4761234567" );

        CTutorDTO tutor = new CTutorDTO();
        tutor.setClsTutor( persona );
        tutor.setLngTutorID( 1 );

        mockList.add( tutor );

        when( clsService.regresarLista() ).thenReturn( mockList );
        List<CTutorDTO> lista = clsController.regresarLista();
        assertEquals( mockList, lista );
  }
}
