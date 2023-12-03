package com.example.demo.Service;

import com.example.demo.Entity.Member;

//Memberサービス処理
public interface MemberService {

//メンバー情報の登録
	void insertMember(Member member);
	
//新規登録かログインの判定
//	Boolean Login(Boolean login);
	
////メンバー情報の更新
//	void updateMember(Member member);
//
////メンバー情報の削除
//	void deleteMemberById(Integer id);
	
}
