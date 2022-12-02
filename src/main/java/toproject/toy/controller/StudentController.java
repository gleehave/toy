package toproject.toy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import toproject.toy.bean.Student;

import java.util.ArrayList;
import java.util.List;

@RestController // 알아서 JSON으로 바꿔줌
public class StudentController {

    @GetMapping("/student")
    public Student getStudent() {
        Student student = new Student(1L, "lee", "glee");
        return student; // 여기서 객체를 넘기면 json으로 변환됨
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2L, "kim", "lee"));
        students.add(new Student(3L, "park", "lee"));
        students.add(new Student(4L, "song", "lee"));
        // [{"id":2,"firstName":"kim","lastName":"lee"},{"id":3,"firstName":"park","lastName":"lee"},{"id":4,"firstName":"song","lastName":"lee"}]
        return students;
    }

    @GetMapping("/student/{Id}")
    public Student studentPathVariable(@PathVariable Long Id) {
        return new Student(Id, "leelee", "lee");
    }

    @GetMapping("/student/query") //위의 경로가 같아서 오류 뜸.
    public Student studentRequestVariable(
            @RequestParam Long Id,
            @RequestParam String firstName,
            @RequestParam String lastName) {

        // {"id":1,"firstName":"lee","lastName":"lee"}
        return new Student(Id, firstName, lastName);
    }

    @PostMapping("student/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }

    @PutMapping("student/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return student;
    }
}
