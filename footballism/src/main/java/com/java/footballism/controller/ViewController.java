package com.java.footballism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
						@RequestParam(value = "exception", required = false) String exception,
						Model model) {
		
		/* 에러와 예외를 모델에 담아 view resolve */
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return "login";
	}
	
	 @GetMapping("hello")
	    public String hello (Model model) {
	        model.addAttribute("data", "hello!");
	        return "hello";
	    }
}
