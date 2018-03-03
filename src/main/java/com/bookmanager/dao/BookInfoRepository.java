package com.bookmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.bookmanager.domain.BookInfo;

public interface BookInfoRepository extends 
	JpaRepository<BookInfo, Long>,JpaSpecificationExecutor<BookInfo> {
	
	

}
