	package com.school.test.security;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;
	import org.springframework.stereotype.Component;
	import org.springframework.transaction.annotation.Transactional;
	
	import com.school.test.entity.User;
	import com.school.test.repository.UserRepository;
	
	
	
	@Component
	public class UserDetailsServiceImpl implements UserDetailsService {
	  @Autowired
	  UserRepository userRepository;
	  
	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
	
	    return UserDetailsImpl.build(user);
	  }
	
	}