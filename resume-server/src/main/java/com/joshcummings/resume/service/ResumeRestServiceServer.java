package com.joshcummings.resume.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.joshcummings.resume.model.Degree;
import com.joshcummings.resume.model.Job;
import com.joshcummings.resume.model.Profile;
import com.joshcummings.resume.model.Responsibility;
import com.joshcummings.resume.model.Resume;
import com.joshcummings.resume.model.ResumeNotFoundException;
import com.joshcummings.resume.model.Skill;


@Service
public class ResumeRestServiceServer implements ResumeRestService {
	@PersistenceContext EntityManager em;
	
	@Autowired
	@Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;
	
	public Resume getResume(Long id) {
		try {
			return em.find(Resume.class, id);
		} catch ( NoResultException e ) {
			throw new ResumeNotFoundException("Couldn't find resume #" + id, e);
		}
	}
	
	public List<Degree> getDegreesForResume(Long id) {
		TypedQuery<Degree> d = em.createNamedQuery("degreesByResume", Degree.class);
		d.setParameter("id", id);
		return d.getResultList();
	}
	
	public List<Skill> getSkillsForResume(Long id) {
		TypedQuery<Skill> d = em.createNamedQuery("skillsByResume", Skill.class);
		d.setParameter("id", id);
		return d.getResultList();
	}
	
	public List<Job> getJobsForResume(Long id) {
		TypedQuery<Job> d = em.createNamedQuery("jobsByResume", Job.class);
		d.setParameter("id", id);
		return d.getResultList();
	}
	
