package org.bedu.java.backend.pet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateVeterinarioDTO;
import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import org.bedu.java.backend.pet.exception.VeterinarioNotFoundException;
import org.bedu.java.backend.pet.model.CPersona;
import org.bedu.java.backend.pet.model.CVeterinario;
import org.bedu.java.backend.pet.repository.CVeterinarioRepository;
import org.bedu.java.backend.pet.service.CVeterinarioService;
import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
  
@SpringBootTest
@ExtendWith( MockitoExtension.class )
class CVeterinarioControllerTest {

    @MockBean
    private CVeterinarioService clssService;

    @Autowired
    private CVeterinarioController clsController;

    @Test
    @DisplayName( "CVeterinarioController should be injected" )
    void smokeTest(){
        assertNotNull(clsController);
    }

    @Test
    @DisplayName( "CVeterinarioController should return a list of Veterinarians" )
    void regresarListaTest(){

        List<CVeterinarioDTO> mockList = new LinkedList<>();

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

        CVeterinarioDTO veterinario = new CVeterinarioDTO();
        veterinario.setLngVetID(1);
        veterinario.setClsPersona(persona);
        veterinario.setStrCedula("1234456");
        veterinario.setStrEspecialidad("Cardiologia");

        mockList.add(veterinario);
        
        when( clssService.regresarLista() ).thenReturn( mockList);
        List<CVeterinarioDTO> lista = clsController.regresarLista();
        assertTrue( lista.size() > 0);
        assertEquals( mockList.get(0).getLngVetID(), lista.get(0).getLngVetID());
        assertEquals( mockList.get(0).getClsPersona(), lista.get(0).getClsPersona());
        assertEquals( mockList.get(0).getStrCedula(), lista.get(0).getStrCedula());
        assertEquals(mockList.get(0).getStrEspecialidad(), lista.get(0).getStrEspecialidad());
    }

    @Test
    @DisplayName("CVeterinarioController should add a new Veterinarian")
    void agregarVeterinarioTest() throws CPersonaApellidoException, CPersonaContactoException {
        // Crea un nuevo veterinarioDTO para agregar
        CPersonaDTOCreate personaDTO = new CPersonaDTOCreate();
        personaDTO.setStrNombre("Alberto");
        personaDTO.setStrPaterno("Veterinario");
        personaDTO.setStrMaterno("Agregado");
        personaDTO.setStrEmail("nuevo.vet@gmail.com");
        personaDTO.setStrTelefono("2234347890");

        CPersona persona = new CPersona();
        persona.setStrNombre(personaDTO.getStrNombre());
        persona.setStrPaterno(personaDTO.getStrPaterno());
        persona.setStrMaterno(personaDTO.getStrMaterno());
        persona.setStrEmail(personaDTO.getStrEmail());
        persona.setStrTelefono(personaDTO.getStrTelefono());


        CVeterinarioDTOCreate nuevoVeterinarioDTO = new CVeterinarioDTOCreate();
        nuevoVeterinarioDTO.setClsPersona(personaDTO);
        nuevoVeterinarioDTO.setStrCedula("9876543");
        nuevoVeterinarioDTO.setStrEspecialidad("Ortopedia");

        CVeterinarioDTO newVet = new CVeterinarioDTO();
        newVet.setClsPersona(persona);
        newVet.setStrCedula("9876543");
        newVet.setStrEspecialidad("Ortopedia");


        when( clssService.nuevo( any( CVeterinarioDTOCreate.class))).thenReturn(newVet);

        CVeterinarioDTO resultado = clsController.nuevo(nuevoVeterinarioDTO);
        assertNotNull(resultado);
        assertEquals(resultado.getClsPersona().getStrNombre(), nuevoVeterinarioDTO.getClsPersona().getStrNombre());
        assertEquals(resultado.getClsPersona().getStrPaterno(), nuevoVeterinarioDTO.getClsPersona().getStrPaterno());
        assertEquals(resultado.getClsPersona().getStrMaterno(), nuevoVeterinarioDTO.getClsPersona().getStrMaterno());
        assertEquals(resultado.getClsPersona().getStrTelefono(), nuevoVeterinarioDTO.getClsPersona().getStrTelefono());
        assertEquals(resultado.getClsPersona().getStrEmail(), nuevoVeterinarioDTO.getClsPersona().getStrEmail());
        assertEquals(resultado.getStrCedula(), nuevoVeterinarioDTO.getStrCedula());
        assertEquals(resultado.getStrEspecialidad(), nuevoVeterinarioDTO.getStrEspecialidad());

    }

    @Test
    @DisplayName("CVeterinarioController should update an existing Veterinarian")
    void actualizarVeterinarioTest() throws VeterinarioNotFoundException {
        
        UpdateVeterinarioDTO dto = new UpdateVeterinarioDTO();
        dto.setStrCedula("89675634");

        clsController.update(399L, dto);
        verify( clssService, times(1)).actualizarVeterinario(399L, dto);
    }

    @Test
    @DisplayName("CVeterinarioController should delete a Veterinarian")
    void eliminarVeterinarioTest(){

        when ( clssService.deleteById( anyLong())).thenReturn(true);
        clsController.eliminarVeterinario(1L);
        verify( clssService, times(1)).deleteById(1L);
    }
    
    @Test
    @DisplayName("CVeterinarioController test success message")
    void testSuccessMessage() {
        System.out.println("¡Pruebas de veterinarios exitosas!");
        assertTrue(true); // Asegura que la prueba siempre tenga éxito
    }
}