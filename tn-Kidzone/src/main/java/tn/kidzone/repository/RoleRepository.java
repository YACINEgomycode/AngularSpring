package tn.kidzone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(ERole name);
	

}
