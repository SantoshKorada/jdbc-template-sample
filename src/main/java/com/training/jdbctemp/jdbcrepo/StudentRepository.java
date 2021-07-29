package com.training.jdbctemp.jdbcrepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.training.jdbctemp.modal.StudentModal;

@Repository
public class StudentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public StudentModal insertStudent(StudentModal studentModal) {
		String insert = "insert into training.student values(?,?, ?)";
		int i = jdbcTemplate.update(insert, studentModal.getUser_id(), studentModal.getUsername(),
				studentModal.getEmail());
		if (i >= 1)
			return studentModal;
		else
			return null;
	}

	public List<StudentModal> studentsList() {
		String sql = "select * from training.student";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(StudentModal.class));
	}

	public StudentModal getStudentById(int user_id) {
		String sql = "select * from training.student where user_id = ?";
		StudentModal student = jdbcTemplate.queryForObject(sql, new Object[] { user_id },
				new BeanPropertyRowMapper<>(StudentModal.class));
		return student;
	}

	public String updateStudentEmailId(int id, String emailId) {
		String sql = "update training.student set email =? where user_id =?";
		int i = jdbcTemplate.update(sql, emailId, id);
		if (i >= 1)
			return "updated";
		else
			return "not updated";
	}
}
