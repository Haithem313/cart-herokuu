package com.courzelo.courzelo_core.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.courzelo.courzelo_core.entity.MyLearning;
import com.courzelo.courzelo_core.entity.User;

public interface MyLearningRepository extends MongoRepository<MyLearning, Long>{
	
	MyLearning findMyLearningByUser(User user);
	
	boolean existsByUser(User user);
	
	MyLearning findMyLearningByUserId(long id);

}
