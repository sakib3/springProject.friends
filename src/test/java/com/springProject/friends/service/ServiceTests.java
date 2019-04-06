package com.springProject.friends.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.springProject.friends.model.Friend;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {
	
	@Autowired
	private FriendService friendService;
	
	@Test
	public void testcreateReadDelete() {
		Friend friendTest = new Friend("TestSerFirstName", "TestSerLastName");
		
		friendService.save(friendTest);
		Iterable<Friend> friends = friendService.findAll();
		
		Assertions.assertThat(friends).first().extracting("firstName").contains("TestSerFirstName");
		Assertions.assertThat(friends).first().extracting("lastName").contains("TestSerLastName");
		
		friendService.deleteAll();
		
		Iterable<Friend> noFriends = friendService.findAll();
		
		Assertions.assertThat(noFriends).isEmpty();
		
	}

}
