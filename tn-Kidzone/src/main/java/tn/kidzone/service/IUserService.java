package tn.kidzone.service;

import java.util.List;

import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.entity.User;


public interface IUserService {
	
	List<User> retrieveUsers();
	User retrieveById(Long id);
	User saveUser(User us);
	User addRoleToUser(String username , ERole roleName);
	User getUserByUserName(String username);
	User updateUser(User us);
	void deleteUser(Long id);
	List<Role> retrieveUserRoles(String username);


}








	
	
	


