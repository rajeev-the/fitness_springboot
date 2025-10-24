package com.fitness.aiservice.service;

import com.fitness.aiservice.Repository.RecommendationRepository;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    public List<Recommendation> getUserRecommendation(String userid) {

        return recommendationRepository.findByUserId(userid);
    }

    public Recommendation getActivityRecommendation(String activityId) {

        return recommendationRepository.findByActivityId(activityId).orElseThrow(()->new RuntimeException("no recommedation founed"+activityId));
    }
}
