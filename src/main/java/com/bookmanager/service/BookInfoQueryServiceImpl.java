package com.bookmanager.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.bookmanager.dao.BookInfoQueryService;
import com.bookmanager.dao.BookInfoRepository;
import com.bookmanager.domain.BookInfo;
import com.bookmanager.domain.BookQuery;

@Service(value="bookInfoQueryServiceImpl")
public class BookInfoQueryServiceImpl implements BookInfoQueryService {

	@Autowired
	BookInfoRepository bookInfoRepository;
	
	@Override
	public Page<BookInfo> findBookNoCriteria(Integer page, Integer size) {
		
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		
		return bookInfoRepository.findAll(pageable);
	}

	@Override
	public Page<BookInfo> findBookCriteria(Integer page, Integer size, BookQuery bookQuery) {
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		
		Page<BookInfo> bookPage = bookInfoRepository.findAll(new Specification<BookInfo>() {

			@Override
			public Predicate toPredicate(Root<BookInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate p1 = cb.equal(root.get("title").as(String.class), bookQuery.getTitle());
				Predicate p2 = cb.equal(root.get("content").as(String.class), bookQuery.getContent());
				Predicate p3 = cb.equal(root.get("extraInfo").as(String.class), bookQuery.getExtraInfo());
				
				query.where(cb.or(p1,p2,p3));
				return query.getRestriction();
			}
			
		}, pageable);
		
		return bookPage;
	}

}
