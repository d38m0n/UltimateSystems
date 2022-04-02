package ultimate.systems.pawelBuczek.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ultimate.systems.pawelBuczek.entity.Student;
import ultimate.systems.pawelBuczek.service.StudentService;

import javax.validation.Valid;

@Controller
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/student")
    ResponseEntity<?> createNewStudent(@Valid @RequestBody Student source) {
        studentService.createNewStudent(source);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/student/{idStudent}")
    ResponseEntity<?> deleteStudent(@PathVariable Long idStudent) {
        studentService.deleteStudent(idStudent);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/student")
    ResponseEntity<?> editStudent(@Valid @RequestBody Student source) {
        studentService.editStudent(source);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/student/{id}")
    ResponseEntity<?> findStudentById(@PathVariable Long id) {
        return ResponseEntity
                .ok(studentService.getStudentById(id));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/student/name={name}")
    ResponseEntity<?> findTeacherName(@PathVariable String name) {
        return ResponseEntity
                .ok(studentService.getTeacherByName(name));
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/student/surname={surname}")
    ResponseEntity<?> findTeacherSurname(@PathVariable String surname) {
        return ResponseEntity
                .ok(studentService.getTeacherBySurname(surname));
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/student/teacher-id={idTeacher}")
    ResponseEntity<?> findAllStudentsWithTeacherId(@PathVariable Long idTeacher) {
        return ResponseEntity
                .ok(studentService.findAllStudentsWithTeacherId(idTeacher));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/students",
            params = {"!sort", "!page", "!size"})
    ResponseEntity<?> getAllStudents() {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/students")
    ResponseEntity<?> getAllStudents(Pageable pageable) {
        return ResponseEntity
                .ok(studentService.findAllBySortedTeacher(pageable));
    }


    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/student/{idStudent}/{idTeacher}")
    ResponseEntity<?> addTeacherToStudent(@PathVariable Long idStudent,
                                          @PathVariable Long idTeacher) {
        studentService.addTeacherToList(idStudent, idTeacher);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/student/{idStudent}/{idTeacher}")
    ResponseEntity<?> deleteTeacherWitStudentList(@PathVariable Long idStudent,
                                                  @PathVariable Long idTeacher) {
        studentService.deletedTeacherWithList(idStudent, idTeacher);
        return ResponseEntity
                .ok()
                .build();
    }
}
