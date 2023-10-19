package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.repositories.IRegistrationRepository;
import tn.esprit.spring.repositories.ISkierRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class RegistrationServicesImpl implements  IRegistrationServices{

    private IRegistrationRepository registrationRepository;
    private ISkierRepository skierRepository;
    private ICourseRepository courseRepository;


    @Override
    public Registration addRegistrationAndAssignToSkier(Registration registration, Long numSkier) {
        Skier skier = skierRepository.findById(numSkier).orElse(null);
        registration.setSkier(skier);
        return registrationRepository.save(registration);
    }

    @Override
    public Registration assignRegistrationToCourse(Long numRegistration, Long numCourse) {
        Registration registration = registrationRepository.findById(numRegistration).orElse(null);
        Course course = courseRepository.findById(numCourse).orElse(null);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Transactional
    @Override
    public Registration addRegistrationAndAssignToSkierAndCourse(Registration registration, Long numSkieur, Long numCours) {
        Skier skier = skierRepository.findById(numSkieur).orElse(null);
        Course course = courseRepository.findById(numCours).orElse(null);

        if (skier == null || course == null) {
            return null;
        }

        if (isAlreadyRegistered(registration.getNumWeek(), skier.getNumSkier(), course.getNumCourse())) {
            logRegistrationError(registration.getNumWeek());
            return null;
        }

        int ageSkieur = calculateAge(skier.getDateOfBirth());

        switch (course.getTypeCourse()) {
            case INDIVIDUAL:
                return assignRegistration(registration, skier, course);

            case COLLECTIVE_CHILDREN:
                if (isAgeValidForChild(ageSkieur) && isCourseAvailable(course, registration.getNumWeek())) {
                    return assignRegistration(registration, skier, course);
                } else {
                    logRegistrationError(ageSkieur < 16);
                    return null;
                }

            default:
                if (isAgeValidForAdult(ageSkieur) && isCourseAvailable(course, registration.getNumWeek())) {
                    return assignRegistration(registration, skier, course);
                } else {
                    logRegistrationError(ageSkieur >= 16);
                    return null;
                }
        }
    }

    private boolean isAlreadyRegistered(int numWeek, Long numSkier, Long numCourse) {
        return registrationRepository.countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(numWeek, numSkier, numCourse) >= 1;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    private boolean isAgeValidForChild(int age) {
        return age < 16;
    }

    private boolean isAgeValidForAdult(int age) {
        return age >= 16;
    }

    private boolean isCourseAvailable(Course course, int numWeek) {
        return registrationRepository.countByCourseAndNumWeek(course, numWeek) < 6;
    }

    private void logRegistrationError(boolean isChild) {
        log.info("Sorry, your age doesn't allow you to register for this " +
                "course! Try to Register to a " + (isChild ? "Collective Adult" : "Collective Child") + " Course...");
    }

    private void logRegistrationError(int numWeek) {
        log.info("Sorry, you're already registered for this course of the week: " + numWeek);
    }



    private Registration assignRegistration (Registration registration, Skier skier, Course course){
        registration.setSkier(skier);
        registration.setCourse(course);
        return registrationRepository.save(registration);
    }

    @Override
    public List<Integer> numWeeksCourseOfInstructorBySupport(Long numInstructor, Support support) {
        return registrationRepository.numWeeksCourseOfInstructorBySupport(numInstructor, support);
    }

}
