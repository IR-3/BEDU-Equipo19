package org.bedu.java.backend.pet.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.bedu.java.backend.pet.dto.CPersonaDTO;
import org.bedu.java.backend.pet.dto.CPersonaDTOCreate;
import org.bedu.java.backend.pet.dto.CVeterinarioDTO;
import org.bedu.java.backend.pet.dto.CVeterinarioDTOCreate;
import org.bedu.java.backend.pet.dto.UpdateVeterinarioDTO;
import org.bedu.java.backend.pet.exception.CPersonaApellidoException;
import org.bedu.java.backend.pet.exception.CPersonaContactoException;
import org.bedu.java.backend.pet.exception.VeterinarioNotFoundException;
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
    private CVeterinarioService clsService;

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
        List<CVeterinarioDTO> result = clsService.regresarLista();
        assertTrue( result.size() > 0);
        assertEquals( mockList.get(0).getLngVetID(), result.get(0).getLngVetID());
        assertEquals( mockList.get(0).getClsPersona(), result.get(0).getClsPersona());
        assertEquals( mockList.get(0).getStrCedula(), result.get(0).getStrCedula());
        assertEquals(mockList.get(0).getStrEspecialidad(), result.get(0).getStrEspecialidad());

    }

    @Test
    @DisplayName("CVeterinarioService should add a new veterinarian")
    void agregarVeterinarioTest() throws CPersonaApellidoException, CPersonaContactoException {
        
        CPersonaDTOCreate personaDTO = new CPersonaDTOCreate();
        personaDTO.setStrNombre("Alan");
        personaDTO.setStrPaterno("Ignacio");
        personaDTO.setStrMaterno("Sanchez");
        personaDTO.setStrEmail("nuevo.vet@gmail.com");
        personaDTO.setStrTelefono("5514896745");

        CVeterinarioDTOCreate veterinarioDTOCreate = new CVeterinarioDTOCreate();
        veterinarioDTOCreate.setClsPersona(personaDTO);
        veterinarioDTOCreate.setStrCedula("98765623");
        veterinarioDTOCreate.setStrEspecialidad("Oftalmologia");

        when(clsRepository.save(any(CVeterinario.class))).thenAnswer(invocation -> {
            CVeterinario veterinarioGuardado = invocation.getArgument(0);
            veterinarioGuardado.setLngVetID(2); //ID asignado por la base de datos simulado
            return veterinarioGuardado;
        });

        CVeterinarioDTO result = clsService.nuevo(veterinarioDTOCreate);

        verify(clsRepository, times(1)).save(any(CVeterinario.class));

        assertNotNull(result);
        assertEquals(2, result.getLngVetID()); // El ID simulado asignado por el repositorio
        assertEquals(personaDTO.getStrNombre(), result.getClsPersona().getStrNombre());
        assertEquals(personaDTO.getStrPaterno(), result.getClsPersona().getStrPaterno());
        assertEquals(personaDTO.getStrMaterno(), result.getClsPersona().getStrMaterno());
        assertEquals(personaDTO.getStrTelefono(), result.getClsPersona().getStrTelefono());
        assertEquals(personaDTO.getStrEmail(), result.getClsPersona().getStrEmail());
        assertEquals("98765623", result.getStrCedula());
        assertEquals("Oftalmologia", result.getStrEspecialidad());
    }

    @Test
    @DisplayName("CVeterinarioService should update an existing veterinarian")
    void actualizarVeterinarioTest() throws VeterinarioNotFoundException {
       
        UpdateVeterinarioDTO updateData = new UpdateVeterinarioDTO();
        updateData.setStrCedula("9876598");
        updateData.setStrEspecialidad("Otorrinología");

        CVeterinario veterinarioExistente = new CVeterinario();
        veterinarioExistente.setLngVetID(1);
        veterinarioExistente.setStrCedula("98765623");
        veterinarioExistente.setStrEspecialidad("Oftalmologia");

        when(clsRepository.findById(1L)).thenReturn(Optional.of(veterinarioExistente));
        clsService.actualizarVeterinario(1L, updateData);
        verify(clsRepository, times(1)).save(any(CVeterinario.class));

        assertEquals("9876598", veterinarioExistente.getStrCedula());
        assertEquals("Otorrinología", veterinarioExistente.getStrEspecialidad());
    }

    @Test
    @DisplayName("CVeterinarioService should delete an existing veterinarian")
    void eliminarVeterinarioTest() {
        
        Long veterinarioId = 1L;

        CVeterinario veterinarioExistente = new CVeterinario();
        veterinarioExistente.setLngVetID(veterinarioId);

        when(clsRepository.findById(veterinarioId)).thenReturn(Optional.of(veterinarioExistente));
        boolean eliminado = clsService.deleteById(veterinarioId);
        verify(clsRepository, times(1)).delete(veterinarioExistente);
        assertTrue(eliminado);
    }

    @Test
    @DisplayName("CVeterinarioService should not delete a non-existing veterinarian")
    void eliminarVeterinarioNoEncontradoTest() {
        Long veterinarioId = 1L;

        when(clsRepository.findById(veterinarioId)).thenReturn(Optional.empty());
        boolean eliminado = clsService.deleteById(veterinarioId);
        verify(clsRepository, never()).delete(any(CVeterinario.class));
        assertFalse(eliminado);
    }

}
