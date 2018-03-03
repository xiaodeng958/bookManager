package com.bookmanager.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookmanager.dao.BookInfoQueryService;
import com.bookmanager.dao.BookInfoRepository;
import com.bookmanager.domain.BookInfo;
import com.bookmanager.domain.BookQuery;

@Controller

public class BookManagerController {

	@Autowired
	BookInfoRepository bookInfoRepository;
	
	@Autowired
	BookInfoQueryService bookInfoQueryService;
	
	@RequestMapping("/")
	public String home(Model model) {
		
//		List<BookInfo> books = new ArrayList<BookInfo>();
//		
//		BookInfo book = null;
//		for (int i = 0; i < 20; i++) {
//			book = new BookInfo();
//			book.setTitle("sanguoyanyi".concat(String.valueOf(i)));
//			book.setContent("fsadfasdf");
//			book.setExtraInfo("ExtraInfo");
//			bookInfoRepository.save(book);
//		}
//		books = bookInfoRepository.findAll();
//
//		model.addAttribute("books", books);
		return "bookList";
	}
	
	@RequestMapping("/bookList")
	public String getBooks(Model model) {
		
//		List<BookInfo> books = new ArrayList<BookInfo>();
//		
//		BookInfo book = null;
//		for (int i = 0; i < 20; i++) {
//			book = new BookInfo();
//			book.setTitle("sanguoyanyi".concat(String.valueOf(i)));
//			book.setContent("fsadfasdf");
//			book.setExtraInfo("ExtraInfo");
//			bookInfoRepository.save(book);
//		}
//		books = bookInfoRepository.findAll();
//
//		model.addAttribute("books", books);
		return "bookList";
	}
	
	@RequestMapping(value="/findbookNoQuery", method=RequestMethod.GET)
	public String findbookNoQuery(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size) {
		
		Page<BookInfo> books = bookInfoQueryService.findBookNoCriteria(page, size);
		model.addAttribute("datas", books);
		
		return "bookNoQuery";
	}
	
	@RequestMapping(value="/findbookQuery", method=RequestMethod.GET)
	public String findbookQuery(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size, BookQuery bookQuery) {
		
		Page<BookInfo> books = bookInfoQueryService.findBookCriteria(page, size, bookQuery);
		model.addAttribute("datas", books);
		
		return "bookQuery";
	}
	
}
