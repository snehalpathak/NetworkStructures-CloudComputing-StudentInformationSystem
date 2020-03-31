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
import com.cyse6225.spring2020.courseservice.datamodel.Program;
import com.cyse6225.spring2020.courseservice.service.ProgramService;

@Path("programs")
public class ProgramResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms() {
		List<Program> list = ProgramService.getInstance().getAllPrograms();
		return list;
	}
	
	@GET
	@Path("{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgrambyId(@PathParam("programId") String programId) {
		return ProgramService.getInstance().getProgrambyId(programId);
	}
	
	//get all courses for program
	@GET
	@Path("{programId}/course")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCoursesforProgram(@PathParam("programId") String progId){
		return ProgramService.getInstance().getCourseForProgram(progId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program addProgram(Program prog) {
		ProgramService.getInstance().addProgram(prog.getProgramId(), prog.getProgramName(), prog.getCourseId());
		return prog;
	}
	
	@DELETE
	@Path("{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program deleteProgram(@PathParam("programId") String progId) {
		return ProgramService.getInstance().deleteProgram(progId);
	}
	
	@PUT
	@Path("{programId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(@PathParam("programId") String progId, Program prog) {
		return ProgramService.getInstance().updateProgram(progId, prog);
	}
}
