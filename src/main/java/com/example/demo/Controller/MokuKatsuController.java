package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Bean.member;

@Controller
//@RequestMapping("MokuKatsu")
public class MokuKatsuController {

	@ModelAttribute
	public member setUpForm() {
		member form = new member();
		return form;
	}
	
	@GetMapping("MokuKatsu")
	public String start(Model model) {
		return "Mok_001/register";
	}
	
	@PostMapping("register_check")
	public String showList(Model model , @RequestParam String Name , @RequestParam String UserId ,
			@RequestParam String Pass , @RequestParam String Mail) {
//	新規登録内容の確認画面の出力
		model.addAttribute("Name" , Name);
		model.addAttribute("UserId", UserId);
		model.addAttribute("Pass", Pass);
		model.addAttribute("Mail", Mail);
		return "Mok_001/register_check";
	}
	
//	@PostMapping("register_check")
//	public String confirmView(Confbean c) {
//		return "confirm";
//	}
//	
}
