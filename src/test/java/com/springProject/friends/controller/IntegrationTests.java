package com.springProject.friends.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.springProject.friends.FriendsApplication;
import com.springProject.friends.model.Friend;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class IntegrationTests {
	
	@Autowired
	private FriendController friendcontroller;
	
	@Test
	public void testcreateReadDelete() {
		Friend friend = new Friend("TestIntFirstName", "TestIntLastName");
		
		Friend friendCreated = friendcontroller.create(friend);
		Iterable<Friend> friends = friendcontroller.read();
		
		Assertions.assertThat(friends).first().extracting("firstName").contains("TestIntFirstName");
		Assertions.assertThat(friends).first().extracting("lastName").contains("TestIntLastName");
		
		int friendId = friendCreated.getId();
		
		friendcontroller.delete(friendId);
		
		Assertions.assertThat(friendcontroller.read()).isEmpty();
	}

}
