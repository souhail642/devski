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
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.ISkierServices;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Test
    public void addSkierTest() {
        Skier skier = new Skier();
        skier.setFirstName("nassim");
        skier.setLastName("allouche");
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        skier.setSubscription(subscription);

        when(skierRepository.save(skier)).thenReturn(skier);

        Skier savedSkier = skierServicesImpl.addSkier(skier);

        verify(skierRepository).save(skier);
        assert (savedSkier.equals(skier));
    }
    @Test
    public void removeSkierTest() {
        Long numSkier = 1L;

        skierRepository.deleteById(numSkier);

        verify(skierRepository).deleteById(numSkier);
    }
}