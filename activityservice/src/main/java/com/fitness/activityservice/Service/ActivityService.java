package com.fitness.activityservice.Service;

import com.fitness.activityservice.Repository.ActvityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final  ActvityRepository actvityRepository;
    private final UserVaildationService userVaildationService;
    private final KafkaTemplate<String,Activity> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private  String topicname;

    public ActivityResponse trackActvity(ActivityRequest request) {

        boolean isvaildate = userVaildationService.vaildateUser(request.getUserId());
        if(!isvaildate){
            throw  new RuntimeException("Invalid user"+request.getUserId());
        }

        Activity activity =  Activity.builder()
                .userid(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .calories(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = actvityRepository.save(activity);

        try {
            kafkaTemplate.send(topicname,savedActivity.getUserid(),savedActivity);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return mapToResponse(savedActivity);

    }

    private ActivityResponse mapToResponse(Activity activity) {

        ActivityResponse Response = new ActivityResponse();
        Response.setId(activity.getId());
        Response.setUserId(activity.getUserid());
        Response.setType(activity.getType());
        Response.setDuration(activity.getDuration());
        Response.setCaloriesBurned(activity.getCalories());
       Response.setAdditionalMetrics(activity.getAdditionalMetrics());
        Response.setStartTime(activity.getStartTime());
        Response.setCreatedAt(activity.getCreatedAt());
        Response.setUpdatedAt(activity.getUpdatedAt());


        return  Response;
    }
}
