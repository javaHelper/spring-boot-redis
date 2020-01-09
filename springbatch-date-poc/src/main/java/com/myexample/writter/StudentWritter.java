package com.myexample.writter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.myexample.model.Student;
import com.myexample.repository.StudentRepository;



public class StudentWritter implements ItemWriter<Student>{
	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public void write(List<? extends Student> students) throws Exception {
		studentRepository.saveAll(students);
	}

}
