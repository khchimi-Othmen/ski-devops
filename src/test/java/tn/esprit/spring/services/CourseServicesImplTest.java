package tn.esprit.spring.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CourseServicesImplTest {

    @InjectMocks
    private CourseServicesImpl courseService;

    @Mock
    private ICourseRepository courseRepository;

    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllCourses() {
        // Mock behavior of the repository method
        List<Course> sampleCourses = new ArrayList<>();
        when(courseRepository.findAll()).thenReturn(sampleCourses);

        List<Course> retrievedCourses = courseService.retrieveAllCourses();

        assertNotNull(retrievedCourses);
        assertEquals(sampleCourses, retrievedCourses);
    }

    @Test
    public void testAddCourse() {
        // Mock behavior of the repository method
        Course newCourse = new Course();
        when(courseRepository.save(any(Course.class))).thenReturn(newCourse);

        Course addedCourse = courseService.addCourse(newCourse);

        assertNotNull(addedCourse);
        assertEquals(newCourse, addedCourse);
    }

    @Test
    public void testUpdateCourse() {
        // Mock behavior of the repository method
        Course updatedCourse = new Course();
        when(courseRepository.save(any(Course.class))).thenReturn(updatedCourse);

        Course updated = courseService.updateCourse(new Course());

        assertNotNull(updated);
        assertEquals(updatedCourse, updated);
    }

    @Test
    public void testRetrieveCourse() {
        // Mock behavior of the repository method
        Long courseNum = 1L;
        Course sampleCourse = new Course();
        when(courseRepository.findById(courseNum)).thenReturn(Optional.of(sampleCourse));

        Course retrievedCourse = courseService.retrieveCourse(courseNum);

        assertNotNull(retrievedCourse);
        assertEquals(sampleCourse, retrievedCourse);
    }
}
