package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Repository.MemberRepository;

@SpringBootApplication
public class MokuKatsuApplication {

	public static void main(String[] args) {
		SpringApplication.run(MokuKatsuApplication.class, args)
		.getBean(MokuKatsuApplication.class).execute();
	}
	
	@Autowired
	MemberRepository repository;
	
	private void execute() {
		register();
	}
	
//	新規登録の実行
	private void register() {
//		エンティティの作成
//		Member newMember = new Member(null , "genta" , "AAAAA" , "BBBBB", "xxxx@xxxxx.ne.jp");
		
//		登録の実行
//		newMember = repository.save(newMember);
		
//登録の確認
//		System.out.println("登録したデータは、" + newMember + "です。");
	}
	
	

}
