package ultimate.systems.pawelBuczek.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ultimate.systems.pawelBuczek.entity.Teacher;
import ultimate.systems.pawelBuczek.service.TeacherService;

import javax.validation.Valid;

@Controller
public class TeacherController {

    private TeacherService teacherService;


    public TeacherController(
            TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/teacher/{idTeacher}/{idStudent}")
    ResponseEntity<?> addStudentToTeacher(@PathVariable Long idTeacher,
                                          @PathVariable Long idStudent) {
        teacherService.addStudentToList(idStudent, idTeacher);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/teacher")
    ResponseEntity<?> createNewTeacher(@Valid @RequestBody Teacher source) {
        teacherService.createNewTeacher(source);
        return ResponseEntity
                .ok()
                .build();

    }
    @RequestMapping(
            method = RequestMethod.PATCH,
            path = "/teacher")
    ResponseEntity<?> editStudent(@Valid @RequestBody Teacher source) {
        teacherService.editStudent(source);
        return ResponseEntity
                .ok()
                .build();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teacher/{idTeacher}")
    ResponseEntity<?> findTeacherById(@PathVariable Long idTeacher) {
        return ResponseEntity
                .ok(teacherService.getTeacherById(idTeacher));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teacher/name={name}")
    ResponseEntity<?> findTeacherName(@PathVariable String name) {
        return ResponseEntity
                .ok(teacherService.getTeacherByName(name));
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teacher/surname={surname}")
    ResponseEntity<?> findTeacherSurname(@PathVariable String surname) {
        return ResponseEntity
                .ok(teacherService.getTeacherBySurname(surname));
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/teacher/{idTeacher}")
    ResponseEntity<?> deleteTeacher(@PathVariable Long idTeacher) {
        teacherService.deleteTeacher(idTeacher);
        return ResponseEntity
                .ok()
                .build();
    }
    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teacher/student-id={idStudent}")
    ResponseEntity<?> findAllTeachersWithStudentId(@PathVariable Long idStudent) {
        return ResponseEntity
                .ok(teacherService.findAllTeachersWithStudentId(idStudent));
    }
//

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teachers",
            params = {"!sort", "!page", "!size"})
    ResponseEntity<?> getAllTeachers() {
        return ResponseEntity
                .ok(teacherService.getAllTeachersModel());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/teachers")
    ResponseEntity<?> getAllTeachers(Pageable pageable) {
        return ResponseEntity
                .ok(teacherService.findAllBySortedTeacher(pageable));
    }


    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/teacher/{idTeacher}/{idStudent}")
    ResponseEntity<?> deleteStudentWithTeacherList(@PathVariable Long idTeacher,
                                                   @PathVariable Long idStudent) {
        teacherService.deleteStudentToList(idStudent, idTeacher);
        return ResponseEntity
                .ok()
                .build();
    }
}
