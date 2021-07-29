package com.training.jdbctemp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.jdbctemp.jdbcrepo.StudentRepository;
import com.training.jdbctemp.modal.StudentModal;

@RestController
@RequestMapping("sms")
public class SmsController {

	@Autowired
	StudentRepository jdbcRepo;

	@GetMapping("test")
	public String testApi() {
		return "sms api jdbc template is working";
	}

	@PostMapping("/register")
	public StudentModal registerStudent(@RequestBody StudentModal studentModal) {
		System.out.println(studentModal.toString());
		return jdbcRepo.insertStudent(studentModal);
	}

	@GetMapping("/list")
	public List<StudentModal> studentsList() {
		return jdbcRepo.studentsList();
	}

	@GetMapping("/list/{user_id}")
	public StudentModal getStudentData(@PathVariable("user_id") int user_id) {
		return jdbcRepo.getStudentById(user_id);
	}

	@GetMapping("/update")
	public String updateStudentEmailID(@RequestParam("id") int id, @RequestParam("email") String email) {
		System.out.println("id ::" + id);
		System.out.println("email ::" + email);
		return jdbcRepo.updateStudentEmailId(id,email);
	}
}
