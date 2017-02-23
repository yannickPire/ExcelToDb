package com.axa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axa.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByName(String name);

}
