package com.sise.cch.controller;

import com.sise.cch.domain.User;
import com.sise.cch.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController{
    @Autowired
    private UserService userService;

    @RequestMapping("showUser")
    public String findAll(Model model){
        Pageable pageable = PageRequest.of(0,2);
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("pageUser",page);
        return "show";
    }
    @RequestMapping("showUserByPage")
    public String showUserByPage(Model model,Integer pageNum){
        Pageable pageable = PageRequest.of(pageNum,2);
        Page<User> page = userService.findAll(pageable);
        model.addAttribute("pageUser",page);
        return "show";
    }
}