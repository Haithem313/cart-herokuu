package com.courzelo.courzelo_core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;

import com.courzelo.courzelo_core.entity.Cart;
import com.courzelo.courzelo_core.entity.Course;
import com.courzelo.courzelo_core.entity.User;
import com.courzelo.courzelo_core.exception.CartNotFoundException;
import com.courzelo.courzelo_core.repository.CartRepository;
import com.courzelo.courzelo_core.repository.CourseRepository;



@Service
public class CartService {
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	private static final String GET_USER_BY_ID_API = "https://springgateway.herokuapp.com/auth-herokuu/api/auth/getUser/{id}";
	
	public Course addCourse(Course course){
		return courseRepository.save(course);
	}
	
	public User getUserByRestTemplate(long id){
		Map<String, Long> param = new HashMap<>();
		param.put("id", id);
		User user = restTemplateBuilder.build().getForObject(GET_USER_BY_ID_API, User.class, param);
		return user;
	}
	
	public Cart addCart(Cart cart, Long id, String idCourse) throws Exception {
		Course course = courseRepository.findCourseById(idCourse);
		User user = getUserByRestTemplate(id);
		List<Cart> carts = findCartByUser(id);
		for (int i = 0; i < carts.size(); i++) {
			if(carts.get(i).getCourse().getId() == course.getId()){
				throw new Exception("You have already add this course to your cart");
			}
		}
		cart.setCourse(course);
		cart.setUser(user);
		cart.setIdCart(sequenceGeneratorService.generateSequence(Cart.SEQUENCE_NAME));
		cart.setState(false);
        return cartRepository.save(cart);
    }

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findCartById(long id) {
        return cartRepository.findCartByIdCart(id)
                .orElseThrow(() -> new CartNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteCart(long id){
        cartRepository.deleteCartByIdCart(id);
    }
    
    public List<Cart> findCartByUser(long id){
    	User user = getUserByRestTemplate(id);
    	return cartRepository.findCartByUser(user);
    }
    
    public void deleteAllUserCart(long id){
    	User user = getUserByRestTemplate(id);
    	List<Cart> carts = cartRepository.findCartByUser(user);
    	for(int i=0; i<carts.size(); i++){
    		cartRepository.deleteCartByIdCart(carts.get(i).getIdCart());    		
    	}    	
    }

}
