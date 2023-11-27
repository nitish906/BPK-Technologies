package com.student.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.exceptions.StudentException;
import com.student.exceptions.SubjectException;
import com.student.models.Student;
import com.student.models.StudentMarks;
import com.student.models.Subject;
import com.student.repository.StudentMarksRepository;
import com.student.repository.StudentRepository;
import com.student.repository.SubjectRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentMarksRepository studentMarksRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> registerStudent(Student student) {
		studentRepository.save(student);
		return studentRepository.findAll();
	}

	@Override
	public void addSubjet(Subject subject) {
		subjectRepository.save(subject);
		
	}

	@Override
	public void addMarks(StudentMarks marks, Long subjectId, Long studentId)throws StudentException, SubjectException {
		
		Optional<Student> optStudent = studentRepository.findById(studentId);
		Optional<Subject> optSubject = subjectRepository.findById(subjectId);
		if (optStudent.isEmpty()) {
			
			throw new StudentException("Invalid studentId "+studentId);
		}
		if (optSubject.isEmpty()) {
			throw new SubjectException("Invalid subjectId "+subjectId);
		}

		
		Student student = optStudent.get();
		Subject subject = optSubject.get();
		
		marks.setStudent(student);
		marks.setSubject(subject);
		studentMarksRepository.save(marks);
		
	}

	@Override
	public List<Student> getAllStudentWith60Percent() {
		
		return studentMarksRepository.findStudentsWithMarksGreaterThan60();
	}

}
