package tn.kidzone.security.services;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import tn.kidzone.entity.User;
import tn.kidzone.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
@Slf4j
@Service
//@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
           // .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }
/*public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user= userRepository.findByUsername(username);
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
  }*/

}
