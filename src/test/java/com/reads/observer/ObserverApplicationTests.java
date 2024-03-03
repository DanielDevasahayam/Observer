package com.reads.observer;

import com.reads.observer.entity.Users;
import com.reads.observer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ObserverApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
		//List<Users> users = userRepository.findByName("daniel");
		//System.out.println(users.size());
	}

}
