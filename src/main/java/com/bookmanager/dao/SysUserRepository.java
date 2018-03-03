package com.bookmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmanager.domain.SysUser;
import java.lang.String;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

	SysUser findByUsername(String username);
	
}
