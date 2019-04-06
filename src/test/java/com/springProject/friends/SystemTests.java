package com.springProject.friends;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.springProject.friends.model.Friend;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FriendsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")

public class SystemTests {

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testCreateReadDelete() {
		String url = "/friend";
		Friend friend = new Friend("TestFirstName", "TestLastName");
		ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);
		
		Friend[] friends = restTemplate.getForObject(url, Friend[].class);
		
		Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("TestFirstName");
		
		restTemplate.delete	(url + "/"+ entity.getBody().getId());
		
		Friend[] noFriend = restTemplate.getForObject(url, Friend[].class);
		
		Assertions.assertThat(noFriend).isEmpty();
	}

}
