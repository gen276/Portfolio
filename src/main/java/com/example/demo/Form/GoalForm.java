package com.example.demo.Form;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DBから取得したGoalテーブルの情報を一時的に保管する
 * @author 81805
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoalForm implements Serializable{
	
	/**
	 * 個人番号
	 */
	private Integer Id;
	
	/**
	 * 目標番号
	 */
	@Id
	private Integer GoalId;
	
	/**
	 * 目標名
	 */
	private String GoalName;
	
	/**
	 * 期限
	 */
	private Date GoalDeadLine;
	
	/**
	 * 目標設定理由
	 */
	private String  GoalReason;
	
	/**
	 * 登録日
	 */
	private Date GoalRegistationDate;
	
	/**
	 * ステータス
	 */
	private String  GoalStatus;
	
	/**
	 *　達成率
	 */
	private Integer  Achieve;
}
