package com.example.MyUiRestDbService.dao;

import com.example.MyUiRestDbService.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
