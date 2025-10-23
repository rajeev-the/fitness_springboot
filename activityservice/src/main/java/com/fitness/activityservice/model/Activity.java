package com.fitness.activityservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Map;

@Document("activities")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    private String id;
    private String userid;
    private ActivityType type;
    private Integer duration;
    private  Integer calories;
    private LocalDateTime startTime;
    @Field("matrices")
    private Map<String,Object> additionalMetrics;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
