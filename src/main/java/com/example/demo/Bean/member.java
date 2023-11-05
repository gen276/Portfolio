package com.example.demo.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class member implements Serializable{

//	識別ID
	private Integer Id;
//　　ユーザー名
	private String  Name;
//　　ユーザーID
	private String UserId;
//　　パスワード
	private String  Pass;	
//	メールアドレス
	private String  Mail;

}
