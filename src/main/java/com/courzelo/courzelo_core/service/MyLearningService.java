package com.courzelo.courzelo_core.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.courzelo.courzelo_core.entity.Cart;
import com.courzelo.courzelo_core.entity.Course;
import com.courzelo.courzelo_core.entity.MyLearning;
import com.courzelo.courzelo_core.entity.User;
import com.courzelo.courzelo_core.repository.CourseRepository;
import com.courzelo.courzelo_core.repository.MyLearningRepository;

@Service
public class MyLearningService {
	
	@Autowired
	MyLearningRepository myLearningRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	private static final String GET_USER_BY_ID_API = "http://localhost:8087/api/auth/getUser/{id}";
	
	public User getUserByRestTemplate(long id){
		Map<String, Long> param = new HashMap<>();
		param.put("id", id);
		User user = restTemplateBuilder.build().getForObject(GET_USER_BY_ID_API, User.class, param);
		return user;
	}
	
	public MyLearning addMylearning(long id){
		MyLearning myLearning = new MyLearning();
		List<Course> courses = new ArrayList<Course>();
		User user = getUserByRestTemplate(id);
		List<Cart> carts = cartService.findCartByUser(id);
			for(int i=0; i<carts.size(); i++){
				courses.add(carts.get(i).getCourse());			
			}
			myLearning.setIdMyLearning(sequenceGeneratorService.generateSequence(MyLearning.SEQUENCE_NAME));
			myLearning.setCourses(courses);
			myLearning.setUser(user);
			return myLearningRepository.save(myLearning);
	}
	
	public MyLearning findMyLearningByUser(long id){
		User user = getUserByRestTemplate(id);
		return myLearningRepository.findMyLearningByUserId(user.getId());
	}
	
	public boolean existsMyLearningByUser(long id){
		User user = getUserByRestTemplate(id);
		return myLearningRepository.existsByUser(user);
	}
	
	public MyLearning updateMyLearning(long id){
		User user = getUserByRestTemplate(id);
		MyLearning myLearning = myLearningRepository.findMyLearningByUser(user);
		List<Course> courses = new ArrayList<Course>();
		List<Cart> carts = cartService.findCartByUser(id);
		courses.addAll(myLearning.getCourses());
		for(int i=0; i<carts.size(); i++){
			courses.add(carts.get(i).getCourse());			
		}
		myLearning.setCourses(courses);
		return myLearningRepository.save(myLearning);
	}

}
