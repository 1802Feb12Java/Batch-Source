package com.revature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.revature.model.Cave;
import com.revature.repository.CaveDao;

@SpringBootApplication
public class App {
	
	@Autowired
	CaveDao caveDao;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			List<Cave> caves = caveDao.findAll();
			Cave myCave = caveDao.findByName("Gloworm Cave");
			List<Cave> myOtherCave = caveDao.findByIdBetween(2, 2);
			System.out.println(caves.toString());
			System.out.println(myCave.toString());
			System.out.println(myOtherCave.toString());
		};
	}

}
