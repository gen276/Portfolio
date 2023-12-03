package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Bean.MemberBean;

@Controller
public class MokuKatsuController {

	@ModelAttribute
	public MemberBean setUpForm() {
		MemberBean form = new MemberBean();
		return form;
	}
	
	@GetMapping("MokuKatsu")
	public String start(Model model) {
		model.addAttribute("title" , "新規登録画面");
		return "Mok_001/register";
	}
	
	@PostMapping("register_check")
	public String showList(Model model , MemberBean form) {
		//	新規登録内容の確認画面の出力
		model.addAttribute("Name" , form.getName());
		model.addAttribute("UserId", form.getUserId());
		model.addAttribute("Pass", form.getPass());
		model.addAttribute("Mail", form.getMail());
		return "Mok_001/register_check";
	}
	
	@PostMapping("register_comp")
	public String register_comp() {
		//	データベースにデータの新規登録を行う
		return "Mok_001/register_comp";
	}
	
	@RequestMapping("/home")
	public String register_login_comp() {
		//	データベースにデータの新規登録を行う
		return "Mok_001/register_check";
//		return "Mok_002/Goal_list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
