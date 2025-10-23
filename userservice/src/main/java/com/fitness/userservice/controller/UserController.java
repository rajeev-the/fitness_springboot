package com.fitness.userservice.controller;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.userservice.userService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

   private userService userService;

   @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request){
       return ResponseEntity.ok(userService.regsiter(request));
   }
    @GetMapping("/check")
    public String register(){
        return "All good";
    }

    @GetMapping("/{userid}")
    public  ResponseEntity<UserResponse> getUser(@PathVariable("userid") String userid){

       if(userid==null || userid.isEmpty()){
           ResponseEntity.status(404).body("Userid is required");
       }


       return  ResponseEntity.ok(userService.getUserProfile(userid));

    }

    @GetMapping("/{userid}/vaildate")
    public ResponseEntity<Boolean> vaildateUser(@PathVariable String userid  ){

       return  ResponseEntity.ok(userService.existUserId(userid));
    }


}
