package tn.esprit.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SkierServicesImplTest {

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private IPisteRepository pisteRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    private SkierServicesImpl skierServicesImpl;

    @BeforeEach
    public void setUp() {
        skierServicesImpl = new SkierServicesImpl(skierRepository, pisteRepository, courseRepository, registrationRepository, subscriptionRepository);
    }

    @Test
    public void retrieveAllSkiersTest() {
        List<Skier> skiers = skierServicesImpl.retrieveAllSkiers();
        Mockito.verify(skierRepository).findAll();
        Assert.assertNotNull(skiers);
    }

    @Test
    public void addSkierTest() {
        Skier skier = new Skier();
        Subscription subscription = new Subscription();
        skier.setSubscription(subscription);

        Skier savedSkier = skierServicesImpl.addSkier(skier);
        Mockito.verify(skierRepository).save(skier);
        Assert.assertEquals(skier, savedSkier);
    }



    @Test
    public void removeSkierTest() {
        Long numSkier = 3L;

        Mockito.doNothing().when(skierRepository).deleteById(numSkier);

        skierServicesImpl.removeSkier(numSkier);
        Mockito.verify(skierRepository).deleteById(numSkier);
    }
}




