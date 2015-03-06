package com.joshcummings.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@NamedQueries({
	@NamedQuery(name="skillsByResume", query="FROM Skill s WHERE s.resume.id = :id"),
})
@Entity
@Table(name="skill")
public class Skill {
	@Id
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ", name="seq")
	@Column
	private Long id;
	
	@Column
	private String skill;

	@Column	
	private String proficiency;
	
	@Column
	private Double years;
	
	@ManyToOne
	@JoinColumn(name="RESUME_ID")
	private Resume resume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public Double getYears() {
		return years;
	}

	public void setYears(Double years) {
		this.years = years;
	}

	@XmlTransient
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
}
