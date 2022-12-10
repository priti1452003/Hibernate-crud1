package com.glearning.hibernate.crud.client;

import java.time.LocalDate;
import java.util.List;

import com.glearning.hibernate.crud.model.Teacher;
import com.glearning.hibernate.crud.model.TeacherDetails;
import com.glearning.hibernate.crud.service.TeacherService;

public class TeacherClient {
	
	private final TeacherService teacherService;
	
	public TeacherClient() {
		this.teacherService = new TeacherService();
	}
		
	public static void main(String[] args) {
		TeacherClient client = new TeacherClient();
		client.insertTeacher();
		//fetch all the teachers
		client.fetchTeachers();
		
		//fetch teacher by teacher id
		client.fetchTeacherByTeacherId(1);
		
		//delete the teacher record from the db based on id
		client.deleteTeacherByTeacherId(1);
	}

	private void deleteTeacherByTeacherId(long id) {
		this.teacherService.deleteTeacherRecordByTeacherId(id);
		
	}

	private void fetchTeacherByTeacherId(long teacherId) {
		Teacher teacher = this.teacherService.findTeacherByTeacherId(teacherId);
		
		System.out.println("Fetched the teacher by teacher id");
		System.out.println(teacher);
		
	}

	private void fetchTeachers() {
		List<Teacher> teachers = this.teacherService.findAllTeachers();
		for(Teacher teacher: teachers) {
			System.out.println(teacher);
		}
		
	}

	private void insertTeacher() {
		Teacher bhuvan = new Teacher("Bhuvan", 150000, "bhuvan@gmail.com");
		TeacherDetails teacherDetails = new TeacherDetails("678-987-8767", LocalDate.now(), "Bangalore");
		Teacher sunil = new Teacher("Sunil", 250000, "sunil@gmail.com");
		TeacherDetails sunilTeacherDetails = new TeacherDetails("888-898-8777", LocalDate.now(), "Chennai");
		teacherService.insertTeacherRecord(bhuvan, teacherDetails);
		teacherService.insertTeacherRecord(sunil, sunilTeacherDetails);
	}
	
	

}
