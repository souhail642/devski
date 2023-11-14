package tn.esprit.spring;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseImplTest  {

  @Mock
  private ICourseRepository courseRepository;

  @InjectMocks
  private CourseServicesImpl courseServices;

  @Test
  public void testRetrieveAllCourses() {
    // Arrange
    when(courseRepository.findAll()).thenReturn(Arrays.asList(new Course(), new Course()));

    // Act
    List<Course> courses = courseServices.retrieveAllCourses();

    // Assert
    verify(courseRepository, times(1)).findAll();
    // Ajoutez des assertions supplémentaires selon vos besoins
  }

  @Test
  public void testAddCourse() {
    // Arrange
    Course course = new Course();
    when(courseRepository.save(course)).thenReturn(course);

    // Act
    Course addedCourse = courseServices.addCourse(course);

    // Assert
    verify(courseRepository, times(1)).save(course);
    // Ajoutez des assertions supplémentaires selon vos besoins
  }

  @Test
  public void updateCourseTest() {
    Course course = new Course();
    course.setTypeCourse(TypeCourse.INDIVIDUAL);

    when(courseRepository.save(course)).thenReturn(course);

    Course updatedCourse = courseServices.updateCourse(course);

    verify(courseRepository).save(course);
    assert (updatedCourse.equals(course));
  }
}


