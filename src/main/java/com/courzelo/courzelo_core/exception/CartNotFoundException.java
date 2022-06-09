package com.courzelo.courzelo_core.exception;

public class CartNotFoundException extends RuntimeException{
	public CartNotFoundException(String message) {
        super(message);
    }
}
