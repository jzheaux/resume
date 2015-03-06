package com.joshcummings.resume.model;

import java.time.LocalDateTime;

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
	@NamedQuery(name="degreesByResume", query="FROM Degree d WHERE d.resume.id = :id"),
})
@Entity
@Table(name="degree")
public class Degree {
	@Id
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ", name="seq")
	@Column
	private Long id;
	
	@Column
	private String institution;
	
	@Column(name="AREA_OF_STUDY")
	private String areaOfStudy;
	
	@Column
	private String degree;
	
	@Column
	private Long graduation;
	
	@ManyToOne
	@JoinColumn(name="RESUME_ID")
	private Resume resume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getAreaOfStudy() {
		return areaOfStudy;
	}

	public void setAreaOfStudy(String areaOfStudy) {
		this.areaOfStudy = areaOfStudy;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public Long getGraduation() {
		return graduation;
	}

	public void setGraduation(Long graduation) {
		this.graduation = graduation;
	}

	@XmlTransient
	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}
}
