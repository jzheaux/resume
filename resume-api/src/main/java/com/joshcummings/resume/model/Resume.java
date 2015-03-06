package com.joshcummings.resume.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="resume")
public class Resume {
	@Id
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ", name="seq")
	@Column
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PROFILE_ID")
	private Profile profile;
	
	@Column
	private String objective;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="resume")
	private Set<Job> history = new HashSet<Job>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="resume")
	private Set<Skill> skills = new HashSet<Skill>();
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="resume")
	private Set<Degree> degrees = new HashSet<Degree>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
		profile.addResume(this);
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	@XmlTransient
	public Set<Job> getHistory() {
		return Collections.unmodifiableSet(history);
	}

	public void addJob(Job job) {
		history.add(job);
		job.setResume(this);
	}

	@XmlTransient
	public Set<Skill> getSkills() {
		return Collections.unmodifiableSet(skills);
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
		skill.setResume(this);
	}

	@XmlTransient
	public Set<Degree> getDegrees() {
		return Collections.unmodifiableSet(degrees);
	}

	public void addDegree(Degree degree) {
		degrees.add(degree);
		degree.setResume(this);
	}

	public void addSkill(String proficiency, String skill, double years) {
		Skill s = new Skill();
		s.setProficiency(proficiency);
		s.setSkill(skill);
		s.setYears(years);
		addSkill(s);
	}
}
