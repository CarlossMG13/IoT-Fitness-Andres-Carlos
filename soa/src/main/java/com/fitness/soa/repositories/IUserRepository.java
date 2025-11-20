package com.fitness.soa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fitness.soa.models.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, Long>{
    
}
