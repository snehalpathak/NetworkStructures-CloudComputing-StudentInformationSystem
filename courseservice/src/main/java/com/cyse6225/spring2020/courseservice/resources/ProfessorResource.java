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
import com.cyse6225.spring2020.courseservice.datamodel.Professor;
import com.cyse6225.spring2020.courseservice.service.ProfessorService;

// .. /webapi/myresource
@Path("professors")
public class ProfessorResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessors() {
		return ProfessorService.getInstance().getAllProfessors();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor addProfessor(Professor prof) {
		ProfessorService.getInstance().addProfessor(prof.getFirstName(), prof.getLastName(), prof.getDepartment(),
				prof.getJoiningDate());

		return prof;
	}

	@GET
	@Path("{department}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getProfessorsByDeparment(@PathParam("department") String department) {
		if (department == null) {
			return ProfessorService.getInstance().getAllProfessors();
		}
		return ProfessorService.getInstance().getProfessorsByDepartment(department);
	}
	
	@GET
	@Path("id/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor getProfessor(@PathParam("professorId") String profId) {
		return ProfessorService.getInstance().getProfessor(profId);
	}

	@DELETE
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor deleteProfessor(@PathParam("professorId") String profId) {
		return ProfessorService.getInstance().deleteProfessor(profId);
	}

	@PUT
	@Path("{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(@PathParam("professorId") String profId, Professor prof) throws Exception {
		Professor newProf = ProfessorService.getInstance().updateProfessorInformation(profId, prof);
		
		return newProf;
	}

}
