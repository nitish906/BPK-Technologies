package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.student.exceptions.StudentException;
import com.student.exceptions.SubjectException;
import com.student.models.Student;
import com.student.models.StudentMarks;
import com.student.models.Subject;
import com.student.services.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student/add")
	public ResponseEntity<List<Student>> saveStudent(@RequestBody Student student){
		
		List<Student> studnets =  studentService.registerStudent(student);
		return new ResponseEntity<List<Student>>(studnets, HttpStatus.CREATED);
	}
	
	@PostMapping("/subject/add")
	public void saveSubject(@RequestBody Subject subect) {
		
		studentService.addSubjet(subect);
		
	}
	
	@PostMapping("/marks/add/{studentId}/{subjectId}")
	public void addMarks(@RequestBody StudentMarks marks, @PathVariable Long studentId, @PathVariable Long subjectId)throws StudentException, SubjectException  {
		
		studentService.addMarks(marks, subjectId, studentId);
	}
	
	@GetMapping("/students/60")
	public ResponseEntity<List<Student>> getSetudetsHavingMoreThan60Percent(){
		
		List<Student> students = studentService.getAllStudentWith60Percent();
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	

}
