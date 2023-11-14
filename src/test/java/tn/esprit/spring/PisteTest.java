package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tn.esprit.spring.entities.Color.BLACK;

class PisteTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices;

    public PisteTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testAddPiste() {
        // Arrange
        Piste piste = new Piste(1L, "New Piste", BLACK, 1, 2);
        when(pisteRepository.save(piste)).thenReturn(piste);

        // Act
        Piste result = pisteServices.addPiste(piste);

        // Assert
        assertNotNull(result);
        assertEquals("New Piste", result.getNamePiste());
        assertEquals(BLACK, result.getColor());
        assertEquals(piste, result);
    }
    @Test
     void testRetrievePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "P2", BLACK, 1, 7);

        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteServices.retrievePiste(numPiste);

        assertEquals(piste, result);
        assertEquals("P2", result.getNamePiste());
    }

    /*@Test
    public void testAddPiste_Failure() {
        // Arrange
        Piste piste = new Piste(1L, "PP", Color.BLACK, 1, 2);

        when(pisteRepository.save(piste)).thenReturn(piste);

        // Act
        Piste result = pisteServices.addPiste(piste);

        // Assert
        assertNotNull(result);
        assertNotEquals("PPP", result.getNamePiste());
        assertEquals(Color.BLACK, result.getColor());
        assertNotEquals(piste, result);
    }*/

    @Test
    void testAddPiste_NotFound() {
        // Arrange
        Piste piste = new Piste(2L, "PP", BLACK, 1, 2);

        when(pisteRepository.save(piste)).thenReturn(null);

        // Act
        Piste result = pisteServices.addPiste(piste);

        // Assert
        assertNull(result);
    }

    @Test
     void testRetrieveAllPistes() {
        // Arrange
        List<Piste> pisteList = new ArrayList<>();
        pisteList.add(new Piste(4L, "Piste 1", BLACK, 5, 2));
        pisteList.add(new Piste(2L, "Piste 2", BLACK, 1, 7));

        when(pisteRepository.findAll()).thenReturn(pisteList);

        // Act
        List<Piste> result = pisteServices.retrieveAllPistes();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Piste 1", result.get(0).getNamePiste());
        assertEquals("Piste 2", result.get(1).getNamePiste());
    }

    // Other test methods...

    @Test
     void testDeletePiste() {
        // Arrange
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "P2", BLACK, 1, 7);
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        // Act
        pisteServices.removePiste(numPiste);

        // Assert
        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

 /*   @Test
    public void testRemovePiste_NotFound() {
        // Arrange
        Long numPiste = 4L;
        Piste piste = new Piste(numPiste, "P2", Color.BLACK, 1, 7);
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        // Act
        pisteServices.removePiste(numPiste);

        // Assert
        verify(pisteRepository, never()).deleteById(numPiste);
    }


  */
}



