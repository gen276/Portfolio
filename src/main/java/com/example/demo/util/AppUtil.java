package com.example.demo.util;

import java.util.Calendar;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * アプリケーション共通クラス
 * 
 * @author 81805
 *
 */

public class AppUtil {

	 /**
	  * TODO 画面の見出し名をUtilにまとめる
	  */

	/**
	 * メッセージIDからメッセージを取得する
	 * @param messageSource　メッセージソース
	 * @param key　メッセージ
	 * @param params
	 * @return
	 */
	public static String getMessage(MessageSource messageSource , String key , Object...params){
		return messageSource.getMessage(key, params , Locale.JAPAN);
	}
	
	/**
	 * 当月の月名を表示するメソッド
	 */
	public static int NowMonth() {
	    Calendar cal = Calendar.getInstance();
	    int Month = cal.get(Calendar.MONTH) + 1;
	    return Month;
	}
	
}
