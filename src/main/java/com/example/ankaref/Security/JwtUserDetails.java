package com.example.ankaref.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.example.ankaref.Entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtUserDetails implements UserDetails {

	public Long id;
	private String username;
	private String password;
   private Collection<?extends GrantedAuthority> authorities;

    public static JwtUserDetails creater(Users user) {
    List <GrantedAuthority>	 authoritiesList=new ArrayList<>();
    authoritiesList.add(new SimpleGrantedAuthority("user"));
    return new JwtUserDetails(user.getId(), user.getEmail(), user.getPassword(), authoritiesList);
    	
    }
   
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}


	

}