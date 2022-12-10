package com.glearning.hibernate.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * This class has the representation in the database
 * Hibernate mandates that the persistence class should have a default no argument constructor
 * There should not be any business logic in the entity class
 * 
 * This class is also referred to as 
 *  - Entity
 *  - POJO - Plain Old Java Object
 *  - DTO - Data transfer object
 *
 */

@Entity
@Table(name = "teachers")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="teacher_name", nullable = false)
	private String name;
	
	@Column(name="teacher_salary", nullable = false)
	private double salary;
	
	@Column(name="teacher_email", nullable = false)
	private String emailAddress;
	
	@OneToOne(mappedBy = "teacher", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private TeacherDetails teacherDetails;
	
	@OneToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
	private List<Subject> subjects;
	
	Teacher(){
		
	}
	
	public Teacher(String name, double salary, String email) {
		this.name = name;
		this.salary = salary;
		this.emailAddress = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	
	
	public TeacherDetails getTeacherDetails() {
		return teacherDetails;
	}

	public void setTeacherDetails(TeacherDetails teacherDetails) {
		this.teacherDetails = teacherDetails;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	//scaffolding code
	//setting both sides of the relationship
	public void addTeacherDetails(TeacherDetails teacherDetails) {
		//set both sides of the relationship
		this.teacherDetails = teacherDetails;
		teacherDetails.setTeacher(this);
	}
	
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
		subject.setTeacher(this);
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", salary=" + salary + ", emailAddress=" + emailAddress + "]";
	}

}
