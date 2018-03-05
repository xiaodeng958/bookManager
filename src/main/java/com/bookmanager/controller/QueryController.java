package com.bookmanager.controller;


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
@RequestMapping("/query")
public class QueryController {

	@Autowired
	BookInfoRepository bookInfoRepository;
	
	@Autowired
	BookInfoQueryService bookInfoQueryService;
	
	@RequestMapping("/register")
	public String register(Model model) {
		System.out.println("register");
		return "html/register";
	}
	
	@RequestMapping(value="/bookNoQuery", method=RequestMethod.GET)
	public String findbookNoQuery(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size) {
		
		Page<BookInfo> books = bookInfoQueryService.findBookNoCriteria(page, size);
		model.addAttribute("datas", books);
		System.out.println("bookNoQuery");
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/bookQuery", method=RequestMethod.GET)
	public String findbookQuery(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size, BookQuery bookQuery) {
		
		Page<BookInfo> books = bookInfoQueryService.findBookCriteria(page, size, bookQuery);
		model.addAttribute("datas", books);
		System.out.println("bookQuery");
		
		return "redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String list(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size) {
		
		Page<BookInfo> books = bookInfoQueryService.findBookNoCriteria(page, size);
		model.addAttribute("datas", books);
		System.out.println("bookNoQuery");
		
        return "query/list";
    }
}
