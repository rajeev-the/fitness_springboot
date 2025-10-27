package com.fitness.aiservice.service;


import com.fitness.aiservice.Repository.RecommendationRepository;
import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityMessageListener {

    private final ActvityAiService  activityAiService;
    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "activity-processor-group")
    public void processActivity(Activity activity) {
        log.info("Processing activity {}", activity.getUserid());
      Recommendation recommendation = activityAiService.generateRecommendation(activity);
      recommendationRepository.save(recommendation);

    }




}
