package com.fitness.activityservice.controller;

import com.fitness.activityservice.Service.ActivityService;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/activties")
@RequiredArgsConstructor
public class ActivityController {

    private final   ActivityService activityService;


    @PostMapping("/created")
    public ResponseEntity<ActivityResponse> trackactivity(@RequestBody ActivityRequest request , @RequestHeader("X-User-ID") String userid){

        return ResponseEntity.ok(activityService.trackActvity(request));
    }

    @GetMapping("/test")
    public String testing(){
        return "test";
    }
}
