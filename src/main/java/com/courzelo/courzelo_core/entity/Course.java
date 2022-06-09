package com.courzelo.courzelo_core.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document
@Getter
@Setter
public class Course {
	
	@Transient
    public static final String SEQUENCE_NAME = "courses_sequence";

	@Id
	private long idCourse;
	
	@Field(value = "Title")
    private String title;
	
	@Field(value = "Language")
	private String language;
	
	@Field(value = "Category")
	private String category;  
	
	@Field(value = "Outcomes")
	private String outcomes;
	
	@Field(value = "Details")
	private String details;
	
	@Field(value = "Skills_Required")
	private List<String> skillsRequired;
    
    /*@Field(value="Introduction")
    private ExtremCourse introduction;
    
    @Field(value="Sections")
    private List<ExtremCourse> sections;
    
    @Field(value="Customer Sections")
    private List<CustomerExtremCourse> customerSections;
    
    @Field(value="Conclusion")
    private ExtremCourse conclusion;*/
    
    @Field(value = "Audience")
	private String audience;
    
    @Field(value = "Pre-Requisites")
	private String preRequisites;
    
    @Field(value = "InstructorInfo")
	private String instructorInfo;
    
    @Field(value = "Image")
	private String courseImage;
    
    @Field(value = "Video")
	private String courseVideo;
    
    @Field(value = "Price")
	private float price;
    
	public static String getSequenceName() {
		return SEQUENCE_NAME;
	}

	

}
