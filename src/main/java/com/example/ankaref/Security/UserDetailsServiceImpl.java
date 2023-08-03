package com.example.ankaref.Security;

import com.example.ankaref.DataAccess.UserRepository;
import com.example.ankaref.Entities.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Service

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		Users user = userRepository.findByEmail(email);

		return JwtUserDetails.creater(user);
	}

	public org.springframework.security.core.userdetails.UserDetails loadUserById(Long id) {

		Users user = userRepository.findById(id).get();
		return JwtUserDetails.creater(user);
	}

	








}