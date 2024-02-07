package org.bedu.java.backend.pet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import org.bedu.java.backend.pet.model.CPersona;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;

@SpringBootTest
class CVeterinarioServiceTest {

    @MockBean
    private CPersona clsCPersona;

    @MockBean
    private CVeterinarioRepository clsRepository;

    @Autowired
    private CVeterinarioServiceTest clsService;

    @Test
    @DisplayName( "CVeterinarioService should be injected")
    void smokeTest() {
        assertNotNull(clsService);
    }

    @Test
    @DisplayName (" CVeterinarioService should return a list of pets")
    void regresarListaTest(){

        List<CVeterinario> mockList = new LinkedList<>();

        CPersona persona = new CPersona();
        persona.setStrNombre("Joe");
        persona.setStrPaterno("Doe");
        persona.setStrMaterno("Smith");
        persona.setStrEmail("joe.doe@gmail.com");
        persona.setStrTelefono("6589542366");

        CVeterinario veterinarios = new CVeterinario();
        veterinarios.setLngVetID(1);
        veterinarios.setClsPersona(persona);
        veterinarios.setStrCedula("1234456");
        veterinarios.setStrEspecialidad("Cardiologia");

        mockList.add(veterinarios);

        when( clsRepository.findAll() ).thenReturn( mockList);
        List<CVeterinarioDTO> result = clsService.regresarListaTest();
        assertTrue( result.size() > 0);
        assertEquals( mockList.get(0).getLngVetID(), result.get(0).getLngVetID());
        assertEquals( mockList.get(0).getClsPersona(), result.get(0).getClsPersona());
        assertEquals( mockList.get(0).getStrCedula(), result.get(0).getStrCedula());
        assertEquals(mockList.get(0).getStrEspecialidad(), result.get(0).getStrEspecialidad());

    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("CVeterinarioService should add a new Veterinarian")
    void agregarVeterinarioTest() throws CPersonaApellidoException, CPersonaContactoException {

        CPersonaDTO personaDTO = new CPersonaDTO();
        personaDTO.setStrNombre("Joe");
        personaDTO.setStrPaterno("Doe");
        personaDTO.setStrMaterno("Smith");
        personaDTO.setStrEmail("joe.doe@gmail.com");
        personaDTO.setStrTelefono("6589542366");
        
        CPersona persona = new CPersona();
        persona.setStrNombre(personaDTO.getStrNombre());
        persona.setStrPaterno(personaDTO.getStrPaterno());
        persona.setStrMaterno(personaDTO.getStrMaterno());
        persona.setStrEmail(personaDTO.getStrEmail());
        persona.setStrTelefono(personaDTO.getStrTelefono());

        CVeterinario newVeterinario = new CVeterinario();
        newVeterinario.setClsPersona(persona);
        newVeterinario.setStrCedula("6719278334");
        newVeterinario.setStrEspecialidad("Oftalmologia");

        when( clsRepository.save( any(CVeterinario.class) ) ).thenReturn(newVeterinario);
        CVeterinarioDTOCreate prueba = new CVeterinarioDTOCreate();
        assertEquals(newVeterinario.getClsPersona(), prueba.getClsPersona());
        assertEquals(newVeterinario.getStrCedula(), prueba.getStrCedula());
        assertEquals(newVeterinario.getStrEspecialidad(), prueba.getStrEspecialidad());


    }

}
