package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;
import tn.esprit.spring.services.InstructorServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

 class InstructorServicesImplTest {

    @Mock
    private IInstructorRepository instructorRepository;

    @Mock
    private ICourseRepository courseRepository;

    @InjectMocks
    private InstructorServicesImpl instructorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
void testAddInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L, "rim", "chaouch");
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act
        Instructor result = instructorService.addInstructor(instructor);

        // Assert
        assertNotNull(result);
        assertEquals("rim", result.getFirstName());
        assertEquals("chaouch", result.getLastName());
        assertEquals(instructor, result);
    }

    /*@Test
     void testAddInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"rim","chaouch");
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act
        Instructor result = instructorService.addInstructor(instructor);

        // Assert
        assertNotNull(result);
        assertNotEquals("rima", result.getFirstName());
        assertNotEquals("ch", result.getLastName());
        assertNotEquals(instructor, result);
    }*/

    @Test
   void testRemoveInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L, "rim", "chaouch");
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act
        instructorService.removeInstructor(1L);

        // Assert
        verify(instructorRepository, times(1)).deleteById(1L);
    }

    /*@Test
    public void testRemoveInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"rim","chaouch");
        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        // Act & Assert
        assertThrows(Exception.class, () -> instructorService.removeInstructor(2L));
        verify(instructorRepository, never()).deleteById(1L);
    }*/

    @Test
   void testAddInstructorAndAssignToCourse() {
        // Arrange
        Instructor instructor = new Instructor(1L, "rim", "chaouch");
        Course course = new Course();
        Long numCourse = 1L;

        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act
        Instructor result = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCourses().size());
        assertTrue(result.getCourses().contains(course));
    }

    /*@Test
    public void testAddInstructorAndAssignToCourse_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"rim","chaouch");
        Course course = new Course();
        Long numCourse = 1L;

        when(courseRepository.findById(numCourse)).thenReturn(Optional.of(course));
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act
        Instructor result = instructorService.addInstructorAndAssignToCourse(instructor, numCourse);

        // Assert
        assertNotNull(result);
        assertSame(instructor, result);
        assertFalse(result.getCourses().contains(course));
        assertEquals(0, result.getCourses().size());
    }*/

    @Test
     void testRetrieveInstructor() {
        // Arrange
        Long numInstructor = 1L;
        Instructor instructor = new Instructor(numInstructor,"rim","chaouch");
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.of(instructor));

        // Act
        Instructor result = instructorService.retrieveInstructor(numInstructor);

        // Assert
        assertEquals("rim", result.getFirstName());
        assertEquals("chaouch", result.getLastName());
        assertNotNull(result);
        assertEquals(instructor, result);
    }

    @Test
    void testRetrieveInstructor_NotFound() {
        // Arrange
        Long numInstructor = 1L;
        Instructor instructor = new Instructor(numInstructor,"rim","chaouch");
        when(instructorRepository.findById(numInstructor)).thenReturn(Optional.empty());

        // Act
        Instructor result = instructorService.retrieveInstructor(numInstructor);

        // Assert
        assertNull(result);
    }

    @Test
    void testUpdateInstructor() {
        // Arrange
        Instructor instructor = new Instructor(1L,"rim","chaouch");
        when(instructorRepository.save(instructor)).thenReturn(instructor);

        // Act
        Instructor result = instructorService.updateInstructor(instructor);

        // Assert
        assertNotNull(result);
        assertEquals("rim", result.getFirstName());
        assertEquals("chaouch", result.getLastName());
        assertEquals(instructor, result);
    }

    /*@Test
    public void testUpdateInstructor_Failure() {
        // Arrange
        Instructor instructor = new Instructor(1L,"rim","chaouch");
        when(instructorRepository.save(instructor)).thenReturn(null);

        // Act
        Instructor result = instructorService.updateInstructor(instructor);

        // Assert
        assertNull(result);
        verify(instructorRepository, never()).save(instructor);
    }*/

    @Test
    void testRetrieveAllInstructors() {
        // Arrange
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(new Instructor(1L,"rim","chaouch"));
        instructorList.add(new Instructor(2L,"emna","gharbia"));
        when(instructorRepository.findAll()).thenReturn(instructorList);

        // Act
        List<Instructor> result = instructorService.retrieveAllInstructors();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("rim", result.get(0).getFirstName());
        assertEquals("chaouch", result.get(0).getLastName());
        assertEquals("emna", result.get(1).getFirstName());
        assertEquals("gharbia", result.get(1).getLastName());
        assertEquals(instructorList, result);
    }

    /*@Test
    public void testRetrieveAllInstructors_Failure() {
        // Arrange
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(new Instructor(1L,"rim","chaouch"));
        when(instructorRepository.findAll()).thenReturn(instructorList);

        // Act
        List<Instructor> result = instructorService.retrieveAllInstructors();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }*/
}
