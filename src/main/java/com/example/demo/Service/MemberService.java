package com.example.demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//Memberサービス処理
public class MemberService {
	
	private final MemberRepository repository;

	public Optional<Member> searchUserById (String UserId) {
		return repository.findById(UserId);
	}
	
////メンバー情報の登録
//	void insertMember(Member member);
	
//新規登録かログインの判定
//	Boolean Login(Boolean login);
	
////メンバー情報の更新
//	void updateMember(Member member);
//
////メンバー情報の削除
//	void deleteMemberById(Integer id);
	
}
