package com.bookmanager.dao;

import org.springframework.data.domain.Page;

import com.bookmanager.domain.BookInfo;
import com.bookmanager.domain.BookQuery;

public interface BookInfoQueryService {
	
	Page<BookInfo> findBookNoCriteria(Integer page, Integer size);
	Page<BookInfo> findBookCriteria(Integer page, Integer size, BookQuery bookQuery);
}
