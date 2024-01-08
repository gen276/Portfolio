package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Goal;

/**
 * ユーザーテーブルのDAO
 * Goalのレポジトリ(データを取得してくる)
 *crudrepositoryの引数のIntegerは主キーの型を使用
 */

@Repository
public interface GoalRepository extends CrudRepository<Goal,Integer>{

}
