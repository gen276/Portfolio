package com.example.demo.Service;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Member;
import com.example.demo.Repository.MemberRepository;

import lombok.RequiredArgsConstructor;

/**
 * Memberサービス処理
 * @author 81805
 *
 */

@Service
@RequiredArgsConstructor
public class MemberService {
	
	/**ユーザー情報テーブルDAO*/
	private final MemberRepository repository;
	
	private final JdbcTemplate jdbcTemplate;
	
	/**
	 * ユーザテーブルから登録番号を基にして名前を取得する
	 * TODO　SQL処理の内容だけを1ファイルにまとめる
	 * @param id
	 * @return
	 */
	public String GetName(Integer id){
		String sql = "select name from member where id = ?";
		return jdbcTemplate.queryForObject(sql , String.class , id);
	}
	
	/**
	 * クエリを使用する為の準備
	 */
//	@Repository
//	public class SampleRepository {
//	    private final JdbcTemplate jdbcTemplate;
//	    
//		    public SampleRepository(JdbcTemplate jdbcTemplate) {
//	        this.jdbcTemplate = jdbcTemplate;
//	    }
//        
//		public String GetName(Integer id){
//			String sql = "select name from member where id = ?";
//			return jdbcTemplate.queryForObject(sql , String.class , id);
//		}
//	}
	
	/**ユーザー情報テーブルキー検索
	 * @param Id
	 * @return　ユーザ情報テーブルを主キー検索をした結果(1件)
	 */
	public Optional<Member> searchUserById (Integer Id) {
		return repository.findById(Id);
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
