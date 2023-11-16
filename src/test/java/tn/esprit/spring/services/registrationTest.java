package tn.esprit.spring.services;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Registration;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
public class registrationTest {
    IRegistrationServices ir;
        @Mock
        private IRegistrationRepository registrationRepository;

        @Mock
        private ISkierRepository skierRepository;

        @Mock
        private ICourseRepository courseRepository;

        @InjectMocks
        private RegistrationServicesImpl registrationServices;

    @Test
    void testAddRegistrationAndAssignToSkier() {
        // Mock data
        Registration registration = new Registration(/* initialize Registration */);
        Long numSkier = 1L;
        Skier skier = new Skier(/* initialize Skier */);

        // Mock the behavior of the skierRepository.findById method
        when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));

        // Mock the behavior of the registrationRepository.save method
        when(registrationRepository.save(any(Registration.class))).thenReturn(registration);

        // Test the method
        Registration result = ir.addRegistrationAndAssignToSkier(registration, numSkier);

        // Verify
        assertEquals(registration, result);
    }


    }


