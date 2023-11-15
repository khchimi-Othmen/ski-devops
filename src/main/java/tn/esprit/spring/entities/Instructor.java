package tn.esprit.spring.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	@OneToMany
	Set<Course> courses;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Instructor instructor = (Instructor) o;

		if (!Objects.equals(numInstructor, instructor.numInstructor)) return false;
		if (!Objects.equals(firstName, instructor.firstName)) return false;
		if (!Objects.equals(lastName, instructor.lastName)) return false;
		if (!Objects.equals(dateOfHire, instructor.dateOfHire)) return false;
		// Add comparison for other attributes as needed
		// ...

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numInstructor, firstName, lastName, dateOfHire);
	}


}