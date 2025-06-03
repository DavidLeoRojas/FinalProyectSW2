package edu.uptc.swii.cqrscontroller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.uptc.swii.cqrscontroller.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}