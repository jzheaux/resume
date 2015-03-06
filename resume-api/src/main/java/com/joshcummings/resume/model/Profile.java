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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "profile")
public class Profile {
	@Id
	@Column
	@GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "SEQ", name = "seq")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column
	private String address;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String email;

	@Column
	private String profession;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "TWITTER_HANDLE")
	private String twitterHandle;

	@Column(name = "LINKEDIN_HANDLE")
	private String linkedinHandle;

	@Column(name = "FACEBOOK_HANDLE")
	private String facebookHandle;

	@Column(name = "GITHUB_HANDLE")
	private String githubHandle;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "profile")
	private Set<Resume> resumes = new HashSet<Resume>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	@XmlTransient
	public String getEmailRotated() {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < email.length(); i++ ) {
			char ch = email.charAt(i);
			if ( ch >= 48 && ch <= 97 + 25 ) {
				sb.append((char)(( ch - 47 ) % 75) + 48);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@XmlTransient
	public String getPhoneRotated() {
		StringBuilder sb = new StringBuilder();
		for ( int i = 0; i < phoneNumber.length(); i++ ) {
			char ch = phoneNumber.charAt(i);
			if ( ch >= 48 && ch <= 97 + 25 ) {
				sb.append((char)(( ch - 47 ) % 75) + 48);
			} else {
				sb.append(ch);
			}
		}
		return sb.toString();
	}
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}

	public String getLinkedinHandle() {
		return linkedinHandle;
	}

	public void setLinkedinHandle(String linkedinHandle) {
		this.linkedinHandle = linkedinHandle;
	}

	public String getFacebookHandle() {
		return facebookHandle;
	}

	public void setFacebookHandle(String facebookHandle) {
		this.facebookHandle = facebookHandle;
	}

	public String getGithubHandle() {
		return githubHandle;
	}

	public void setGithubHandle(String githubHandle) {
		this.githubHandle = githubHandle;
	}

	@XmlTransient
	public Set<Resume> getResumes() {
		return Collections.unmodifiableSet(resumes);
	}

	public void addResume(Resume resume) {
		resumes.add(resume);
	}
}
