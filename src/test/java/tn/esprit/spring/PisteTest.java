package tn.esprit.spring;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Color;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class PisteTest {
    @InjectMocks
    PisteServicesImpl pisteServices;

    @Mock
    IPisteRepository pisteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Piste piste1 = new Piste();
        piste1.setNumPiste(1L);
        piste1.setNamePiste("pisteone");
        piste1.setColor(Color.BLUE);

        Piste piste2 = new Piste();
        piste2.setNumPiste(2L);
        piste2.setNamePiste("pistetwo");
        piste2.setColor(Color.RED);


        // Mock behavior for the repository
        when(pisteRepository.findAll()).thenReturn(Arrays.asList(piste1,piste2));
        when(pisteRepository.save(any(Piste.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testRetrieveAllPistes() {
        List<Piste> pistes = pisteServices.retrieveAllPistes();

        assertEquals(2, pistes.size());
        assertTrue(pistes.stream().anyMatch(p->p.getNamePiste().equals("pisteone")));
        assertTrue(pistes.stream().anyMatch(d -> d.getNamePiste().equals("pistetwo")));

    }
    @Test
    void testAddpiste() {
        Piste newpiste = new Piste();
        newpiste.setNamePiste("P3");

        Piste savedPiste = pisteServices.addPiste(newpiste);

        assertNotNull(savedPiste);
        assertEquals("P3", savedPiste.getNamePiste());

        // Verify that the save method was called once
        verify(pisteRepository, times(1)).save(newpiste);
    }
}







