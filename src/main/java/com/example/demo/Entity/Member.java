package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Memberテーブルの内容
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member{
	
//	識別ID
	private Integer Id;
//　　ユーザー名
	@NotBlank
	private String  Name;
//　　ユーザーID
	@Id
	@Column
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