	@PostConstruct
	public void bootstrapResume() {
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
            	Resume r = em.find(Resume.class, 100L);
        		if ( r == null ) {
        			r = new Resume();
        			r.setObjective("Apply my extensive experience in software engineering in general and Java application development specifically to the role of part-time technical consultant.");
        			
        			Profile p = new Profile();
        			p.setAddress("5498 W Sondrio Street");
        			p.setCity("Herriman");
        			p.setEmail("josh.cummings@gmail.com");
        			p.setProfession("Principal Software Engineer");
        			p.setPhoneNumber("801-556-2751");
        			p.setState("UT");
        			p.setLinkedinHandle("innovativejavaarchitect");
        			p.setFacebookHandle("jzheaux");
        			p.setGithubHandle("jzheaux");
        			p.setTwitterHandle("jzheaux");
        			p.setBlog("http://tech.joshuacummings.com");
        			p.setLastName("Cummings");
        			p.setFirstName("Josh");
        			r.setProfile(p);
        			
        			Degree d = new Degree();
        			d.setAreaOfStudy("Computer Science");
        			d.setDegree("Bachelors");
        			
        			Calendar cal = Calendar.getInstance();
        			cal.set(Calendar.YEAR, 2003);
        			cal.set(Calendar.MONTH, 3);
        			cal.set(Calendar.DATE, 30);
        			d.setGraduation(cal.getTimeInMillis());
        			d.setInstitution("University of Utah");
        			r.addDegree(d);
        			
        			d = new Degree();
        			d.setAreaOfStudy("Mathematics");
        			d.setDegree("Bachelors");
        			d.setGraduation(cal.getTimeInMillis());
        			d.setInstitution("University of Utah");
        			r.addDegree(d);
        			
        			Job j = new Job();
        			j.setCompany("Neumont University");
        			cal.set(Calendar.YEAR, 2013);
        			cal.set(Calendar.MONTH, 8);
        			cal.set(Calendar.DATE, 23);
        			j.setStartDate(cal.getTimeInMillis());
        			cal = Calendar.getInstance();
        			j.setEndDate(cal.getTimeInMillis());
        			j.setPosition("Faculty Member");
        			
        			j.addResponsibilities("Consult with other teachers with regard to the seven courses Neumont offers that are based in Java. Ensure that faculty Java coding practices are current and up-to-date.");
        			j.addResponsibilities("Develop curriculum for our Java-based courses, including labs, exams, assignments, and other assessments.");
        			j.addResponsibilities("In classes of 30-40 students, teach students Java at a collegiate level from novice to expert. Specifically, teach those who have no previous coding experience how to code and, via the course model, elevate students to a level of expertise that easily surpasses typical college graduates.");
        			j.addResponsibilities("Provide instruction, guidance, and management on project courses where students design an entire software product from beginning to end.");  
        			r.addJob(j);
        			
        			j = new Job();
        			j.setCompany("Deem/Rearden Commerce");
        			cal.set(Calendar.YEAR, 2010);
        			cal.set(Calendar.MONTH, 11);
        			cal.set(Calendar.DATE, 30);
        			j.setStartDate(cal.getTimeInMillis());
        			
        			cal.set(Calendar.YEAR, 2014);
        			cal.set(Calendar.MONTH, 1);
        			cal.set(Calendar.DATE, 28);
        			j.setEndDate(cal.getTimeInMillis());
        			j.setPosition("Principle Software Engineer");
        			
        			j.addResponsibilities("Architect, Design, and Develop mission-critical web applications and platform components for the organization’s authentication and security needs; interface  with both internal development teams and external third-parties for SAML and other SSO requirements; maintain excellent standards of documentation and support to other devs.");
        			j.addResponsibilities("Lead a team of six engineers in the process, performing code reviews, pair programming, and coordinating with other teams to deliver a solid product.");
        			j.addResponsibilities("Sit on Architectural Board that articulates and evangelizes good coding standards and practices."); 
        			r.addJob(j);
        			
        			j = new Job();
        			j.setCompany("LDS Church");
        			cal.set(Calendar.YEAR, 2008);
        			cal.set(Calendar.MONTH, 8);
        			cal.set(Calendar.DATE, 1);
        			j.setStartDate(cal.getTimeInMillis());
        			
        			cal.set(Calendar.YEAR, 2010);
        			cal.set(Calendar.MONTH, 11);
        			cal.set(Calendar.DATE, 31);
        			j.setEndDate(cal.getTimeInMillis());
        			j.setPosition("Senior Software Engineer");
        			
        			j.addResponsibilities("Consult dozens of small projects (about 300 engineers across the org) on specifications, design decisions, and build vs. buy.");
        			j.addResponsibilities("Develop key application framework components for use by dozens of small project teams, particularly in the areas of Web Services, SSO, and static analysis, maintaining a consistent release cycle, a comprehensive corpus of documentation, and clear cohesion for each component.");
        			j.addResponsibilities("Train and support dozens of small projects on use of in-house modules, third-party technologies, and the Java platform in general. This included helping teams track down bugs, code consultation, and holding formal presentations and workshops."); 
        			r.addJob(j);
        			
        			j = new Job();
        			j.setCompany("iCentris");
        			cal.set(Calendar.YEAR, 2003);
        			cal.set(Calendar.MONTH, 5);
        			cal.set(Calendar.DATE, 1);
        			j.setStartDate(cal.getTimeInMillis());
        			
        			cal.set(Calendar.YEAR, 2008);
        			cal.set(Calendar.MONTH, 8);
        			cal.set(Calendar.DATE, 30);
        			j.setEndDate(cal.getTimeInMillis());
        			j.setPosition("Senior Software Engineer");
        			
        			j.addResponsibilities("Research and develop application framework components for use by the rest ofthe development organization (30-40 engineers), particularly for the MVC layer in function and ecommerce in industry.");
        			j.addResponsibilities("Train and support the development organization on in-house modules, third-party technologies, and the Java platform in general. This included assisting in bug hunts as well as code consultation.");
        			j.addResponsibilities("Lead the Production Support team of eight engineers, performing code reviews, pair programming, and developing software for tenured clients.");
        			j.addResponsibilities("Design and Develop fast and fault-tolerant ecommerce software for the Direct Sales industry."); 
        			r.addJob(j);
        			
        			r.addSkill("Expert", "Java", 15d);
        			r.addSkill("Beginner", "Groovy", 1d);
        			r.addSkill("Beginner", "Scala", 1d);
        			r.addSkill("Intermediate", "C", 2d);
        			r.addSkill("Academic", "C++", .5);
        			r.addSkill("Intermediate", "PHP", 5d);
        			r.addSkill("Advanced", "Javascript", 7d);
        			r.addSkill("Advanced", "Spring", 7d);
        			r.addSkill("Intermediate", "Spring Security", 3d);
        			r.addSkill("Intermediate", "Hibernate", 4d);
        			r.addSkill("Intermediate", "Tomcat", 10d);
        			r.addSkill("Intermediate", "JIRA", 7d);
        			r.addSkill("Intermediate", "Perforce", 3d);
        			r.addSkill("Intermediate", "SVN", 3d);
        			r.addSkill("Beginner", "Git", 1.5d);
        			r.addSkill("Advanced", "Eclipse", 10d);
        			r.addSkill("Advanced", "Maven", 7d);
        			r.addSkill("Beginner", "Ant", 1d);
        			r.addSkill("Beginner", "Ivy", 0.5d);
        			r.addSkill("Beginner", "Gradle", 0.5d);
        			r.addSkill("Intermediate", "ReST", 5d);
        			r.addSkill("Beginner", "SOAP", 2d);
        			r.addSkill("Intermediate", "Web App Security", 2d);
        			
        			em.persist(p);
        			em.persist(r);
        			
        		}
            }
        });    
	}
}
