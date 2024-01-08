package com.example.demo.Form;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member情報の一時格納先
 * @author 81805
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberForm implements Serializable{

//	識別ID
	@Id
	private Integer Id;
//　　ユーザー名
	@NotBlank
	private String  Name;
//　　ユーザーID
	@NotBlank
	@Id
	private Integer UserId;
//　　パスワード
	@NotBlank
	private String  Pass;	
//	メールアドレス
	private String  Mail;
//新規登録かログインを判定する
//	private Boolean register;

}
