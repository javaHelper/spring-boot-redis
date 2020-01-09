package com.myexample.repository;

import org.springframework.data.repository.CrudRepository;

import com.myexample.model.Student;

public interface StudentRepository extends CrudRepository<Student, String>{

}
