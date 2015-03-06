package com.joshcummings.resume.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joshcummings.resume.model.Degree;
import com.joshcummings.resume.model.Job;
import com.joshcummings.resume.model.Resume;
import com.joshcummings.resume.model.Skill;
import com.joshcummings.resume.service.ResumeRestService;

@Controller
public class ResumeController {
	@Inject ResumeRestService resumeService;
	
	@RequestMapping("/resume/{id}")
	public ModelAndView getResume(@PathVariable("id") Long id) {
		Resume r = resumeService.getResume(id);
		return new ModelAndView("/resume", "model", r);
		
	}
	
	@RequestMapping("/resume/{id}/degrees")
	public @ResponseBody
	List<Degree> getDegrees(@PathVariable("id") Long id) {
		return resumeService.getDegreesForResume(id);
		
	}
	
	@RequestMapping("/resume/{id}/skills")
	public @ResponseBody
	List<Skill> getSkills(@PathVariable("id") Long id) {
		return resumeService.getSkillsForResume(id);
		
	}
	
	@RequestMapping("/resume/{id}/jobs")
	public @ResponseBody
	List<Job> getJobs(@PathVariable("id") Long id) {
		return resumeService.getJobsForResume(id);
	}
}
