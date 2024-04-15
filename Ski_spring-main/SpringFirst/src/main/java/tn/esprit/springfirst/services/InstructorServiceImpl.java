package tn.esprit.springfirst.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.springfirst.entities.Course;
import tn.esprit.springfirst.entities.Instructor;
import tn.esprit.springfirst.repositories.CourseRepository;
import tn.esprit.springfirst.repositories.InstructorRepository;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService{
    InstructorRepository instructorRepository;
    CourseRepository courseRepository;
    @Override
    public Instructor addInstructor(Instructor i) {
        return instructorRepository.save(i);
    }
    @Override
    public Instructor updateInstructor(Instructor i) {
        return instructorRepository.save(i);
    }
    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }
    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id).get();//dima naamlou .get()
    }
    @Override
    public void removeInstructor(Long id) {
        instructorRepository.deleteById(id);
    }

    Instructor addInstructorAndAssignToCourse(Instructor instructor, Integer numCourse){
        Course course=courseRepository.findByNumCourse(numCourse);
        course.setInstructor(instructor);
        Set<Course> courseSet=instructor.getCourseSet();
        courseSet.add(course);
        instructor.setCourseSet(courseSet);

        return instructor;
    }
}