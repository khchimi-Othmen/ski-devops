package tn.esprit.spring.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.entities.Course;

import java.time.LocalDate;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
public class UpdatedInstructorDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfHire;
    private Set<Course> courses;
}