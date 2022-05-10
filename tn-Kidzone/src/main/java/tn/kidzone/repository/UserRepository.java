package tn.kidzone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.kidzone.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	User findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);

	
}
