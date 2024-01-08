package com.example.demo.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DBから取得したMemberテーブルの情報を一時的に保管する
 * @author 81805
 *
 */

@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member{
	
/**
 * 識別ID
 */
	@Id
	private Integer Id;
	/**
	 * ユーザー名
	 */
	@NotBlank
	private String  Name;
	/**
	 * ユーザーID
	 */
	@Column
	@NotBlank
	private Integer UserId;
	/**
	 * パスワード
	 */
	@NotBlank
	private String  Pass;	
	/**
	 * メールアドレス
	 */
	private String  Mail;
//新規登録かログインかを判定する
//	private boolean  login;
}
