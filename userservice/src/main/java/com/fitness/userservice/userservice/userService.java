package com.fitness.userservice.userservice;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import com.fitness.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class userService {

    private final UserRepository userRepository;
    public UserResponse regsiter( @Valid RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setEmail(request.getEmail());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setPassword(request.getPassword());


        User SavedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(SavedUser.getEmail());
        userResponse.setFirstname(SavedUser.getFirstname());
        userResponse.setLastname(SavedUser.getLastname());
        userResponse.setPassword(SavedUser.getPassword());
        userResponse.setId(SavedUser.getId());

        return userResponse;
    }

    public UserResponse getUserProfile(String userid) {
        User  user = userRepository.findById(userid).orElseThrow(()-> new RuntimeException("User not found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setId(user.getId());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());

        return userResponse;

    }

    public Boolean existUserId(String userid) {
        return userRepository.existsById(userid);
    }
}
