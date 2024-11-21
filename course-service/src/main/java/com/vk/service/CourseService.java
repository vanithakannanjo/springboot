package com.vk.service;

import com.vk.dao.CourseDao;
import com.vk.dto.CourseRequestDTO;
import com.vk.dto.CourseResponseDTO;
import com.vk.entity.CourseEntity;
import com.vk.exception.CourseServiceBusinessException;
import com.vk.util.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service


public class CourseService {

@Autowired
    public CourseDao courseDao;



    //create course object in DB -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {

        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity =null;

        try {
            entity = courseDao.save(courseEntity);

            CourseResponseDTO courseResponseDTO = AppUtils.mapEntiyToDTO(entity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);

            return courseResponseDTO;
        }catch (Exception exception){
            throw new CourseServiceBusinessException(("onboard New Course service method failed..."));
        }

    }

    //load all the course from Database  // GET
    public List<CourseResponseDTO> viewAllCourses() {
        Iterable<CourseEntity> courseEntities = courseDao.findAll();

        return StreamSupport.stream(courseEntities.spliterator(),false)
                .map(courseEntity -> AppUtils.mapEntiyToDTO(courseEntity))
                .collect(Collectors.toList());

    }

    //filter course by course id //GET
    public CourseResponseDTO findByCourseId(Integer courseId) {
        CourseEntity courseEntity = courseDao.findById(courseId)
                .orElseThrow(()->new CourseServiceBusinessException( courseId + " does not exist"));

       return AppUtils.mapEntiyToDTO(courseEntity);
    }

    //delete course  //DELETE
    public void deleteCourse(int courseId) {
        courseDao.deleteById(courseId);
    }

    //update the course //PUT
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
        CourseEntity existingCourseEntity = courseDao.findById(courseId).orElse(null);
        existingCourseEntity.setName(courseRequestDTO.getName());
        existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
        existingCourseEntity.setDuration(courseRequestDTO.getDuration());
        existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
        existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
        existingCourseEntity.setFees(courseRequestDTO.getFees());
        existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
        existingCourseEntity.setDescription(courseRequestDTO.getDescription());

        CourseEntity updateCourseEntity = courseDao.save(existingCourseEntity);
        return AppUtils.mapEntiyToDTO(updateCourseEntity);

    }


}
