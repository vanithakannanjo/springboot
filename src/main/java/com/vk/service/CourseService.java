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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log = LoggerFactory.getLogger(CourseService.class);

@Autowired
    public CourseDao courseDao;



    //create course object in DB -> POST
    public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {

        CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
        CourseEntity entity =null;
        log.info("CourseService::onboardNewCourse method execution started...");
        try {
            entity = courseDao.save(courseEntity);
            log.info("CourseService::onboardNewCourse, course entity {} " + AppUtils.convertObjectToJson(entity));
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntiyToDTO(entity);
            courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
            log.info("CourseService::onboardNewCourse, course courseResponseDTO {} " + AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::onboardNewCourse method execution ended...");
            return courseResponseDTO;
        }catch (Exception exception){
            log.error("CourseService::onboardNewCourse exception occurred while adding the new course", exception.getMessage());
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
        log.info("CourseService::deleteCourse method execution started");
        try {
            log.debug("CourseService::deleteCourse courseId  {} " + courseId);
            courseDao.deleteById(courseId);
        }catch(Exception ex){
            log.error("CourseService::deleteCourse exception occurred while delete course.", ex.getMessage());
            throw new CourseServiceBusinessException("deletecourse service method failed");
        }

        log.info("CourseService::deleteCourse method execution ended");
    }

    //update the course //PUT
    public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {

        log.info("CourseService::updateCourse method execution started");
        try {
            log.debug("CourseService::updateCourse request payload {} & id{}", AppUtils.convertObjectToJson(courseRequestDTO), courseId);
            CourseEntity existingCourseEntity = courseDao.findById(courseId).orElseThrow(()-> new RuntimeException("Course object not present in db with id : " + courseId));
            log.debug("CourseService::updateCourse getting existing course object as {}", AppUtils.convertObjectToJson(existingCourseEntity));

            existingCourseEntity.setName(courseRequestDTO.getName());
            existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
            existingCourseEntity.setDuration(courseRequestDTO.getDuration());
            existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
            existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
            existingCourseEntity.setFees(courseRequestDTO.getFees());
            existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
            existingCourseEntity.setDescription(courseRequestDTO.getDescription());

            CourseEntity updateCourseEntity = courseDao.save(existingCourseEntity);
            CourseResponseDTO courseResponseDTO = AppUtils.mapEntiyToDTO(updateCourseEntity);
            log.debug("CourseService::updateCourse update course entity object  {}", AppUtils.convertObjectToJson(courseResponseDTO));
            log.info("CourseService::updateCourse method execution ended");
            return courseResponseDTO;
        }catch(Exception ex){
            log.error("CourseService::updateCourse exception occurred while updating course", ex.getMessage());
            throw new CourseServiceBusinessException("updatecourse service failed");
        }

    }


}
