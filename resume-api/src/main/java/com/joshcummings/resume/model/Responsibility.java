package com.joshcummings.resume.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="responsibility")
public class Responsibility {

	@Id
	@Column
	@GeneratedValue(generator="seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName="SEQ", name="seq")
	private Long id;
		
	@Column
	private String description;
	
	@ManyToOne
	@JoinColumn(name="JOB_ID")
	private Job job;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
}
