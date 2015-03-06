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
        			r.setObjective("My Objective");
        			
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
        			j.setPosition("Faculty Member");
        			
        			Responsibility responsibility = new Responsibility();
        			responsibility.setDescription("Teach");
        			j.addResponsibilities(responsibility);
        			r.addJob(j);
        			
        			Skill s = new Skill();
        			s.setProficiency("Expert");
        			s.setSkill("Java");
        			s.setYears(15d);
        			r.addSkill(s);
        			
        			em.persist(p);
        			em.persist(r);
        			
        		}
            }
        });    
	}
}
