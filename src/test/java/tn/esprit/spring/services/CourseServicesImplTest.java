package tn.esprit.spring.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseServicesImplTest {

    @Autowired
    private CourseServicesImpl courseService;
    @Autowired
    private ICourseRepository courseRepository;

    private Course course;

    // This method runs before each test method to set up common objects or resources.
    @BeforeEach
    void setUp() {
        // Create a new course with sample data
        course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(100f);
        course.setTimeSlot(3);
    }

    // This method runs after each test method to perform cleanup tasks.
    @AfterEach
    void tearDown() {
        // Delete the created course from the repository if it exists
        if (course != null && course.getNumCourse() != null) {
            courseRepository.delete(course);
        }
    }

    // Test case to verify retrieving all courses when the repository is empty.
    @Test
    @Order(1)
    void shouldRetrieveAllCoursesWhenEmpty() {
        // When: Retrieve all courses when the repository is empty
        List<Course> courses = courseService.retrieveAllCourses();

        // Then: Verify that an empty list is returned
        assertNotNull(courses, "Returned list should not be null");
        assertTrue(courses.isEmpty(), "Expected an empty list");
    }

    // Test case to verify adding a new course.
    @Test
    @Order(2)
    void shouldAddCourse() {
        // When: Add the course
        Course savedCourse = courseService.addCourse(course);

        // Then: Verify that the added course matches the input course
        assertNotNull(savedCourse, "Added course should not be null");
        assertEquals(course, savedCourse, "Added course should match the input course");
    }

    // Test case to verify updating an existing course.
    @Test
    @Order(3)
    void shouldUpdateCourse() {
        // Given: Add a course to the repository
        Course savedCourse = courseService.addCourse(course);

        // When: Update the course's level
        savedCourse.setLevel(2);
        Course updatedCourse = courseService.updateCourse(savedCourse);

        // Then: Verify that the updated course has the new level
        assertNotNull(updatedCourse, "Updated course should not be null");
        assertEquals(2, updatedCourse.getLevel(), "Course level should be updated");
    }

    // Test case to verify retrieving a course by its ID.
    @Test
    @Order(4)
    void shouldRetrieveCourseById() {
        // Given: Add a course to the repository
        Course savedCourse = courseService.addCourse(course);

        // When: Retrieve the course by ID
        Course retrievedCourse = courseService.retrieveCourse(savedCourse.getNumCourse());

        // Then: Verify that the retrieved course matches the saved course
        assertNotNull(retrievedCourse, "Retrieved course should not be null");
        assertEquals(savedCourse, retrievedCourse, "Retrieved course should match the saved course");
    }
}
