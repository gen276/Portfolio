package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Bean.Confbean;

@Controller
public class MokuKatsuController {

	@GetMapping("MokuKatsu")
	public String start() {
		return "register";
	}
	
	@PostMapping("check")
	public String confirmView(Confbean c) {
		return "confirm";
	}
	
}
