package memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import sqlmap.MybatisManager;

//DAO : Data Access Object 데이타 접근 객체
public class MemoDAO {
	
	
	public void insertMemo(MemoDTO dto) { //insertMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		//session은 namespace(memo3.xml)에서 찾는다
		
		//공백, <, >을 사용했을때 오류현상을 막는다
		/*
		 * String writer=dto.getWriter(); writer=writer.replace(" ", "&nbsp;"); String
		 * memo=dto.getMemo(); memo=memo.replace(" ", "&nbsp");
		 * 
		 * //태그 문자 처리 //String memo=dto.getMemo(); memo=memo.replace("<", "&lt;");//Less
		 * //Than ~보다 작다 memo=memo.replace(">", "&gt;");//Greater Than ~보다 크다
		 * dto.setMemo(memo);
		 */
		  
		session.insert("memo.insert", dto);
		session.commit();
		session.close();
	}
	
	
	public List<MemoDTO> listMemo() { //listMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = null;
		try {
			list = session.selectList("memo.list");
		} catch (Exception e) {
			e.printStackTrace(); //예외 정보 출력
		} finally {
		}
		return list;
	} 

	
	public MemoDTO viewMemo(int idx) { //viewMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		MemoDTO dto = session.selectOne("memo.view", idx);
		session.close();
		return dto;
	}

	
	public void updateMemo(MemoDTO dto) { //updateMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("memo.update", dto);
		session.commit();
		session.close();
	}

	
	public void deleteMemo(int idx) { //deleteMemo 메소드
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("memo.delete", idx);
		session.commit();
		session.close();
		
	}


	public int totCnt() { 
		SqlSession session = MybatisManager.getInstance().openSession();
		int cnt = session.selectOne("memo.totcnt");
		System.out.println("cont"+cnt);
		session.commit();
		session.close();
		
		return cnt;
	}


	public List<MemoDTO> memoList(int start, int end) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = null;
		Map<String,Object> map = new HashMap<>();
		
		map.put("start", start);
		map.put("end", end);
		System.out.println(map);
		
		list =session.selectList("memo.list",map);
		session.close();
		
		return list;
	}


	//ref, re_step값을 밀어낸다
	public void updateStep(int ref, int re_step) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = null;
		Map<String,Object> map = new HashMap<>();
		
		map.put("ref", ref);
		map.put("re_step", re_step);
		System.out.println(map);
		
		list =session.selectList("memo.updateStep",map);
		session.close();
		
		
	}


	public void insert_reply(MemoDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		  
		session.insert("memo.insert_reply", dto);
		session.commit();
		session.close();
		
	}

	
} //class end
