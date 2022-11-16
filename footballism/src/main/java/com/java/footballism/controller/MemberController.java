package com.java.footballism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.footballism.UserCheck;
import com.java.footballism.service.MemberService;

import io.swagger.annotations.ApiOperation;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService; 
	
	
	@UserCheck	
	@GetMapping("/check")
    @ApiOperation(value = "AOP 테스트")
    public String checkUser(@RequestParam(value = "userSub", required = true) String userSub) {
    	 
		memberService.getMemberList();

    	return "checkUser";
    }
	
	@GetMapping("/test1")
    @ApiOperation(value = "test")
    public String test(@RequestParam(value = "userSub", required = true) String userSub) {
    	 
		System.out.println("test aop");
    	return "checkUser";
    }

}
