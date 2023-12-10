package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Bean.MemberBean;
import com.example.demo.Entity.Member;
import com.example.demo.Service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MokuKatsuController {

//	private static final String ID = "user";
//	private static final String PASS = "pass";
	private final MemberService service;
	
	@ModelAttribute
	public MemberBean setUpForm() {
		MemberBean form = new MemberBean();
		return form;
	}
	
//　初期画面への遷移
	@GetMapping("MokuKatsu")
	public String start(Model model) {
		model.addAttribute("title"   , "初期表示画面");
		model.addAttribute("message" , "新規登録かログインを選択して下さい。");
		return "Mok_001/Index";
	}
	
//新規登録画面への遷移
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("title" , "新規登録画面");
		return "Mok_001/register";
	}
	
//新規登録内容の確認
	@PostMapping("register_check")
	public String showList(Model model , MemberBean form) {
		//	新規登録内容の確認画面の出力
		model.addAttribute("Name" , form.getName());
		model.addAttribute("UserId", form.getUserId());
		model.addAttribute("Pass", form.getPass());
		model.addAttribute("Mail", form.getMail());
		return "Mok_001/register_check";
	}
	
//新規登録完了画面
	@PostMapping("register_comp")
	public String register_comp() {
		//	データベースにデータの新規登録を行う
		return "Mok_001/register_comp";
	}
	
//ログイン画面への遷移
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("title" , "ログイン画面");
		return "Mok_001/login";
	}
		
	//ログイン画面から目標確認画面への遷移
	@PostMapping("Goal_list")
	public String login_result(Model model , MemberBean form) {
		Optional<Member> UserId = service.searchUserById(form.getUserId());
		boolean isCorrectUserAuth = UserId.isPresent()
				&& form.getPass().equals(UserId.get().getPass());
		if (isCorrectUserAuth) {
			return "Mok_002/Goal_check";
		}else {
			model.addAttribute("errorMsg" , "IDとパスワードの組み合わせが間違っています");
			return "Mok_001/login";			
		}
	}
	
	@RequestMapping("/home")
	public String register_login_comp() {
		//	データベースにデータの新規登録を行う
		return "Mok_001/register_check";
//		return "Mok_002/Goal_list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
