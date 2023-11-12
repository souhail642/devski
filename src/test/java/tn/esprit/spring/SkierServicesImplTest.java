package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sun.jvm.hotspot.utilities.Assert;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.ISkierServices;
import tn.esprit.spring.services.SkierServicesImpl;

import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SkierServicesImplTest {

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SkierServicesImpl skierServicesImpl ;
    @Test
    public void retrieveAllSkiersTest() {
        List<Skier> skiers = skierServicesImpl.retrieveAllSkiers();
        Mockito.verify(skierRepository).findAll();

    }
}