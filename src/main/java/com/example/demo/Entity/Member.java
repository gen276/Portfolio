package com.example.demo.Entity;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Memberテーブルの内容
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member{
	
//	識別ID
	@Id
	private Integer Id;
//　　ユーザー名
	@NotBlank
	private String  Name;
//　　ユーザーID
	@NotBlank
	private String UserId;
//　　パスワード
	@NotBlank
	private String  Pass;	
//	メールアドレス
	private String  Mail;
//新規登録かログインかを判定する
//	private boolean  login;
}
