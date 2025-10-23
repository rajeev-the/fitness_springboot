package com.fitness.activityservice.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@RequiredArgsConstructor
public class UserVaildationService {

    private  final WebClient userVaildationWebClient;
    public boolean vaildateUser(String userid){

        try {

            return userVaildationWebClient.get()
                    .uri("/api/users/{userid}/vaildate",userid)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        }
        catch (WebClientResponseException e){
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found"+userid);
            }
            else if(e.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new RuntimeException("Invaild request" +userid);
            }
        }

        return false;
    }
}
