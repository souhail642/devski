package tn.esprit.spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.RegistrationServicesImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServicesImplTest {

    @InjectMocks
    private RegistrationServicesImpl registrationServices;

    @Mock
    private IRegistrationRepository registrationRepository;

    @Mock
    private ISkierRepository skierRepository;

    @Mock
    private ICourseRepository courseRepository;

    @Test
    public void testAddRegistrationAndAssignToSkier() {
        // Create a registration object.
        Registration registration = new Registration();
        registration.setNumWeek(1);

        // Create a skier object.
        Skier skier = new Skier();
        skier.setNumSkier(1L);

        // Mock the behavior of the skierRepository.findById() method.
        when(skierRepository.findById(skier.getNumSkier())).thenReturn(Optional.of(skier));

        // Mock the behavior of the registrationRepository.save() method.
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Call the addRegistrationAndAssignToSkier() method.
        Registration savedRegistration = registrationServices.addRegistrationAndAssignToSkier(registration, skier.getNumSkier());

        // Verify that the registration object was saved to the database.
        assertNotNull(savedRegistration);
        assertEquals(registration.getNumWeek(), savedRegistration.getNumWeek());
        assertEquals(skier.getNumSkier(), savedRegistration.getSkier().getNumSkier());
    }

    @Test
    public void testAssignRegistrationToCourse() {
        // Create a registration object.
        Registration registration = new Registration();
        registration.setNumRegistration(1L);

        // Create a course object.
        Course course = new Course();
        course.setNumCourse(1L);

        // Mock the behavior of the registrationRepository.findById() method.
        when(registrationRepository.findById(registration.getNumRegistration())).thenReturn(Optional.of(registration));

        // Mock the behavior of the courseRepository.findById() method.
        when(courseRepository.findById(course.getNumCourse())).thenReturn(Optional.of(course));

        // Mock the behavior of the registrationRepository.save() method.
        when(registrationRepository.save(registration)).thenReturn(registration);

        // Call the assignRegistrationToCourse() method.
        Registration savedRegistration = registrationServices.assignRegistrationToCourse(registration.getNumRegistration(), course.getNumCourse());

        // Verify that the registration object was saved to the database.
        assertNotNull(savedRegistration);
        assertEquals(course.getNumCourse(), savedRegistration.getCourse().getNumCourse());
    }
}
