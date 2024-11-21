/*
package com.vk.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vk.dto.Course;
import org.hibernate.jdbc.Expectation;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.converter.json.GsonFactoryBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CourseTypeConsolidate {


    private static List<Course>  courseDatabase = new ArrayList<>();

    public static void main(String[] args) {



       String json1 = "{\n        \"courseId\": 31,\n            \"name\": \"Vertx1\",\n            \"trainerName\": \"VK1\",\n            \"duration\": \"1300\",\n            \"startDate\": \"18-02-2024\",\n            \"courseType\": \"Live Class\",\n            \"fees\": 15000.0,\n            \"description\": \"Complete Spring boot microservice course\",\n            \"certificateAvailable\": false\n    }";
       String json2 = "{\n        \"courseId\": 32,\n            \"name\": \"Vertx2\",\n            \"trainerName\": \"VK2\",\n            \"duration\": \"2300\",\n            \"startDate\": \"18-02-2024\",\n            \"courseType\": \"Recording Class\",\n            \"fees\": 25000.0,\n            \"description\": \"Complete Spring boot microservice course\",\n            \"certificateAvailable\": false\n    }";
       String json3 = "{\n        \"courseId\": 33,\n            \"name\": \"Vertx3\",\n            \"trainerName\": \"VK3\",\n            \"duration\": \"3300\",\n            \"startDate\": \"18-02-2024\",\n            \"courseType\": \"Live Class\",\n            \"fees\": 35000.0,\n            \"description\": \"Complete Spring boot microservice course\",\n            \"certificateAvailable\": false\n    }";
       String json4 = "{\n        \"courseId\": 34,\n            \"name\": \"Vertx4\",\n            \"trainerName\": \"VK4\",\n            \"duration\": \"4300\",\n            \"startDate\": \"18-02-2024\",\n            \"courseType\": \"Recording Class\",\n            \"fees\": 45000.0,\n            \"description\": \"Complete Spring boot microservice course\",\n            \"certificateAvailable\": false\n    }";
       String json5 = "{\n        \"courseId\": 35,\n            \"name\": \"Vertx5\",\n            \"trainerName\": \"VK5\",\n            \"duration\": \"5300\",\n            \"startDate\": \"18-02-2024\",\n            \"courseType\": \"Live Class\",\n            \"fees\": 55000.0,\n            \"description\": \"Complete Spring boot microservice course\",\n            \"certificateAvailable\": false\n    }";


        ObjectMapper mapper = new ObjectMapper();
        Course c1 = new Course();
        Course c2 = new Course();
        Course c3 = new Course();
        Course c4 = new Course();
        Course c5 = new Course();


        try {
            c1 = mapper.readValue(json1, Course.class);
            c2 = mapper.readValue(json2, Course.class);
            c3 = mapper.readValue(json3, Course.class);
            c4 = mapper.readValue(json4, Course.class);
            c5 = mapper.readValue(json5, Course.class);


            courseDatabase.add(c1);
            courseDatabase.add(c2);
            courseDatabase.add(c3);
            courseDatabase.add(c4);
            courseDatabase.add(c5);

        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String, IntSummaryStatistics> = courseDatabase.stream()
                        .collect(groupingBy(Course::getCourseType),summarizingInt(Course::getCourseType));


        System.out.println(courseDatabase.toString());
        System.out.println(courseDatabase.size());


    }


}
*/
