package com.springProject.friends.service;

import org.springframework.data.repository.CrudRepository;
import com.springProject.friends.model.Friend;

public interface FriendService extends CrudRepository<Friend, Integer> {

}
