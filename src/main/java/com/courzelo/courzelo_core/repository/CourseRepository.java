package com.courzelo.courzelo_core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courzelo.courzelo_core.entity.Course;

public interface CourseRepository extends MongoRepository<Course, String>{
	
	Course findCourseById(String idCourse);

}
