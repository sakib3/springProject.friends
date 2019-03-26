package com.springProject.friends.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.friends.model.Friend;
import com.springProject.friends.service.FriendService;

@RestController
public class FriendController {

	@Autowired
	FriendService friendService;
	
	@PostMapping("friend")
	Friend create(@Valid @RequestBody Friend friend) {
		return friendService.save(friend);
	}
		
	@GetMapping("friend")
	Iterable<Friend> read() {
		return friendService.findAll();
	}
	
	@PutMapping("friend")
	ResponseEntity<Friend> update(@RequestBody Friend friend) {
		if(friendService.findById(friend.getId()).isPresent())
			return new ResponseEntity<Friend>(friendService.save(friend), HttpStatus.OK);
		else
		return new ResponseEntity<Friend>(friend, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("friend/{id}")
	void delete(@PathVariable Integer id) {
		friendService.deleteById(id);
	}
	
	@GetMapping("friend/{id}")
	Optional<Friend> findById(@PathVariable Integer id) {
		return friendService.findById(id);
	}
	
	@GetMapping("friend/search")
	Iterable<Friend> findByQuery(
			@RequestParam(value = "first", required = false) String firstname, 
			@RequestParam(value = "last", required = false) String lastname) {
		if(firstname != null && lastname != null)
		return friendService.findByFirstNameAndLastName(firstname, lastname);
		if(firstname != null)
			return friendService.findByFirstName(firstname);
		if(lastname != null)
			return friendService.findByLastName(lastname);
		return
			friendService.findAll();
		
	}
}
