package tn.esprit.spring.services;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.*;

import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IInstructorRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InstructorServicesImplTest {

    @Autowired
    private IInstructorServices instructorService;
    @Autowired
    private IInstructorRepository instructorRepository;
    @Autowired
    private ICourseRepository courseRepository;

    private Instructor instructor;

    @BeforeEach
    void setUp() {
        // Create a new instructor with sample data
        instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
    }

    @AfterEach
    void tearDown() {
        // Delete the created instructor from the repository if it exists
        if (instructor != null && instructor.getNumInstructor() != null) {
            instructorRepository.delete(instructor);
        }
    }

//    @Test
//    @Order(1)
//    void shouldAddInstructor() {
//        // When: Add the instructor
//        Instructor savedInstructor = instructorService.addInstructor(instructor);
//
//        // Then: Verify that the added instructor matches the input instructor
//        assertNotNull(savedInstructor, "Added instructor should not be null");
//        assertEquals(instructor, savedInstructor, "Added instructor should match the input instructor");
//    }
    @Test
    @Order(1)
    void shouldAddInstructor() {
        // When: Add the instructor
        Instructor savedInstructor = instructorService.addInstructor(instructor);

        // Then: Verify that the added instructor matches the input instructor
        assertNotNull(savedInstructor, "Added instructor should not be null");
        assertEquals(instructor, savedInstructor, "Added instructor should match the input instructor");

        // Then: Verify that the saved instructor can be retrieved from the repository
        Instructor retrievedInstructor = instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
        assertNotNull(retrievedInstructor, "Retrieved instructor should not be null");
        assertEquals(savedInstructor, retrievedInstructor, "Retrieved instructor should match the saved instructor");
    }

    @Test
    @Order(2)
    void shouldRetrieveInstructor() {
        // Given: Add an instructor to the repository
        Instructor savedInstructor = instructorService.addInstructor(instructor);
        //When: Retrieve the instructor by ID
        Instructor retrievedInstructor = instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
        //Then: Verify that the retrieved instructor matches the saved instructor
        Assertions.assertNotNull(retrievedInstructor, "Retrieved instructor should not be null");
        Assertions.assertEquals(savedInstructor, retrievedInstructor, "Retrieved instructor should match the saved instructor");
    }
//   @Test
//   @Order(2)
//   void shouldRetrieveInstructor() {
//       // Given: Add an instructor to the repository
//       Instructor savedInstructor = instructorService.addInstructor(instructor);
//
//       // Ensure that the instructor entity has a valid numInstructor
//       assertNotNull(savedInstructor.getNumInstructor(), "Instructor should have a valid numInstructor");
//
//       // When: Retrieve the instructor by ID
//       Instructor retrievedInstructor = instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
//
//       // Then: Verify that the retrieved instructor matches the saved instructor
//       assertNotNull(retrievedInstructor, "Retrieved instructor should not be null");
//       assertEquals(savedInstructor, retrievedInstructor, "Retrieved instructor should match the saved instructor");
//   }


//    @Test
//    @Order(3)
//    void shouldUpdateInstructor() {
//        // Given: Add an instructor to the repository
//        Instructor savedInstructor = instructorService.addInstructor(instructor);
//
//        // When: Update the instructor's first name
//        savedInstructor.setFirstName("Alice");
//        Instructor updatedInstructor = instructorService.updateInstructor(savedInstructor);
//
//        // Then: Verify that the updated instructor has the new first name
//        assertNotNull(updatedInstructor, "Updated instructor should not be null");
//        assertEquals("Alice", updatedInstructor.getFirstName(), "Instructor first name should be updated");
//    }
//
//    @Test
//    @Order(4)
//    void shouldAddInstructorAndAssignToCourse() {
//        // Create a new course with sample data
//        Course course = new Course();
//        course.setLevel(1);
//        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
//        course.setSupport(Support.SKI);
//        course.setPrice(100f);
//        course.setTimeSlot(3);
//
//        // Add the course to the repository to obtain a valid numCourse
//        Course savedCourse = courseRepository.save(course);

//        // When: Add the instructor and assign them to the course
//        Instructor savedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, savedCourse.getNumCourse());
//
//        // Then: Verify that the added instructor is assigned to the course
//        assertNotNull(savedInstructor, "Added instructor should not be null");
//        assertNotNull(savedInstructor.getCourses(), "Instructor should have assigned courses");
//        Assertions.assertFalse(savedInstructor.getCourses().isEmpty(), "Instructor should be assigned to a course");
//    }
@Test
@Order(3)
void shouldUpdateInstructor() {
    // Given: Add an instructor to the repository
    Instructor savedInstructor = instructorService.addInstructor(instructor);

    // When: Update the instructor's first name
    savedInstructor.setFirstName("Alice");
    savedInstructor.setLastName("Wonderland");
    Instructor updatedInstructor = instructorService.updateInstructor(savedInstructor);

    // Then: Verify that the updated instructor has the new first name
    assertNotNull(updatedInstructor, "Updated instructor should not be null");
    assertEquals("Alice", updatedInstructor.getFirstName(), "Instructor first name should be updated");

    // Then: Verify that other properties are not affected by the update
    assertEquals("Wonderland", updatedInstructor.getLastName(), "Instructor last name should remain unchanged");
    // ... (add similar assertions for other properties)
}

    @Test
    @Order(4)
    void shouldAddInstructorAndAssignToCourse() {
        // Create a new course with sample data
        Course course = new Course();
        course.setLevel(1);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(100f);
        course.setTimeSlot(3);

        // Add the course to the repository to obtain a valid numCourse
        Course savedCourse = courseRepository.save(course);

        // When: Add the instructor and assign them to the course
        Instructor savedInstructor = instructorService.addInstructorAndAssignToCourse(instructor, savedCourse.getNumCourse());

        // Then: Verify that the added instructor is assigned to the course
        assertNotNull(savedInstructor, "Added instructor should not be null");
        assertNotNull(savedInstructor.getCourses(), "Instructor should have assigned courses");
        assertFalse(savedInstructor.getCourses().isEmpty(), "Instructor should be assigned to a course");

        // Then: Verify that the assigned course is the expected course
        assertEquals(savedCourse.getNumCourse(), savedInstructor.getCourses().iterator().next().getNumCourse(),
                "Instructor should be assigned to the expected course");
    }
}
