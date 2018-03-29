package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Cave;

@Repository
public interface CaveDao extends JpaRepository<Cave, Integer>{
	Cave findByName(String name);
	List<Cave> findByIdBetween(Integer min, Integer max);
}
