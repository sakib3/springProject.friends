package com.springProject.friends.service;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.springProject.friends.model.Friend;

public interface FriendService extends CrudRepository<Friend, Integer> {

	Iterable<Friend> findByFirstNameAndLastName(String firstname, String lastname);
	Iterable<Friend> findByFirstName(String firstname);
	Iterable<Friend> findByLastName(String lastname);
}
