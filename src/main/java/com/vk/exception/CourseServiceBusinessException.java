package com.vk.exception;

public class CourseServiceBusinessException extends RuntimeException{
    public CourseServiceBusinessException(String message) {
        super(message);
    }
}
