package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Entity.Member;

//Memberテーブル
public interface MemberRepository extends CrudRepository<Member,Integer>{

}
