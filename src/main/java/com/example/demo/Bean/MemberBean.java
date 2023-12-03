package com.example.demo.Bean;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberBean implements Serializable{

//	識別ID
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
//新規登録かログインを判定する
//	private Boolean register;

}
