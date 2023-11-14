package tn.esprit.spring.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
public class Instructor implements Serializable {
	//ahmed was here
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long numInstructor;
	String firstName;
	String lastName;
	LocalDate dateOfHire;
	@OneToMany
	Set<Course> courses;

	}