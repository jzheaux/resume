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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@NamedQueries({
	@NamedQuery(name="jobsByResume", query="FROM Job j WHERE j.resume.id = :id ORDER BY j.startDate DESC"),
})
@Entity
@Table(name="job")
public class Job {

	@Id
	@Column
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ", name="seq")
	private Long id;
		
	@Column
	private String company;
	
	@Column
	private String position;
	
	@Column
	private Long startDate;
	
	@Column
	private Long endDate;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true, mappedBy="job")
	private Set<Responsibility> responsibilities = new HashSet<Responsibility>();

	@ManyToOne
	@JoinColumn(name="RESUME_ID")
	private Resume resume;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Set<Responsibility> getResponsibilities() {
		return Collections.unmodifiableSet(responsibilities);
	}

	public void addResponsibilities(Responsibility responsibility) {
		responsibilities.add(responsibility);
		responsibility.setJob(this);
	}

	@XmlTransient
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public void addResponsibilities(String description) {
		Responsibility r = new Responsibility();
		r.setDescription(description);
		addResponsibilities(r);
	}
}
