package com.joshcummings.resume.service;

import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.joshcummings.resume.model.Degree;
import com.joshcummings.resume.model.Job;
import com.joshcummings.resume.model.Resume;
import com.joshcummings.resume.model.Skill;

@Path("/resume")
public interface ResumeRestService {
	@GET
	@Produces("application/vnd.joshcummings.com-v1+json")
	@Path("/{id}")
	Resume getResume(@PathParam("id") Long id);

	@GET
	@Produces("application/vnd.joshcummings.com-v1+json")
	@Path("/{id}/degrees")
	List<Degree> getDegreesForResume(@PathParam("id") Long id);
	
	@GET
	@Produces("application/vnd.joshcummings.com-v1+json")
	@Path("/{id}/skills")	
	List<Skill> getSkillsForResume(@PathParam("id") Long id);
	
	@GET
	@Produces("application/vnd.joshcummings.com-v1+json")
	@Path("/{id}/jobs")	
	List<Job> getJobsForResume(@PathParam("id") Long id);
}
