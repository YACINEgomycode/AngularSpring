package tn.kidzone.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import tn.kidzone.entity.ERole;
import tn.kidzone.entity.Role;
import tn.kidzone.entity.User;
import tn.kidzone.repository.RoleRepository;
import tn.kidzone.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Log4j2
public class UserService implements IUserService, UserDetailsService {

	private final PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRep;
	@Autowired
	RoleRepository roleRep;

	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> retrieveUsers() {
		
		return (List<User>) userRep.findAll();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user= userRep.findByUsername(username);
		 if(user==null){
			 log.error("User not found in the database");
			 throw new UsernameNotFoundException("User not found in the database");
		 }
		 else{
			 log.info("User found in the database: {}",username);
		 }
		Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
		 user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		 } );

		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities );
	}

	@Override
	public User retrieveById(Long id) {
		
		return userRep.findById(id).orElse(null);
	}

	@Override
	public User saveUser(User us) {
		log.info("Saving new user {} to the database",us.getUsername());
		us.setPassword(passwordEncoder.encode(us.getPassword()));
		return userRep.save(us);

	}



	@Override
	public User addRoleToUser(String username, ERole roleName) {
		User user= userRep.findByUsername(username);
		Role role= roleRep.findByName(roleName);
		user.getRoles().add(role);
		return user;

	}

	@Override
	public User getUserByUserName(String username) {
		return  userRep.findByUsername(username);
	}

	@Override
	public User updateUser(User us) {
		
		return userRep.save(us);
	}

	@Override
	public void deleteUser(Long id) {
		
		userRep.deleteById(id);
		
		
	}

	@Override
	public List<Role> retrieveUserRoles(String username) {
		User user= userRep.findByUsername(username);
		return (List<Role>) user.getRoles();
	}


}
