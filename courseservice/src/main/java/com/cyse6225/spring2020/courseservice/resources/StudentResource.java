package com.cyse6225.spring2020.courseservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cyse6225.spring2020.courseservice.datamodel.Course;
import com.cyse6225.spring2020.courseservice.datamodel.Student;
import com.cyse6225.spring2020.courseservice.service.CourseService;
import com.cyse6225.spring2020.courseservice.service.StudentService;

@Path("students")
public class StudentResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		return StudentService.getInstance().getAllStudents();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student addStudent(Student student) {
		System.out.println(student);
		StudentService.getInstance().addStudent(student.getStudentId(), student.getName(), student.getEmail(), student.getProgramId(),
				student.getCourseId());
		return student;
	}

	// get student by id
	@GET
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentbyId(@PathParam("studentId") String studentId) {
		return StudentService.getInstance().getStudentById(studentId);
	}

	// get student by program
	@GET
	@Path("program/{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentbyProgram(@PathParam("programId") String programId) {

		if (programId == null) {
			return StudentService.getInstance().getAllStudents();
		}
		return StudentService.getInstance().getStudentbyProgram(programId);
	}

	// get course list for student
	@GET
	@Path("{studentId}/course")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourseListForStudent(@PathParam("studentId") String studentId) {
		List<String> list = StudentService.getInstance().getCourseListForStudent(studentId);
		return CourseService.getInstance().getCoursesByCourseId(list);
	}

	// delete student
	@DELETE
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student deleteProfessor(@PathParam("studentId") String studentId) {
		return StudentService.getInstance().deleteStudent(studentId);
	}

	// update student
	@PUT
	@Path("{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateProfessor(@PathParam("studentId") String studentId, Student student) {
		return StudentService.getInstance().updateStudent(studentId, student);
	}
}
