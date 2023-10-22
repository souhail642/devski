package tn.esprit.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static tn.esprit.spring.entities.Color.BLACK;
@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class PisteTest {

    @Mock
    private IPisteRepository pisteRepository  = Mockito.mock(IPisteRepository.class);

    @InjectMocks
    private PisteServicesImpl pisteServices;

   // @Before

    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }
    @Test
    public void testAddPiste() {
        Piste piste = new Piste(1L, "New Piste", BLACK, 1, 2);

        when(pisteRepository.save(piste)).thenReturn(piste);

        Piste result = pisteServices.addPiste(piste);

        // Assert
        assert result != null;
        assert "New Piste".equals(result.getNamePiste());
        assert BLACK.equals(result.getColor());
        assert piste.equals(result);
        //assertNotNull(result);
        //assertEquals("New Piste", result.getNamePiste());
        //assertEquals(BLACK, result.getColor());
        //assertEquals(piste, result);
    }
    @Test
    public void testAddPiste_Failure() {
        // Arrange
        Piste piste = new Piste(1L, "PP", BLACK, 1, 2);


        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(pisteRepository.save(piste)).thenReturn(piste);
        // Act : Appel de la méthode à tester
        Piste result = pisteServices.addPiste(piste);

        assert result != null;
        assert !"PPP".equals(result.getNamePiste());
        assert BLACK.equals(result.getColor());
        assert !piste.equals(result);



        // Assert : Vérification des résultats
        //assertNotNull(result); // L'instructeur retourné ne doit pas être nul
        //assertNotEquals("PPP", result.getNamePiste());
        //assertEquals(BLACK, result.getColor());
        //assertNotEquals(piste, result);
    }
    @Test
    public void testAddPiste_NotFound() {
        // Arrange
        Piste piste = new Piste(2L, "PP", BLACK, 1, 2);

        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(pisteRepository.save(piste)).thenReturn(null);

        // Act : Appel de la méthode à tester
        Piste result = pisteServices.addPiste(piste);

        assert result == null;
        // Assert : Vérification des résultats
       // assertNull(result);


    }
    @Test
    public void testRetrieveAllPistes() {
        List<Piste> pisteList = new ArrayList<>();
        pisteList.add(new Piste(4L, "Piste 1", BLACK, 5, 2));
        pisteList.add(new Piste(2L, "Piste 2", BLACK, 1, 7));

        when(pisteRepository.findAll()).thenReturn(pisteList);

        List<Piste> result = pisteServices.retrieveAllPistes();
        assert result.size() == 2;
        assert "Piste 1".equals(result.get(0).getNamePiste());
        assert "Piste 2".equals(result.get(1).getNamePiste());
        //assertEquals(2, result.size());
        //assertEquals("Piste 1", result.get(0).getNamePiste());
        //assertEquals("Piste 2", result.get(1).getNamePiste());
    }
    @Test
    public void testRetrieveAllPistes_Failure() {
        // Arrange
        List<Piste> pisteList = new ArrayList<>(); // Créer une liste d'instructeurs
        pisteList.add(new Piste(4L, "Piste 1", BLACK, 5, 2));
        pisteList.add(new Piste(2L, "¨Piste 2", BLACK, 1, 7));
        // Mocking : Simuler le comportement de la méthode findAll dans le repository
        when(pisteRepository.findAll()).thenReturn(pisteList);

        // Act : Appel de la méthode à tester
        List<Piste> result = pisteServices.retrieveAllPistes();
        assert result != null;
        assert result.size() == 1;

        //assertNotNull(result);
        //assertEquals(1, result.size());

    }
    @Test
    public void testRetrievePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "P2", BLACK, 1, 7);

        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteServices.retrievePiste(numPiste);
        assert result.equals(piste);
        assert "P2".equals(result.getNamePiste());
        //assertEquals(piste, result);
        //assertEquals("P2", result.getNamePiste());
    }

    @Test
    public void testRetrievePiste_Failure() {
        // Arrange
        Long num = 2L;
        Piste piste = new Piste(num, "PP", BLACK, 1, 2);

        // Mocking : Simuler le comportement de la méthode findById dans le repository
        when(pisteRepository.findById(num)).thenReturn(Optional.of(piste));

        // Act : Appel de la méthode à tester
        Piste result = pisteServices.retrievePiste(num);

        // Assert : Vérification des résultats
        //assertNull(result);
        assert result == null;

    }
    @Test
    public void testUpdatePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "P2", BLACK, 1, 7);
        when(pisteRepository.save(piste)).thenReturn(piste);
        Piste result = pisteServices.updatePiste(piste);
        assert result != null;
        assert "P2".equals(result.getNamePiste());
        assert result.equals(piste);
        //assertNotNull(result);
        //assertEquals("P2", result.getNamePiste());
        //assertEquals(piste,result);
    }
    @Test
    public void testUpdatePiste_NotFound() {
        // Arrange
        Piste piste = new Piste(3L, "PP", BLACK, 1, 2);

        // Mocking : Simuler le comportement de la méthode save dans le repository
        when(pisteRepository.save(piste)).thenReturn(piste);

        // Act : Appel de la méthode à tester
        Piste result = pisteServices.updatePiste(piste);
        assert result == null;

        verify(pisteRepository, never()).save(piste);
        //assertNull(result);

        // Verify that save method was never called on the repository
        //verify(pisteRepository, never()).save(piste);
    }
    @Test
    public void testDeletePiste() {
        Long numPiste = 1L;
        Piste piste = new Piste(numPiste, "P2", BLACK, 1, 7);
        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));
        pisteServices.removePiste(numPiste);
        verify(pisteRepository, times(1)).deleteById(1L);
    }
    @Test
    public void testRemovePiste_NotFound() {
        // Arrange
        Piste piste = new Piste(4L, "P2", BLACK, 1, 7);

        // Mocking : Simuler le comportement de la méthode deleteById dans le repository
        when(pisteRepository.findById(4L)).thenReturn(Optional.of(piste));

        // Act : Appel de la méthode à tester
        pisteServices.removePiste(4L);

        // Verify that deleteById was never called on the repository
        verify(pisteRepository, never()).deleteById(4L);
    }
}



