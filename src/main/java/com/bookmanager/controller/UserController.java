package com.bookmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookmanager.dao.SysUserRepository;
import com.bookmanager.domain.SysUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	SysUserRepository sysUserRepository;
	
	@RequestMapping(value="/register")
	public String register(Model model) {
		System.out.println("register");
		return "user/register";
	}
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public String addUser(@ModelAttribute SysUser Sysuser) {
		System.out.println(Sysuser.getUsername());
		System.out.println(Sysuser.getPassword());
		
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String list(Model model ,@RequestParam(value="page", defaultValue="0") Integer page
			, @RequestParam(value="size", defaultValue="5") Integer size) {
		
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
		Page<SysUser> list = sysUserRepository.findAll(pageable);
		model.addAttribute("datas", list);
		System.out.println("userList");
		
        return "user/list";
    }
}
