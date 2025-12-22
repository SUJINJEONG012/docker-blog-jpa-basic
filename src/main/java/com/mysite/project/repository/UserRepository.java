package com.mysite.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.project.model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	//select* from user where username = 1?
	Optional<Users> findByUsername(String username);

}


//전통적인로그인방식
//User findByUsernameAndPassword(String username, String password);

//@Query(value="")
//User login()