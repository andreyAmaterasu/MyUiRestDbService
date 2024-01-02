package com.example.MyUiRestDbService;

import com.example.MyUiRestDbService.dao.StudentRepository;
import com.example.MyUiRestDbService.entity.Student;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@AllArgsConstructor
public class StudentController {

  private final StudentRepository studentRepository;

  @GetMapping({ "/list", "/" })
  public ModelAndView getAllStudents() {

    ModelAndView modelAndView = new ModelAndView("list-students");
    System.out.println(studentRepository.findAll());
    modelAndView.addObject("students", studentRepository.findAll());
    return modelAndView;
  }

  @GetMapping("/addStudentForm")
  public ModelAndView addStudentForm() {

    ModelAndView modelAndView = new ModelAndView("add-student-form");
    Student student = new Student();
    modelAndView.addObject("student", student);
    return modelAndView;
  }

  @PostMapping("/saveStudent")
  public RedirectView saveStudent(@ModelAttribute Student student) {
log.info("save...");
    studentRepository.save(student);
    return new RedirectView("list");
  }

  @GetMapping("/showUpdateForm")
  public ModelAndView showUpdateForm(@RequestParam Integer studentId) {

    ModelAndView modelAndView = new ModelAndView("add-student-form");
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    Student student = new Student();
    if (optionalStudent.isPresent()) {
      student = optionalStudent.get();
    }
    modelAndView.addObject("student", student);
    return modelAndView;
  }

  @GetMapping("/deleteStudent")
  public RedirectView deleteStudent(@RequestParam Integer studentId) {

    studentRepository.deleteById(studentId);
    return new RedirectView("list");
  }
}
