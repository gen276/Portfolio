package com.example.demo.Controller;

import java.util.Optional;

import org.springframework.context.MessageSource;

/**
 * 処理のコントローラー
 * 
 * @author 81805
 * 
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Member;
import com.example.demo.Form.MemberForm;
import com.example.demo.Service.MemberService;
import com.example.demo.Service.SignupService;
import com.example.demo.util.AppUtil;

import constant.ErrorMsgConst;
import lombok.RequiredArgsConstructor;

/**
 * TODO　コントローラーを画面ごとに分割する
 * @author 81805
 *
 */

@Controller
@RequiredArgsConstructor
public class MokuKatsuController {

	/**
	 * ログイン画面　service
	 */
	private final MemberService service;
	
	private final SignupService signup_service;
	
	private final MessageSource messageSource;
	
//	private final Mapper mapper;
	
	@ModelAttribute
	public MemberForm setUpForm() {
		MemberForm form = new MemberForm();
		return form;
	}

	/**
	 * @return　初期表示画面
	 */
	@GetMapping("MokuKatsu")
	public String start(Model model) {
		model.addAttribute("title"   , "初期表示画面");
		model.addAttribute("message" , "新規登録かログインを選択して下さい。");
		return "Mok_001/Index";
	}
	
	/**
	 * @return　新規登録画面
	 */
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("title" , "新規登録画面");
		return "Mok_001/register";
	}
	
	/**
	 * @param model モデル
	 * @return　新規登録画面
	 */
	@PostMapping("register_check")
	public String showList(Model model , MemberForm form) {
		//	新規登録内容の確認画面の出力
		model.addAttribute("Name" , form.getName());
		model.addAttribute("UserId", form.getUserId());
		model.addAttribute("Pass", form.getPass());
		model.addAttribute("Mail", form.getMail());
		return "Mok_001/register_check";
	}
	
/**
 * 新規登録を実装し結果を表示する
 * @param model
 * @param form
 * @return
 */
	@PostMapping("register_comp")
	public String register_comp(Model model , MemberForm form) {
		
		/**
		 * DBにユーザー情報の新規登録を行う処理
		 */
		signup_service.insertMember(form);
		return "Mok_001/register_comp";
	}
	
//ログイン画面への遷移
	@GetMapping("login")
	public String login(Model model) {
		model.addAttribute("title" , "ログイン画面");
		return "Mok_001/login";
	}
		
	/**
	 * IDとパスワードの照合を行い結果の表示画面へ遷移する部分
	 * TODO　パスワードエンコーダーを使用してパスワードのハッシュ化を行う(SpringSessionを参照)
	 * @param model
	 * @param form
	 * @return
	 * TODO 今後の課題:idアノテーションを付けたもので挿入文を作成できない,idで検索する方法しかない,どこでIDとパスワードの制御を付けるか決める
	 */
	@PostMapping("Goal_list")
	public String login_result(Model model , MemberForm form) {
		Optional<Member> Id = service.searchUserById(form.getId());
		boolean isCorrectUserAuth = Id.isPresent()
				&& form.getPass().equals(Id.get().getPass());
		if (isCorrectUserAuth) {
			String name       = service.GetName(form.getId());
			String goal_Month = AppUtil.NowMonth() + "月の目標";
			String greet      = "こんにちは" + name + "さん";
			model.addAttribute("month" , goal_Month);
			model.addAttribute("name" , greet);
			return "Mok_002/Goal_check";
		}else {
			String errorMsg = AppUtil.getMessage(messageSource, ErrorMsgConst.LOGIN_WRONG_INPUT);
			model.addAttribute("errorMsg" , errorMsg);
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
