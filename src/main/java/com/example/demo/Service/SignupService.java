package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Form.MemberForm;
import com.example.demo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * 新規登録時実装内容
 * @author 81805
 */

@Service
@RequiredArgsConstructor
public class SignupService {
	
	/**ユーザー情報テーブルDAO*/
	private final MemberRepository repository;
	
	/**Dozer Mapper
	 * Bean情報を一元的に作成できるもの*/
//	private final Mapper mapper;
	
	/**ユーザー情報テーブル新規登録
	 * @param UserId
	 * @return　ユーザ情報の新規登録
	 * ※IDアノテーションが付いているカラムがnullの場合のみinsertが実装
	 */
	public Member insertMember(MemberForm form) {
		var MemberInfo = new Member();
		MemberInfo.setName(form.getName());
		MemberInfo.setUserId(form.getUserId());
		MemberInfo.setPass(form.getPass());
		MemberInfo.setMail(form.getMail());
		
//		Mapper mapper = new Mapper();
//		mapper.map(form, MemberInfo);
        
		return repository.save(MemberInfo);
	};
//	
//新規登録かログインの判定
//	Boolean Login(Boolean login);
	
////メンバー情報の更新
//	void updateMember(Member member);
//
////メンバー情報の削除
//	void deleteMemberById(Integer id);
	
}
