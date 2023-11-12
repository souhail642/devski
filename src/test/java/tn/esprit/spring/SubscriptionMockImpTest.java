package tn.esprit.spring;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;  // Add this import
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;  // Add this import
import org.springframework.test.context.junit4.SpringRunner;  // Add this import

import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)  // Add this annotation
@SpringJUnitConfig(classes = SubscriptionMockImpTest.class)  // Add this annotation
@SpringBootTest
@ActiveProfiles("test")
public class SubscriptionMockImpTest {
    @InjectMocks
    private SubscriptionServicesImpl subscriptionServices;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testAddSubscription() {
        Subscription subscription3 = new Subscription();
        subscription3.setStartDate(LocalDate.of(2023, 7, 7));
        subscription3.setEndDate(LocalDate.of(2023, 8, 8));
        subscription3.setPrice(220.0f);
        subscription3.setTypeSub(TypeSubscription.MONTHLY);

        Subscription addedSubscription = subscriptionServices.addSubscription(subscription3);
        Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(addedSubscription.getNumSub());

        assertNotNull(addedSubscription);
        assertNotNull(retrievedSubscription);
        assertEquals(retrievedSubscription.getPrice(), addedSubscription.getPrice());
        assertEquals(retrievedSubscription.getStartDate(), addedSubscription.getStartDate());
        assertEquals(retrievedSubscription.getEndDate(), addedSubscription.getEndDate());
        assertEquals(retrievedSubscription.getTypeSub(), addedSubscription.getTypeSub());

        System.out.println("Test 'testAddSubscription' completed successfully.");}
        @Test
        void testUpdateSubscription() {
            Subscription subscription4 = new Subscription();
            subscription4.setStartDate(LocalDate.of(2023, 3, 3));
            subscription4.setEndDate(LocalDate.of(2023, 4, 4));
            subscription4.setPrice(220.0f);
            subscription4.setTypeSub(TypeSubscription.MONTHLY);

            Subscription addedSubscription = subscriptionServices.addSubscription(subscription4);

            addedSubscription.setPrice(180.0f);
            Subscription updatedSubscription = subscriptionServices.updateSubscription(addedSubscription);

            Subscription retrievedSubscription = subscriptionServices.retrieveSubscriptionById(updatedSubscription.getNumSub());

            assertNotNull(updatedSubscription);
            assertEquals(180.0f, updatedSubscription.getPrice());
            assertNotNull(retrievedSubscription);
            assertEquals(updatedSubscription.getNumSub(), retrievedSubscription.getNumSub());
            assertEquals(180.0f, retrievedSubscription.getPrice());

            System.out.println("Test 'testUpdateSubscription' completed successfully.");
        }

    @Test
    public void shouldRetrieveSubscriptionsByDates() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription(1L,LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));
        subscriptions.add(new Subscription(1L,LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6), 220.0f, TypeSubscription.MONTHLY));

        when(subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6))).thenReturn(subscriptions);

        List<Subscription> result = subscriptionServices.retrieveSubscriptionsByDates(LocalDate.of(2023, 11, 6), LocalDate.of(2023, 12, 6));

        assertEquals(subscriptions, result);

        System.out.println("shouldRetrieveSubscriptionsByDates succeeded!");
    }
}
