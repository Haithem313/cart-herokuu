package com.courzelo.courzelo_core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.courzelo.courzelo_core.entity.Cart;
import com.courzelo.courzelo_core.entity.User;

@Repository
public interface CartRepository extends MongoRepository<Cart, Long>{
	
	void deleteCartByIdCart(long idCart);
	
	Optional<Cart> findCartByIdCart(long idCart);
	
	List<Cart> findCartByUser(User user);


}
