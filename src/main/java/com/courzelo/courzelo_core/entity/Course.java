package com.courzelo.courzelo_core.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection="Course")
@Getter
@Setter
public class Course {
	
	

	@Id
	private String id;
	
	@Field(value = "User")
	private long idUser;
	
	@Field(value = "Title")
    private String title;

	@Field(value = "ShortDescription")
	private String description;
	
	@Field(value = "Details")
	private String details;

    @Field(value = "InstructorInfo")
	private String instructorInfo;
    
    @Field(value = "Price")
	private float price;
   
}
