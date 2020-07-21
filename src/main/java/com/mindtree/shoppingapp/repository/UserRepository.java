package com.mindtree.shoppingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.shoppingapp.entity.User;

/**
 * @author M1049006
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
