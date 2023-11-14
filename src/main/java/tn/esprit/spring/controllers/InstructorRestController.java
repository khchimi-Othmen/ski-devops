package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Dto.UpdatedInstructorDto;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;

import java.util.List;

@Tag(name = "\uD83D\uDC69\u200D\uD83C\uDFEB Instructor Management")
@RestController
@RequestMapping("/instructor")
@RequiredArgsConstructor
public class InstructorRestController {

    private final IInstructorServices instructorServices;
    private Instructor instructor;

    @Operation(description = "Add Instructor")
    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor){
        return  instructorServices.addInstructor(instructor);
    }
    @Operation(description = "Add Instructor and Assign To Course")
    @PutMapping("/addAndAssignToCourse/{numCourse}")
    public Instructor addAndAssignToInstructor(@RequestBody Instructor instructor, @PathVariable("numCourse")Long numCourse){
        return  instructorServices.addInstructorAndAssignToCourse(instructor,numCourse);
    }
    @Operation(description = "Retrieve all Instructors")
    @GetMapping("/all")
    public List<Instructor> getAllInstructors(){
        return instructorServices.retrieveAllInstructors();
    }

@Operation(description = "Update Instructor")
@PutMapping("/update")
public Instructor updateInstructor(@RequestBody UpdatedInstructorDto updatedInstructorDto) {
    Instructor updatedInstructor = mapDtoToInstructor(updatedInstructorDto);
    this.instructor = updatedInstructor;
    return instructorServices.updateInstructor(updatedInstructor);
}
    // Mapping method to convert DTO to internal model
    private Instructor mapDtoToInstructor(UpdatedInstructorDto updatedInstructorDto) {
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(updatedInstructorDto, instructor);
        // If there are additional fields not covered by copyProperties, set them individually
        // Example: instructor.setOtherField(updatedInstructorDto.getOtherField());
        return instructor;
    }


    @Operation(description = "Retrieve Instructor by Id")
    @GetMapping("/get/{id-instructor}")
    public Instructor getById(@PathVariable("id-instructor") Long numInstructor){
        return instructorServices.retrieveInstructor(numInstructor);
    }

}
