package com.vk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vk.annotation.CourseTypeValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {
    @NotBlank(message = "Course name should not be NULL or Empty!")
    private String name;
    @NotEmpty(message = "Trainer name should be defined")
    private String trainerName;
    @NotNull(message = "Duration must need to specify")
    private String duration; // days
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    @Past(message="Start date cannot be before the current date")
    private Date startDate;
    @CourseTypeValidation(message = "Course type is not valid")
    private String courseType; //Live OR Recording
    @Min(value = 1500, message = "Course price should not be less than 1500")
    @Max(value=5000, message = "Course price should not be more than 5000")
    private double fees;
    private boolean isCertificateAvailable;
    @NotEmpty(message = "Description must be given")

    private String description;
    @Email(message = "Invalid Email")
    private String emailId;
    @Pattern(regexp = "^[0-9]{10}$")
    private String contact;
}
