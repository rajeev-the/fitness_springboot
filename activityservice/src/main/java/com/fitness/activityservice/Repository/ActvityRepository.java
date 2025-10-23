package com.fitness.activityservice.Repository;

import com.fitness.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActvityRepository  extends MongoRepository<Activity,String> {

}
