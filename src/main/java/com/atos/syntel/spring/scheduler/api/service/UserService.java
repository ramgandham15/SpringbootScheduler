package com.atos.syntel.spring.scheduler.api.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atos.syntel.spring.scheduler.api.dao.UserDao;
import com.atos.syntel.spring.scheduler.api.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao dao;

	Logger log = LoggerFactory.getLogger(UserService.class);


	@Scheduled(fixedRate = 5000)
	public void addToDBJob() {
		User user = new User();
		user.setName("user" + new Random().nextInt(374483));
		dao.save(user);
		System.out.println("add service call in " + new Date().toString());
	}

	@Scheduled(cron = "0/15 * * * * *")
	public void fetchDBJob() {
		List<User> users = dao.findAll();
		System.out.println("fetch service call in " + new Date().toString());
		System.out.println("no of record fetched : " + users.size());
		log.info("users : {}", users);
	}
	
	
	
	
	
	/*

	@Scheduled(cron = "0/15 * * * * *")
	public void callDummyData() {
		System.out.println("call external service  " + new Date().toString());
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject("http://localhost:8082/job", String.class);
		System.out.println(result);
	}
	*/

}
