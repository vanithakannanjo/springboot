package com.vk.controller;

import com.vk.dto.CourseRequestDTO;
import com.vk.dto.CourseResponseDTO;
import com.vk.dto.ServiceResponse;
import com.vk.util.AppUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vk.service.CourseService;

import java.util.List;
@RestController
@RequestMapping("/course")
public class CourseController {

    Logger log = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        log.info("CourseController:addCourse request payload {}", AppUtils.convertObjectToJson(courseRequestDTO));
        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(newCourse);

        log.info("CourseController:addCourse response {}", AppUtils.convertObjectToJson(serviceResponse));
        return serviceResponse;//201
    }

    @GetMapping
    public ServiceResponse< List<CourseResponseDTO> > findALlCourse(){
        List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
        return new ServiceResponse<>(HttpStatus.OK, courseResponseDTOS);
    }

    @GetMapping("/search/path/{courseId}")
    public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId){
        CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
        return new ServiceResponse<>(HttpStatus.OK,responseDTO);
    }

    @GetMapping("/search/request")
    public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(@RequestParam(required = false) Integer courseId){
        return new ServiceResponse<>(HttpStatus.OK, courseService.findByCourseId(courseId));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId){
        log.info("CourseController:deleteCourse delete course with courseid {}", courseId);
        courseService.deleteCourse(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{courseId}")
    public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId, @RequestBody CourseRequestDTO courseRequestDTO){
        log.info("CourseController:updateCourse update course course with id {} & payload {} ", courseId, AppUtils.convertObjectToJson(courseRequestDTO));

        ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();
        CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, courseRequestDTO);
        serviceResponse.setStatus(HttpStatus.OK);
        serviceResponse.setResponse(courseResponseDTO);
        log.info("CourseController:updateCourse update course course with id {} & payload {} ", courseId, AppUtils.convertObjectToJson(serviceResponse));
        return serviceResponse;
    }

}
