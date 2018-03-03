package com.bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookmanager.dao.SysUserRepository;
import com.bookmanager.domain.SysUser;

@Service(value="userService")
public class UserService implements UserDetailsService {

	@Autowired
	SysUserRepository sysUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sysUserRepository.findByUsername(username);
		System.out.println("username---" +username);
		if (user == null) {
			throw new UsernameNotFoundException("user is not exsit");
		}
		System.out.println("username:" + username);
	    System.out.println("username:" + user.getUsername() + ";password:" + user.getPassword());
		return user;
	}

}

